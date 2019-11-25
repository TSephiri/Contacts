package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Business extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        TextView addB = (TextView) findViewById(R.id.AddBusiness);

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Business.this,"Opening add business contacts", Toast.LENGTH_SHORT)
                        .show();

                //creating personal contact intent
                Intent p = new Intent(Business.this,AddBusiness.class);
                startActivity(p);
            }
        });
    }
}
