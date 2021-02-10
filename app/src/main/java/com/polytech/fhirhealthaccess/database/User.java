package com.polytech.fhirhealthaccess.database;

import com.orm.SugarRecord;

public class User extends SugarRecord {

    String email;
    String password;

    public User() {}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
