package com.example.contactapp;

import android.os.Bundle;

import com.example.contactapp.Retrofit.PersonalContactModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.example.contactapp.Retrofit.BusinessContactModel;
import com.example.contactapp.Retrofit.INodeJS;
import com.example.contactapp.Retrofit.RetrofitClient;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusinessContacts extends AppCompatActivity {

    private List<BusinessContactModel> ContactList;
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        setContentView(R.layout.activity_business_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);
    }

    public void getBusinessContact()
    {
        Call<List<BusinessContactModel>> call = myAPI.getBusinessContacts();

        //Toast.makeText(FrontPage.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<BusinessContactModel>>() {
            @Override
            public void onResponse(Call<List<BusinessContactModel>> call, Response<List<BusinessContactModel>> response) {

                ContactList = response.body();

            }

            @Override
            public void onFailure(Call<List<BusinessContactModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }
}
