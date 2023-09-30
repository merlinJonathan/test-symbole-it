package com.example.dto;

import com.example.Model.ExcuseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExcuseDTO {
    @JsonProperty("message ")
    protected String message;
    @JsonProperty("http_code")
    protected Long httpCode;
    @JsonProperty("tag ")
    protected String tag;

    public ExcuseDTO() { }

    public ExcuseDTO(String message, Long httpCode, String tag) {
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

    public Long getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Long httpCode) {
        this.httpCode = httpCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ExcuseModel toModel() {
        return new ExcuseModel(this.getMessage(), this.getHttpCode(), this.getTag());
    }
}
