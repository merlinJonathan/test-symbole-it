package com.example.controllers;

import com.example.Model.ExcuseModel;
import com.example.contantes.ApiUrls;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

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
    public void testGetExcuseById() throws Exception {
        long excuseId = 1;
        ExcuseModel excuseModel = new ExcuseModel(excuseId, "toto", "202", "piwi");

        Mockito.when(this.excuseRepository.findById(excuseId)).thenReturn(Optional.of(excuseModel));

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE + "/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(excuseModel.getMessage()))
                .andExpect(jsonPath("$.tag").value(excuseModel.getTag()))
                .andExpect(jsonPath("$.http_code").value(excuseModel.getHttpCode()))
        ;

        Mockito.verify(this.excuseRepository, times(1)).findById(excuseId);
    }

    @Test
    public void testGetExcuseByIdNotFound() throws Exception {
        long excuseId = 1;

        Mockito.when(this.excuseRepository.findById(excuseId)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE + "/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;

        Mockito.verify(this.excuseRepository, times(1)).findById(excuseId);
    }

    @Test
    public void testGetAllExcuseWithValues() throws Exception {
        List<ExcuseModel> excuses = Arrays.asList(
                new ExcuseModel(1l, "premier message", "100", "tag 1"),
                new ExcuseModel(2l, "second message", "200", "tag 2")
        );

        Mockito.when(this.excuseRepository.findAll()).thenReturn(excuses);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].message").value(excuses.get(0).getMessage()))
                .andExpect(jsonPath("$[0].tag").value(excuses.get(0).getTag()))
                .andExpect(jsonPath("$[0].http_code").value(excuses.get(0).getHttpCode()))
                .andExpect(jsonPath("$[1].message").value(excuses.get(1).getMessage()))
                .andExpect(jsonPath("$[1].tag").value(excuses.get(1).getTag()))
                .andExpect(jsonPath("$[1].http_code").value(excuses.get(1).getHttpCode()))
        ;

        Mockito.verify(this.excuseRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllExcuseWithoutValues() throws Exception {
        List<ExcuseModel> excuses = new ArrayList<>();

        Mockito.when(this.excuseRepository.findAll()).thenReturn(excuses);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)))
        ;

        Mockito.verify(this.excuseRepository, times(1)).findAll();
    }
}
