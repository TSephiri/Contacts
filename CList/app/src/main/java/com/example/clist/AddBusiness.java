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

public class AddBusiness extends AppCompatActivity {
    FloatingActionButton FAB;

    boolean update = false;
    ImageView proPic;
    public static int RESULT_LOAD_IMAGE = 1;

    String pic_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        FAB = findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        if(update){
            //displayInfo();
        }else
        {
            proPic = findViewById(R.id.pic);
            proPic.setImageResource(R.drawable.ic_person_add_24px);
        }
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

//        if(requestCode == 0 && null!= data)
//        {
//            setBirthday(data.getStringExtra("date"));
//        }

    }
}
