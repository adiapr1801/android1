package com.example.aplikasiku.login;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
    private boolean active;


    public Record(String username, String password, List<String> roles, boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public Record() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Record{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", active=" + active +
                '}';
    }
}
