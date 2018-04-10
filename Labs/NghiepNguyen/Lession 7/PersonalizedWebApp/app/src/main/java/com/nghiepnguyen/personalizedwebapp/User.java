package com.nghiepnguyen.personalizedwebapp;

/**
 * Created by 986375 on 4/10/2018.
 */


public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.getEmail()) && password.equals(user.password);
    }
}
