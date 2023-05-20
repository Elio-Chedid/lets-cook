package com.ict370.project_aust;
public class Users
{
    private String phoneNumber;

    private String userName;


    public Users()
    {

    }

    public Users(String name,String phone)
    {
        setPhoneNumber(phone);
        setUserName(name);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }



    public String getUserName() {
        return userName;
    }


}

