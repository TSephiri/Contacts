package com.example.clist.Retrofit;

import io.reactivex.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INodeJS {

    @POST("/add/addPersonalContact")
    @FormUrlEncoded
    Observable<String> addPersonalContact(@Field("type")String type,
                                          @Field("name")String name,
                                          @Field("email")String email,
                                          @Field("phone_number")String phone_number,
                                          @Field("birthday") String birthday,
                                          @Field("surname")String surname,
                                          @Field("type_ad1")String type_ad1,
                                          @Field("street1")String street1,
                                          @Field("postal_code1")String postal_code1,
                                          @Field("city1")String city1);

    @POST("/add/addBusinessContact")
    @FormUrlEncoded
    Observable<String> addBusinessContact(@Field("type")String type,
                                          @Field("emails")String email,
                                          @Field("phone_numbers")String phone_number,
                                          @Field("type_ad1")String type_ad1,
                                          @Field("street1")String street1,
                                          @Field("postal_code1")String postal_code1,
                                          @Field("city1")String city1,
                                          @Field("type_ad2")String type_ad2,
                                          @Field("street2")String street2,
                                          @Field("postal_code2")String postal_code2,
                                          @Field("city2")String city2);

    @POST("/delete/deletePersonalContact")
    @FormUrlEncoded
    Observable<String> deletePersonalContact(@Field("id") String id);

    @POST("/delete/deleteBusinessContact")
    @FormUrlEncoded
    Observable<String> deleteBusinessContact(@Field("id") String id);

    @POST("update/UpdatePersonalContact")
    @FormUrlEncoded
    Observable<String> updatePersonalContact(@Field("type")String type,
                                             @Field("email")String email,
                                             @Field("phone_number")String phone_number,
                                             @Field("surname")String surname,
                                             @Field("street1")String street1,
                                             @Field("postal_code1")String postal_code1,
                                             @Field("city1")String city1);

    @POST("update/updateBusinessContact")
    @FormUrlEncoded
    Observable<String> updateBusinessContact(@Field("type")String type,
                                             @Field("emails")String email,
                                             @Field("phone_numbers")String phone_number,
                                             @Field("type_ad1")String type_ad1,
                                             @Field("street1")String street1,
                                             @Field("postal_code1")String postal_code1,
                                             @Field("city1")String city1,
                                             @Field("type_ad2")String type_ad2,
                                             @Field("street2")String street2,
                                             @Field("postal_code2")String postal_code2,
                                             @Field("city2")String city2);

    @GET("personal/getPersonalContact")
    Call<List<PersonalContactModel>> getPersonalContacts();

    @GET("business/getBusinessContacts")
    Call<List<BusinessContactModel>> getBusinessContacts();


}
