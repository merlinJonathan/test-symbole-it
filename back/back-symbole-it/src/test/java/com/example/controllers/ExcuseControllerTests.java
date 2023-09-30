package com.example.controllers;

import com.example.Model.ExcuseModel;
import com.example.contantes.ApiUrls;
import com.example.dto.ExcuseDTO;
import com.example.repositories.ExcuseFirestoreRepository;
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
    private ExcuseFirestoreRepository excuseFirestoreRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetExcuseById() throws Exception {
        String excuseId = "1";
        ExcuseModel excuseModel = new ExcuseModel(excuseId, "toto", 202L, "piwi");

        Mockito.when(this.excuseFirestoreRepository.getExcuseByIdWithFireStore(excuseId)).thenReturn(excuseModel);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE + "/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['message ']").value(excuseModel.getMessage()))
                .andExpect(jsonPath("$['tag ']").value(excuseModel.getTag()))
                .andExpect(jsonPath("$.http_code").value(excuseModel.getHttpCode()))
        ;

        Mockito.verify(this.excuseFirestoreRepository, times(1)).getExcuseByIdWithFireStore(excuseId);
    }

    @Test
    public void testGetExcuseByIdNotFound() throws Exception {
        String excuseId = "1";

        Mockito.when(this.excuseFirestoreRepository.getExcuseByIdWithFireStore(excuseId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE + "/{id}", excuseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
        ;

        Mockito.verify(this.excuseFirestoreRepository, times(1)).getExcuseByIdWithFireStore(excuseId);
    }

    @Test
    public void testGetAllExcuseWithValues() throws Exception {
        List<ExcuseModel> excuses = Arrays.asList(
                new ExcuseModel(1L, "premier message", 100L, "tag 1"),
                new ExcuseModel(2L, "second message", 200L, "tag 2")
        );

        Mockito.when(this.excuseFirestoreRepository.getAllExcuseWithFireStore()).thenReturn(excuses);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0]['message ']").value(excuses.get(0).getMessage()))
                .andExpect(jsonPath("$[0]['tag ']").value(excuses.get(0).getTag()))
                .andExpect(jsonPath("$[0].http_code").value(excuses.get(0).getHttpCode()))
                .andExpect(jsonPath("$[1]['message ']").value(excuses.get(1).getMessage()))
                .andExpect(jsonPath("$[1]['tag ']").value(excuses.get(1).getTag()))
                .andExpect(jsonPath("$[1].http_code").value(excuses.get(1).getHttpCode()))
        ;

        Mockito.verify(this.excuseFirestoreRepository, times(1)).getAllExcuseWithFireStore();
    }

    @Test
    public void testGetAllExcuseWithoutValues() throws Exception {
        List<ExcuseModel> excuses = new ArrayList<>();

        Mockito.when(this.excuseFirestoreRepository.getAllExcuseWithFireStore()).thenReturn(excuses);

        mockMvc.perform(MockMvcRequestBuilders.get(ApiUrls.EXCUSE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)))
        ;

        Mockito.verify(this.excuseFirestoreRepository, times(1)).getAllExcuseWithFireStore();
    }

    @Test
    public void testCreateExcuse() throws Exception {
        ExcuseModel excuseModel = new ExcuseModel("Test message", 200L, "Test tag");

        String excuseJson = objectMapper.writeValueAsString(excuseModel.toDTO());

        Mockito.when(this.excuseFirestoreRepository.postWithFirestore(Mockito.any(ExcuseDTO.class))).thenReturn(excuseModel);

        mockMvc.perform(MockMvcRequestBuilders.post(ApiUrls.EXCUSE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(excuseJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$['message ']").value(excuseModel.getMessage()))
                .andExpect(jsonPath("$['tag ']").value(excuseModel.getTag()))
                .andExpect(jsonPath("$.http_code").value(excuseModel.getHttpCode()))
        ;

        Mockito.verify(excuseFirestoreRepository, Mockito.times(1)).postWithFirestore(Mockito.any(ExcuseDTO.class));
    }
}
