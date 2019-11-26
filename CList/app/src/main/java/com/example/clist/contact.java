package com.example.clist;

public class contact {

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    private String mName;
    private String mNumber;

    public contact(String name,String number){
        mName = name;
        mNumber = number;
    }
}
