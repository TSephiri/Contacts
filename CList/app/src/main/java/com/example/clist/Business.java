package com.example.clist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Business extends AppCompatActivity {
    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<BusinessContactModel> bcmList;
    ArrayList<String> aList = new ArrayList<String>();
    FloatingActionButton FAB;

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

        FAB = findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(Business.this,AddBusiness.class);
                startActivity(add);
            }
        });

        //Init api
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(INodeJS.class);

        getBusinessContacts();

    }

    public void getBusinessContacts(){
        Call<List<BusinessContactModel>> call = myAPI.getBusinessContacts();

        //Toast.makeText(Business.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<BusinessContactModel>>() {
            @Override
            public void onResponse(Call<List<BusinessContactModel>> call, Response<List<BusinessContactModel>> response) {

                bcmList = response.body();

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

        for(BusinessContactModel model : bcmList)
        {
            aList.add(model.getName());

        }

        String[] cList = new String[aList.size()];

         cList = aList.toArray(cList);

         ArrayAdapter<String> bAdapt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cList);
         ListView listView = (ListView) findViewById(R.id.lView);
         listView.setAdapter(bAdapt);

    }

}
