package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 17/10/2017.
 */

public class Users {
    public String name;
    public String email;
    public String key;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Users() {
    }

    public Users(String name, String email, String user_id) {
        this.name = name;
        this.email = email;
        this.key = user_id;
    }
}
