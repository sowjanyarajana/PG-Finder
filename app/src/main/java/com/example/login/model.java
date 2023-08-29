package com.example.login;

public class model {

    String id;
    String location;
    String email;
    String mobile_No;
    String name;
    String pg_Name;
    String profession;
    String attached_Washroom;
    String electricity_Bill;
    String mess_Facility;
    String pg_Type;
    String rack_No;
    String seperate_Washroom;
    String pg_Price;

    public model() {
    }


    public model(String attached_Washroom,String electricity_Bill,String email,String id, String location,String mess_Facility, String mobile_No, String name, String pg_Name,String pg_Price,String pg_Type, String profession,String rack_No,String seperate_Washroom) {
        this.attached_Washroom=attached_Washroom;
        this.electricity_Bill=electricity_Bill;
        this.email = email;
        this.id = id;
        this.location = location;
        this.mess_Facility=mess_Facility;
        this.mobile_No = mobile_No;
        this.name = name;
        this.pg_Name = pg_Name;
        this.pg_Type=pg_Type;
        this.profession= profession;
        this.rack_No=rack_No;
        this.seperate_Washroom=seperate_Washroom;
        this.pg_Price=pg_Price;

    }

    public   String getId() {
        return id;
    }

    public  void setId(String id) {
         this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_No = mobile_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPg_Name() {
        return pg_Name;
    }

    public void setPg_Name(String pg_Name) {
        this.pg_Name = pg_Name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAttached_Washroom() {
        return attached_Washroom;
    }

    public void setAttached_Washroom(String attached_Washroom) {
        this.attached_Washroom = attached_Washroom;
    }

    public String getElectricity_Bill() {
        return electricity_Bill;
    }

    public void setElectricity_Bill(String electricity_Bill) {
        this.electricity_Bill = electricity_Bill;
    }

    public String getMess_Facility() {
        return mess_Facility;
    }

    public void setMess_Facility(String mess_Facility) {
        this.mess_Facility = mess_Facility;
    }

    public String getPg_Type() {
        return pg_Type;
    }

    public void setPg_Type(String pg_Type) {
        this.pg_Type = pg_Type;
    }

    public String getRack_No() {
        return rack_No;
    }

    public void setRack_No(String rack_No) {
        this.rack_No = rack_No;
    }

    public String getSeperate_Washroom() {
        return seperate_Washroom;
    }

    public void setSeperate_Washroom(String seperate_Washroom) {
        this.seperate_Washroom = seperate_Washroom;
    }

    public String getPg_Price() {
        return pg_Price;
    }

    public void setPg_Price(String pg_Price) {
        this.pg_Price = pg_Price;
    }
}
