package com.example.aplikasiku.signup;

import java.util.ArrayList;
import java.util.List;

public class SignupBody {
    private String username;
    private String password;
    private String email;
    private String name;
    private List<String> roles = new ArrayList<>();
    private boolean active;


    public  SignupBody(String username, String password, String email, String name){}
    public SignupBody(String username, String password, String email, String name, List<String> roles, boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
