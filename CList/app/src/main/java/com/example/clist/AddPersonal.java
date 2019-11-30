package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clist.Retrofit.BusinessContactModel;
import com.example.clist.Retrofit.INodeJS;
import com.example.clist.Retrofit.PersonalContactModel;
import com.example.clist.Retrofit.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPersonal extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    FloatingActionButton FAB;
    ImageView proPic;


    TextInputLayout tiname,tisurname,tiemail,tiphone,tibirthday,tistreet,ticode,ticity;
    String name,surname,email,phone,birthday,street,code,city,pic_add;
    String ID;

    public void setBirthday(String bday){
        tibirthday.getEditText().setText(bday);
    }

    boolean update = false;
    public static int RESULT_LOAD_IMAGE = 1;

    List<PersonalContactModel> pcmList;
    ArrayList<String> aList = new ArrayList<String>();
    ArrayList<PersonalContactModel> contactList = new ArrayList<PersonalContactModel>();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal);

        Retrofit retrofit = RetrofitClient.getInstance();

        myAPI = retrofit.create(INodeJS.class);

        TextView save = (TextView) findViewById(R.id.btn_Save);

        /* Assigning TextInputs to their corresponding views

         */
        tiname = findViewById(R.id.name);
        tisurname = findViewById(R.id.surname);
        tiemail = findViewById(R.id.email);
        tiphone = findViewById(R.id.Phone_Number);
        tistreet = findViewById(R.id.street);
        ticity = findViewById(R.id.City);
        ticode = findViewById(R.id.postal);
        tibirthday = findViewById(R.id.Bday);

        //getting boolean indicating whether to update a contact or
        //create a new one
        update = getIntent().getBooleanExtra("update",false);
        ID = getIntent().getStringExtra("id");

        if(update){
            displayInfo();
        }else
        {
            ImageView Pic = findViewById(R.id.pic);
            Pic.setImageResource(R.drawable.ic_person_add_24px);

        }
        //Method to check if contact should be updated of deleted!!!
        //then passing values to retrofit api
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContactInfo();
                if(update) {
                    updateContact(name, surname, email, phone, street, city, code, birthday,ID,pic_add);
                }else{
                    addContact(name, surname, email, phone, street, city, code, birthday,pic_add);
                }
            }
        });

        FAB = findViewById(R.id.fab);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(update)
                {
                    ID = getIntent().getStringExtra("id");
                    deleteContact(ID);
                }
            }
        });

        proPic = findViewById(R.id.pic);

        proPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        //OnCLick listener for birthday used to start calender event
//        tibirthday.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cal_view = new Intent(AddPersonal.this, Calendar.class);
//                String date = "";
//                cal_view.putExtra("date",date);
//                startActivity(cal_view);
//            }
//        });

        EditText et = findViewById(R.id.b);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cal_view = new Intent(AddPersonal.this, Calender.class);
                //String date = "";
                //cal_view.putExtra("date",date);
                startActivityForResult(cal_view,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            pic_add = cursor.getString(columnIndex);
            cursor.close();

            // String picturePath contains the path of selected Image

            proPic = (ImageView) findViewById(R.id.pic);
            proPic.setImageBitmap(BitmapFactory.decodeFile(pic_add));
        }

        if(requestCode == 0 && null!= data)
        {
            setBirthday(data.getStringExtra("date"));
        }

    }

    //getting contact info from editText
    public void getContactInfo(){
       name = tiname.getEditText().getText().toString().trim();
       surname = tisurname.getEditText().getText().toString().trim();
       email = tiemail.getEditText().getText().toString().trim();
       phone = tiphone.getEditText().getText().toString().trim();
       street = tistreet.getEditText().getText().toString().trim();
       city = ticity.getEditText().getText().toString().trim();
       code = ticode.getEditText().getText().toString().trim();
       birthday = tibirthday.getEditText().getText().toString().trim();
    }

    //Method to create a new contact
    public void addContact(String n,String s,String e,String no,String str,String c,
                           String code,String bday,String pic)
    {

        compositeDisposable.add(myAPI.addPersonalContact("p",n,e,no,bday,s,"residential",str,code,c,pic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(AddPersonal.this, "Added Contact ", Toast.LENGTH_SHORT).show();
                            //creating new activity
                            Intent personal = new Intent(AddPersonal.this, Personal.class);
                            startActivity(personal);
                            finish();
                        } else {
                            Toast.makeText(AddPersonal.this, "Failed to add Contact", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }

    //method to set EditText to info passed to this activity
    public void displayInfo(){
        tiname.getEditText().setText(getIntent().getStringExtra("name"));
        tisurname.getEditText().setText(getIntent().getStringExtra("surname"));
        tibirthday.getEditText().setText(getIntent().getStringExtra("bday"));
        tiemail.getEditText().setText(getIntent().getStringExtra("email"));
        tiphone.getEditText().setText(getIntent().getStringExtra("phone"));
        tistreet.getEditText().setText(getIntent().getStringExtra("street"));
        ticity.getEditText().setText(getIntent().getStringExtra("city"));
        ticode.getEditText().setText(getIntent().getStringExtra("post"));
        pic_add = getIntent().getStringExtra("pic_add");
        ImageView Pic = findViewById(R.id.pic);
        if(pic_add != null) {
            Pic.setImageBitmap(BitmapFactory.decodeFile(pic_add));
        }else
        {
            Pic.setImageResource(R.drawable.ic_person_add_24px);
        }
    }

    //method to update contact based on info in editText
    public void updateContact(String n,String s,String e,String no,String str,String c,
                              String code,String bday,String id,String pic)
    {
        Toast.makeText(AddPersonal.this, "addContact: "+ id +" "+ n +"  "+ e +"   "+ pic +"  "+ str, Toast.LENGTH_LONG).show();
       // Log.i("addContact: "+ n +"  "+ e +"   "+ bday+"  "+ str,);
        compositeDisposable.add(myAPI.updatePersonalContact("p",id,bday,n,e,no,"Physical",s,str,code,c,pic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    if (s.equals("1"))//column name from database
                    {
                        Toast.makeText(AddPersonal.this, "Updated Contact ", Toast.LENGTH_SHORT).show();
                        //creating new activity
                        Intent personal = new Intent(AddPersonal.this, Personal.class);
                        startActivity(personal);
                        finish();
                    } else {
                        Toast.makeText(AddPersonal.this, "Failed to Update Contact", Toast.LENGTH_SHORT).show();
                    }
                }
            })
        );
    }

    public void deleteContact(String id)
    {
        compositeDisposable.add(myAPI.deletePersonalContact(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(AddPersonal.this, "Deleted Contact ", Toast.LENGTH_SHORT).show();
                            //creating new activity
                            Intent personal = new Intent(AddPersonal.this, Personal.class);
                            startActivity(personal);
                            finish();
                        } else {
                            Toast.makeText(AddPersonal.this, "Failed to Delete Contact", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
}
