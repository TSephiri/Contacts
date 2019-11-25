package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView personal,business, addB,addP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personal = (TextView) findViewById(R.id.Personal);
        business = (TextView) findViewById(R.id.Business);
//        addB = (TextView) findViewById(R.id.AddBusiness);
//        addP = (TextView) findViewById(R.id.AddPersonal);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening Personal contacts",Toast.LENGTH_SHORT)
                        .show();

                //creating personal contact intent
                Intent p = new Intent(MainActivity.this,Personal.class);
                startActivity(p);
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Opening business contacts",Toast.LENGTH_SHORT)
                        .show();

                //creating personal contact intent
                Intent p = new Intent(MainActivity.this,Business.class);
                startActivity(p);
            }
        });

//        addP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Opening add personal contacts",Toast.LENGTH_SHORT)
//                        .show();
//
//                //creating personal contact intent
//                Intent p = new Intent(MainActivity.this,AddPersonal.class);
//                startActivity(p);
//            }
//        });
//
//        addB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Opening add business contacts",Toast.LENGTH_SHORT)
//                        .show();
//
//                //creating personal contact intent
//                Intent p = new Intent(MainActivity.this,AddBusiness.class);
//                startActivity(p);
//            }
//        });
    }

}
