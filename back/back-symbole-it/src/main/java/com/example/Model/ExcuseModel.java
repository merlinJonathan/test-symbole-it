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

    public ExcuseModel(String name) {
        super(name);
    }

    public ExcuseModel(Long id, String name) {
        super(name);
        this.id = id;
    }

    public ExcuseDTO getDTO() {
        return new ExcuseDTO(this.message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
