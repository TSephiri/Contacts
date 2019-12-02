package com.example.clist.Retrofit;

public class PersonalContactModel {
    private String user_id,type,pic_add,birthday,email,phone_number,name,surname,
            Add_id,type_add,Street,postal_code,city;


//    public PersonalContactModel(String user_id, String birthday, String email, String phone_number,
//                                String name, String surname, String type_add, String street,
//                                String postal_code, String city) {
//        this.user_id = user_id;
//        this.birthday = birthday;
//        this.email = email;
//        this.phone_number = phone_number;
//        this.name = name;
//        this.surname = surname;
//        this.type_add = type_add;
//        this.Street = street;
//        this.postal_code = postal_code;
//        this.city = city;
//    }

    public PersonalContactModel(String user_id, String birthday, String email, String phone_number,
                                    String name, String surname, String type_add, String street,
                                    String postal_code, String city,String pic_add) {
        this.user_id = user_id;
        this.type = type;
        this.pic_add = pic_add;
        this.birthday = birthday;
        this.email = email;
        this.phone_number = phone_number;
        this.name = name;
        this.surname = surname;
        //Add_id = add_id;
        this.type_add = type_add;
        Street = street;
        this.postal_code = postal_code;
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic_add() {
        return pic_add;
    }

    public void setPic_add(String pic_add) {
        this.pic_add = pic_add;
    }

    public String getAdd_id() {
        return Add_id;
    }

    public void setAdd_id(String add_id) {
        Add_id = add_id;
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
        return Street;
    }

    public void setStreet(String street) {
        this.Street = street;
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
