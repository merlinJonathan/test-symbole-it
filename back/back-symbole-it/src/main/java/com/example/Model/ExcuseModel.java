package com.example.Model;

import com.example.dto.ExcuseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExcuseModel extends ExcuseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ExcuseModel() {}

    public ExcuseModel(String message, String httpCode, String tag) {
        super(message, httpCode, tag);
    }

    public ExcuseModel(Long id, String message, String httpCode, String tag) {
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
}
