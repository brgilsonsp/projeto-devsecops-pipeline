package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResultController.class)
class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void resultadoEndpointDeveRetornarJsonEsperado() throws Exception {
        mockMvc.perform(get("/result"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sumario").value("Trabalho disciplina DevSecOps. Pipeline"))
                .andExpect(jsonPath("$.aluno").value("Gilson Araujo de Souza"))
                .andExpect(jsonPath("$.disciplina").value("DevSecOps"))
                .andExpect(jsonPath("$.professor").value("Fabiano da Silva Carneiro"))
                .andExpect(jsonPath("$.detalhe").exists());
    }
}