package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        TextView addP = (TextView) findViewById(R.id.AddPersonal);


        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Personal.this,"Opening add personal contacts",Toast.LENGTH_SHORT)
                        .show();

                //creating personal contact intent
                Intent p = new Intent(Personal.this,AddPersonal.class);
                startActivity(p);
            }
        });
    }
}
