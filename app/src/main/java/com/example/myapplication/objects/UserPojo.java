package com.example.myapplication.objects;

public class UserPojo {

    private String idn;
    private String email;
    private String userName;
    private String company;

//    UserPojo(String id, String email, String userName, String company){
//        this.id=id;
//        this.email= email;
//        this.userName=userName;
//        this.company=company;
//    }


    public UserPojo(){}

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
