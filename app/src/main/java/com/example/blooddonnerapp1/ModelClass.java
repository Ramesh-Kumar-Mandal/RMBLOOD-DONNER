package com.example.blooddonnerapp1;

public class ModelClass {

    public ModelClass() {

    }


    public ModelClass(String usernamevalue, String passwordvalue, String emailvalue, String phonenovalue, String dobvale, String addressvalue, String bloodgroupvalue) {

        this.usernamevalue = usernamevalue;
        this.passwordvalue = passwordvalue;
        this.emailvalue = emailvalue;
        this.phonenovalue = phonenovalue;
        this.dobvale = dobvale;
        this.addressvalue = addressvalue;
        this.bloodgroupvalue = bloodgroupvalue;
    }


    public  String getUsernamevalue() {
        return usernamevalue;
    }

    public void setUsernamevalue(String usernamevalue) {
        this.usernamevalue = usernamevalue;
    }

    public String getPasswordvalue() {
        return passwordvalue;
    }

    public void setPasswordvalue(String passwordvalue) {
        this.passwordvalue = passwordvalue;
    }

    public String getEmailvalue() {
        return emailvalue;
    }

    public void setEmailvalue(String emailvalue) {
        this.emailvalue = emailvalue;
    }

    public String getPhonenovalue() {
        return phonenovalue;
    }

    public void setPhonenovalue(String phonenovalue) {
        this.phonenovalue = phonenovalue;
    }

    public String getDobvale() {
        return dobvale;
    }

    public void setDobvale(String dobvale) {
        this.dobvale = dobvale;
    }

    public String getAddressvalue() {
        return addressvalue;
    }

    public void setAddressvalue(String addressvalue) {
        this.addressvalue = addressvalue;
    }

    public String getBloodgroupvalue() {
        return bloodgroupvalue;
    }

    public void setBloodgroupvalue(String bloodgroupvalue) {
        this.bloodgroupvalue = bloodgroupvalue;
    }

    String usernamevalue,passwordvalue,emailvalue,phonenovalue,dobvale,addressvalue,bloodgroupvalue;
}
