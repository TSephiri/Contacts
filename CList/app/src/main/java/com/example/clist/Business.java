package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.clist.Retrofit.*;

import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clist.Retrofit.INodeJS;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Business extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<BusinessContactModel> bcmList;

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
        setContentView(R.layout.activity_business);

        TextView addB = (TextView) findViewById(R.id.AddBusiness);

        //Init api
        Retrofit retrofit = RetrofitClient.getInstance_get();
       myAPI = retrofit.create(INodeJS.class);

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

//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://192.168.43.224:3000/")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//        myAPI = retrofit.create(INodeJS.class);

        getBusinessContacts();


    }

    public void getBusinessContacts(){
        Call<List<BusinessContactModel>> call = myAPI.getBusinessContacts();

        //Toast.makeText(Business.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<BusinessContactModel>>() {
            @Override
            public void onResponse(Call<List<BusinessContactModel>> call, Response<List<BusinessContactModel>> response) {

                bcmList = response.body();
                Toast.makeText(Business.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();
                addTextViews();
            }

            @Override
            public void onFailure(Call<List<BusinessContactModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }

    public void addTextViews()
    {
//        ScrollView sv = (ScrollView) findViewById(R.id.root);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//        LinearLayout layout = new LinearLayout(this);
//
//        for(BusinessContactModel model : bcmList)
//        {
//            //Toast.makeText(Business.this, model.getName() , Toast.LENGTH_SHORT).show();
//            TextView contact = new TextView(this);
//            contact.setText(""+model.getName());
//            layout.addView(contact,lp);
//        }
//
//        sv.addView(layout);

    }

}
