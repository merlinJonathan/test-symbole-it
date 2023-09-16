package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExcuseDTO {
    protected String message;

    @JsonProperty("http_code")
    protected String httpCode;
    protected String tag;

    public ExcuseDTO() { }

    public ExcuseDTO(String message, String httpCode, String tag) {
        this.message = message;
        this.httpCode = httpCode;
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
