package com.example.controllers;

import com.example.Model.ExcuseModel;
import com.example.repositories.ExcuseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExcuseControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExcuseController excuseController;

    @MockBean
    private ExcuseRepository excuseRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPersonById() throws Exception {
        long excuseId = 1;
        ExcuseModel excuseModel = new ExcuseModel(excuseId, "John");

        Mockito.when(this.excuseRepository.findById(excuseId)).thenReturn(Optional.of(excuseModel));

        mockMvc.perform(MockMvcRequestBuilders.get("/excuse/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(excuseModel.getMessage()));

        Mockito.verify(this.excuseRepository, times(1)).findById(excuseId);
    }

    @Test
    public void testGetPersonByIdNotFound() throws Exception {
        long excuseId = 1;

        Mockito.when(this.excuseRepository.findById(excuseId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/excuse/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        Mockito.verify(this.excuseRepository, times(1)).findById(excuseId);
    }
}
