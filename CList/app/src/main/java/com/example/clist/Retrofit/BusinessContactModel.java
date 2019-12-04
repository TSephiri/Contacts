package com.example.clist.Retrofit;

public class BusinessContactModel {
    private String user_id;
    private String type;
    private String pic_add;
    private String vat_no;
    private String emails;
    private String phone_numbers;
    private String name;
    private String street1;
    private String postal_code1;
    private String city1;
    private String street2;
    private String postal_code2;
    private String city2;

    public BusinessContactModel(String user_id, String type, String pic_add, String vat_no, String emails, String phone_numbers, String name, String street1, String postal_code1, String city1, String street2, String postal_code2, String city2) {
        this.user_id = user_id;
        this.type = type;
        this.pic_add = pic_add;
        this.vat_no = vat_no;
        this.emails = emails;
        this.phone_numbers = phone_numbers;
        this.name = name;
        this.street1 = street1;
        this.postal_code1 = postal_code1;
        this.city1 = city1;
        this.street2 = street2;
        this.postal_code2 = postal_code2;
        this.city2 = city2;
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

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getPostal_code1() {
        return postal_code1;
    }

    public void setPostal_code1(String postal_code1) {
        this.postal_code1 = postal_code1;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getPostal_code2() {
        return postal_code2;
    }

    public void setPostal_code2(String postal_code2) {
        this.postal_code2 = postal_code2;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }
}
