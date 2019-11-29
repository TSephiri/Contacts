package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clist.Retrofit.PersonalContactModel;
import com.example.clist.Retrofit.INodeJS;
import com.example.clist.Retrofit.RetrofitClient;

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
    ArrayList<PersonalContactModel> contactList = new ArrayList<PersonalContactModel>();
    ListView listView;

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

        listView = (ListView) findViewById(R.id.pListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent contactDetails = new Intent(Personal.this,AddPersonal.class);

                PersonalContactModel tempContact = contactList.get(position);
                contactDetails.putExtra("id", tempContact.getUser_id());
                contactDetails.putExtra("name", tempContact.getName());
                contactDetails.putExtra("surname", tempContact.getSurname());
                contactDetails.putExtra("bday", tempContact.getBirthday());
                contactDetails.putExtra("email", tempContact.getEmail());
                contactDetails.putExtra("phone", tempContact.getPhone_number());
                contactDetails.putExtra("street", tempContact.getStreet());
                contactDetails.putExtra("post", tempContact.getPostal_code());
                contactDetails.putExtra("city", tempContact.getCity());
                contactDetails.putExtra("type_ad",tempContact.getType_add());

                Toast.makeText(Personal.this,""+tempContact.getUser_id()+" "+tempContact.getName()+"   "+
                        tempContact.getBirthday()+" "+tempContact.getStreet(),Toast.LENGTH_LONG).show();
                startActivity(contactDetails);
                finish();
            }
        });

        getPersonalContacts();
    }

    public void getPersonalContacts(){
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(INodeJS.class);

        Call<List<PersonalContactModel>> call = myAPI.getPersonalContacts();

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
            contactList.add(new PersonalContactModel(model.getUser_id(),model.getBirthday(),model.getEmail(),
                    model.getPhone_number(),model.getName(),model.getSurname(),
                    model.getType_add(), model.getStreet(),model.getPostal_code(),model.getCity()));
        }

        ContactAdapter bAdapt = new ContactAdapter(this,contactList);
        listView.setAdapter(bAdapt);
    }
}
