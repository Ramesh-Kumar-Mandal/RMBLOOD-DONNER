package com.example.blooddonnerapp1;

public class Model {
    String namevalue, phonevalue, addressvalue, bloodgropvalue,locationvaue;
    public Model() {

    }
    public Model(String namevalue, String phonevalue, String addressvalue, String bloodgropvalue, String locationvaue) {
        this.namevalue = namevalue;
        this.phonevalue = phonevalue;
        this.addressvalue = addressvalue;
        this.bloodgropvalue = bloodgropvalue;
        this.locationvaue = locationvaue;
    }

    public String getNamevalue() {
        return namevalue;
    }

    public void setNamevalue(String namevalue) {
        this.namevalue = namevalue;
    }

    public String getPhonevalue() {
        return phonevalue;
    }

    public void setPhonevalue(String phonevalue) {
        this.phonevalue = phonevalue;
    }

    public String getAddressvalue() {
        return addressvalue;
    }

    public void setAddressvalue(String addressvalue) {
        this.addressvalue = addressvalue;
    }

    public String getBloodgropvalue() {
        return bloodgropvalue;
    }

    public void setBloodgropvalue(String bloodgropvalue) {
        this.bloodgropvalue = bloodgropvalue;
    }

    public String getLocationvaue() {
        return locationvaue;
    }

    public void setLocationvaue(String locationvaue) {
        this.locationvaue = locationvaue;
    }
}