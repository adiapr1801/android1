package com.example.aplikasiku.signup;

import com.example.aplikasiku.login.Record;

public class SignupResult {
    private boolean success;
    private Record record;
    private String message;


    public SignupResult(){}

    public SignupResult(boolean success, Record record, String message) {
        this.success = success;
        this.record = record;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
