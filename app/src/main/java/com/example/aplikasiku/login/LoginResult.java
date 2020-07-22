package com.example.aplikasiku.login;


public class LoginResult {
    private boolean success;
    private Record record;
    private String token;

    public LoginResult(){}

    public LoginResult(boolean success, Record record, String token) {
        this.success = success;
        this.record = record;
        this.token = token;
    }

    public  boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
