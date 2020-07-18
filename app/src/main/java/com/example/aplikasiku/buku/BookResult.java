package com.example.aplikasiku.buku;

import com.example.aplikasiku.login.Record;

public class BookResult {
    private Boolean success;
    private String message;
    private Book record;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Book getRecord() {
        return record;
    }

    public void setRecord(Book record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return "BookResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", record=" + record +
                '}';
    }
}
