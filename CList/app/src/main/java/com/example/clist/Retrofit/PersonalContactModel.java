package com.example.clist.Retrofit;

public class PersonalContactModel {
    private String user_id,birthday,email,phone_number,name,surname,
            type_add,street,postal_code,city;


    public PersonalContactModel(String user_id, String birthday, String email, String phone_number,
                                String name, String surname, String type_add, String street,
                                String postal_code, String city) {
        this.user_id = user_id;
        this.birthday = birthday;
        this.email = email;
        this.phone_number = phone_number;
        this.name = name;
        this.surname = surname;
        this.type_add = type_add;
        this.street = street;
        this.postal_code = postal_code;
        this.city = city;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType_add() {
        return type_add;
    }

    public void setType_add(String type_add) {
        this.type_add = type_add;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
