package com.example.contact.Retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INodeJS {
    @POST("")
    @FormUrlEncoded
    Observable <String> userLogin(
            @Field("add_student") String student,
            @Field("add_password") String password
    );

    @GET("class/{student}")
    Call <List<ContactModel>> getClassInfo(@Path("student")String student);
}
