package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clist.Retrofit.BusinessContactModel;
import com.example.clist.Retrofit.INodeJS;
import com.example.clist.Retrofit.PersonalContactModel;
import com.example.clist.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
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

    TextInputLayout tiname,tisurname,tiemail,tiphone,tibirthday,tistreet,ticode,ticity;
    String name,surname,email,phone,birthday,street,code,city;
    String ID;

    boolean update = false;

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
        tiname = findViewById(R.id.name);
        tisurname = findViewById(R.id.surname);
        tiemail = findViewById(R.id.email);
        tiphone = findViewById(R.id.Phone_Number);
        tistreet = findViewById(R.id.street);
        ticity = findViewById(R.id.City);
        ticode = findViewById(R.id.postal);
        tibirthday = findViewById(R.id.Bday);


        ID = getIntent().getStringExtra("id");

        if(!ID.equals("")){
            displayInfo();
            update = true;
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContactInfo();
                if(update) {
                    updateContact(name, surname, email, phone, street, city, code, birthday,ID);
                }else{
                    addContact(name, surname, email, phone, street, city, code, birthday);
                }
            }
        });


    }

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

    public void addContact(String n,String s,String e,String no,String str,String c,
                           String code,String bday)
    {

        compositeDisposable.add(myAPI.addPersonalContact("p",n,e,no,bday,s,"residential",str,code,c)
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
    public void displayInfo(){
        tiname.getEditText().setText(getIntent().getStringExtra("name"));
        tisurname.getEditText().setText(getIntent().getStringExtra("surname"));
        tibirthday.getEditText().setText(getIntent().getStringExtra("bday"));
        tiemail.getEditText().setText(getIntent().getStringExtra("email"));
        tiphone.getEditText().setText(getIntent().getStringExtra("phone"));
        tistreet.getEditText().setText(getIntent().getStringExtra("street"));
        ticity.getEditText().setText(getIntent().getStringExtra("city"));
        ticode.getEditText().setText(getIntent().getStringExtra("post"));
    }

    public void updateContact(String n,String s,String e,String no,String str,String c,
                              String code,String bday,String id)
    {
        Toast.makeText(AddPersonal.this, "addContact: "+ n +"  "+ e +"   "+ bday+"  "+ str, Toast.LENGTH_LONG).show();
       // Log.i("addContact: "+ n +"  "+ e +"   "+ bday+"  "+ str,);
        compositeDisposable.add(myAPI.updatePersonalContact("p",id,bday,n,e,no,"Physical",s,str,code,c)
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
}
