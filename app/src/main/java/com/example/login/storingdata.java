package com.example.login;

public class storingdata {
    String name,phonenumber,email,password,conformpwd;

    public storingdata() {
    }

    public storingdata(String name, String phonenumber, String email, String password, String conformpwd) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.conformpwd = conformpwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConformpwd() {
        return conformpwd;
    }

    public void setConformpwd(String conformpwd) {
        this.conformpwd = conformpwd;
    }
}
