package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ResultController {

    @GetMapping("/result")
    public Map<String, String> resultado() {
        return Map.of(
            "sumario", "Trabalho disciplina DevSecOps. Pipeline",
            "aluno", "Gilson Araujo de Souza",
            "disciplina", "DevSecOps",
            "professor", "Fabiano da Silva Carneiro",
            "detalhe", "Este trabalho apresenta um pipeline DevSecOps, focando na integração contínua, entrega contínua e práticas de segurança automatizadas durante o ciclo de vida do desenvolvimento de software"
        );
    }
}
