package com.example.Model;

import com.example.dto.ExcuseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class ExcuseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    protected String message;

    @Column
    protected Long httpCode;

    @Column
    protected String tag;

    public ExcuseModel() {}

    public ExcuseModel(String message, Long httpCode, String tag) {
        this.message = message;
        this.tag = tag;
        this.httpCode = httpCode;
    }

    public ExcuseModel(Long id, String message, Long httpCode, String tag) {
        this(message, httpCode, tag);
        this.id = id;
    }

    public ExcuseDTO getDTO() {
        return new ExcuseDTO(this.message, this.httpCode, this.tag);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
