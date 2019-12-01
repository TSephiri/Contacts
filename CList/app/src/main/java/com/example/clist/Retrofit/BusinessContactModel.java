package com.example.clist.Retrofit;

public class BusinessContactModel {
    private String user_id;
    private String type;
    private String pic_add;
    private String vat_no;
    private String emails;
    private String phone_numbers;
    private String name;
    private String type_add;
    private String street;
    private String postal_code;
    private String city;

    public BusinessContactModel(String user_id, String type, String pic_add, String vat_no,
                                String emails, String phone_numbers, String name, String type_add,
                                String street, String postal_code, String city) {
        this.user_id = user_id;
        this.type = type;
        this.pic_add = pic_add;
        this.vat_no = vat_no;
        this.emails = emails;
        this.phone_numbers = phone_numbers;
        this.name = name;
        this.type_add = type_add;
        this.street = street;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVat_no() {
        return vat_no;
    }

    public void setVat_no(String vat_no) {
        this.vat_no = vat_no;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(String phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
