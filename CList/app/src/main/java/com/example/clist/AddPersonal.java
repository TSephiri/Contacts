package com.example.clist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clist.Retrofit.BusinessContactModel;
import com.example.clist.Retrofit.INodeJS;
import com.example.clist.Retrofit.RetrofitClient;

import org.w3c.dom.Text;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class AddPersonal extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_personal);

        Retrofit retrofit = RetrofitClient.getInstance();

        myAPI = retrofit.create(INodeJS.class);

        TextView save = (TextView) findViewById(R.id.btn_Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    public void addContact()
    {
        compositeDisposable.add(myAPI.addPersonalContact("p","hey","game@ggi.com","99919191","25-08","surprise","residential","live long str","1919","party Central")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.equals("1"))//column name from database
                        {
                            Toast.makeText(AddPersonal.this, "Added Contact ", Toast.LENGTH_SHORT).show();
                            //creating new activity
//                            Intent frontP = new Intent(AddPersonal.this, FrontPage.class);
//                            frontP.putExtra("student",student);
//                            startActivity(frontP);
//                            finish();
                        } else {
                            Toast.makeText(AddPersonal.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );
    }
}
