package com.example.clist;

//import com.example.clist.AddPersonal;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

//import com.example.clist.AddPersonal.setBirthday;

public class Calender extends AppCompatActivity {
    String date;
    CalendarView cV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        cV = findViewById(R.id.calendarView);

        cV.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent();
                intent.putExtra("date",dayOfMonth+"-"+month+1+"-"+year);
                setResult(0,intent);
                finish();
            }
        });

    }
}
