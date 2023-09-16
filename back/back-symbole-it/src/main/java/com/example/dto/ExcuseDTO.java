package com.example.dto;

public class ExcuseDTO {
    protected String message;

    public ExcuseDTO() { }

    public ExcuseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
