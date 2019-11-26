package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clist.Retrofit.PersonalContactModel;
import com.example.clist.Retrofit.INodeJS;
import com.example.clist.Retrofit.PersonalContactModel;
import com.example.clist.Retrofit.RetrofitClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Personal extends AppCompatActivity {

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<PersonalContactModel> pcmList;
    ArrayList<String> aList = new ArrayList<String>();


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
        setContentView(R.layout.activity_personal);

        //TextView addP = (TextView) findViewById(R.id.AddPersonal);

        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(INodeJS.class);

//        addP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Personal.this,"Opening add personal contacts",Toast.LENGTH_SHORT)
//                        .show();
//
//                //creating personal contact intent
//                Intent p = new Intent(Personal.this,AddPersonal.class);
//                startActivity(p);
//            }
//        });
        getPersonalContacts();
    }

    public void getPersonalContacts(){
        Call<List<PersonalContactModel>> call = myAPI.getPersonalContacts();

        //Toast.makeText(Business.this, "Uhm : hey" , Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<List<PersonalContactModel>>() {
            @Override
            public void onResponse(Call<List<PersonalContactModel>> call, Response<List<PersonalContactModel>> response) {

                pcmList = response.body();

                addTextViews();
            }

            @Override
            public void onFailure(Call<List<PersonalContactModel>> call, Throwable t) {
                Log.i("log ",t.getMessage());
            }
        });
    }

    public void addTextViews()
    {

        for(PersonalContactModel model : pcmList)
        {
            aList.add(model.getName());
        }
        
        String[] cList = new String[aList.size()];

        cList = aList.toArray(cList);

        ArrayAdapter<String> bAdapt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cList);
        ListView listView = (ListView) findViewById(R.id.pListView);
        listView.setAdapter(bAdapt);

    }
}
