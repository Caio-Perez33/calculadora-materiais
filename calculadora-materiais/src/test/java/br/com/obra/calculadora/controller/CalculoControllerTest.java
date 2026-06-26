package br.com.obra.calculadora.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CalculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCalcularConcretoViaEndpointRest() throws Exception {
        String json = """
                {
                  "alturaBaldrame": 0.30,
                  "arestas": [
                    {
                      "codigo": "a12",
                      "verticeInicial": { "nome": "V1", "coordenadaX": 0, "coordenadaY": 0 },
                      "verticeFinal": { "nome": "V2", "coordenadaX": 10, "coordenadaY": 0 },
                      "comprimento": 10.0,
                      "largura": 0.20,
                      "alturaParede": 3.0,
                      "possuiPorta": false,
                      "larguraPorta": 0,
                      "alturaPorta": 0,
                      "possuiJanela": false,
                      "larguraJanela": 0,
                      "alturaJanela": 0
                    },
                    {
                      "codigo": "a23",
                      "verticeInicial": { "nome": "V2", "coordenadaX": 10, "coordenadaY": 0 },
                      "verticeFinal": { "nome": "V3", "coordenadaX": 15, "coordenadaY": 0 },
                      "comprimento": 5.0,
                      "largura": 0.15,
                      "alturaParede": 3.0,
                      "possuiPorta": false,
                      "larguraPorta": 0,
                      "alturaPorta": 0,
                      "possuiJanela": false,
                      "larguraJanela": 0,
                      "alturaJanela": 0
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/calculos/concreto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeArestas").value(2))
                .andExpect(jsonPath("$.volumeConcretoM3").value(0.825));
    }

    @Test
    void deveCalcularTijolosViaEndpointRest() throws Exception {
        String json = """
                {
                  "alturaTijolo": 0.19,
                  "larguraTijolo": 0.14,
                  "comprimentoTijolo": 0.39,
                  "arestas": [
                    {
                      "codigo": "a12",
                      "verticeInicial": { "nome": "V1", "coordenadaX": 0, "coordenadaY": 0 },
                      "verticeFinal": { "nome": "V2", "coordenadaX": 4, "coordenadaY": 0 },
                      "comprimento": 4.0,
                      "largura": 0.15,
                      "alturaParede": 3.0,
                      "possuiPorta": true,
                      "larguraPorta": 0.80,
                      "alturaPorta": 2.10,
                      "possuiJanela": true,
                      "larguraJanela": 1.20,
                      "alturaJanela": 1.00
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/calculos/tijolos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantidadeArestas").value(1))
                .andExpect(jsonPath("$.areaTotalParedesM2").value(9.12))
                .andExpect(jsonPath("$.quantidadeTijolos").value(124));
    }
}
