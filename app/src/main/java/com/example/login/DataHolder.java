package com.example.login;

public class DataHolder {
    String susername,spassword,sphonenum;

    public DataHolder(String susername, String spassword, String sphonenum) {
        this.susername = susername;
        this.spassword = spassword;
        this.sphonenum = sphonenum;
    }

    public String getSusername() {
        return susername;
    }

    public void setSusername(String susername) {
        this.susername = susername;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    public String getSphonenum() {
        return sphonenum;
    }

    public void setSphonenum(String sphonenum) {
        this.sphonenum = sphonenum;
    }
}
