package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class AddBusiness extends AppCompatActivity {
    FloatingActionButton FAB;

    boolean update = false;
    ImageView proPic;
    public static int RESULT_LOAD_IMAGE = 1;

    TextInputLayout tiname,tiVat,tiemail1,tiphone1,tiemail2,tiphone2,tistreet,ticode,
            ticity,tistreet1,ticode1,ticity1;

    String pic_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        proPic = findViewById(R.id.pic);

        if(update){
            //displayInfo();
        }else
        {
            //ImageView Pic = findViewById(R.id.pic);
            proPic.setImageResource(R.drawable.ic_person_add_24px);
        }

        FAB = findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        proPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
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

            //proPic = (ImageView) findViewById(R.id.pic);
            proPic.setImageBitmap(BitmapFactory.decodeFile(pic_add));
        }

    }

    public void displayInfo(){
        tiname.getEditText().setText(getIntent().getStringExtra("name"));
        tiemail1.getEditText().setText(getIntent().getStringExtra("email1"));
        tiemail2.getEditText().setText(getIntent().getStringExtra("email2"));
        tiphone1.getEditText().setText(getIntent().getStringExtra("phone1"));
        tiphone2.getEditText().setText(getIntent().getStringExtra("phone2"));
        tistreet.getEditText().setText(getIntent().getStringExtra("street"));
        ticity.getEditText().setText(getIntent().getStringExtra("city"));
        ticode.getEditText().setText(getIntent().getStringExtra("post"));
        tistreet.getEditText().setText(getIntent().getStringExtra("street"));
        ticity.getEditText().setText(getIntent().getStringExtra("city"));
        ticode.getEditText().setText(getIntent().getStringExtra("post"));
        tistreet1.getEditText().setText(getIntent().getStringExtra("street2"));
        ticity1.getEditText().setText(getIntent().getStringExtra("city2"));
        ticode1.getEditText().setText(getIntent().getStringExtra("post2"));
        pic_add = getIntent().getStringExtra("pic_add");

        ImageView Pic = findViewById(R.id.pic);
        if(pic_add != null) {
            Pic.setImageBitmap(BitmapFactory.decodeFile(pic_add));
        }else
        {
            Pic.setImageResource(R.drawable.ic_person_add_24px);
        }
    }

}
