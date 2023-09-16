package com.example.services;

import com.example.Model.ExcuseModel;
import com.example.dto.ExcuseDTO;
import com.example.repositories.ExcuseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseInitializer {
    @Autowired
    private ExcuseRepository excuseRepository;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/h2.db.json");
        List<ExcuseDTO> excuseDTOList = objectMapper.readValue(inputStream, new TypeReference<List<ExcuseDTO>>() {});

        this.excuseRepository.saveAll(excuseDTOList.stream()
                .map(e -> new ExcuseModel(e.getMessage(), e.getHttpCode(), e.getTag()))
                .collect(Collectors.toList()));
    }
}
