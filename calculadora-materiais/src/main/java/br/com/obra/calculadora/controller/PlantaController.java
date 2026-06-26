package br.com.obra.calculadora.controller;

import br.com.obra.calculadora.domain.Aresta;
import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.service.PlantaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/planta")
public class PlantaController {

    private final PlantaService plantaService;

    public PlantaController(PlantaService plantaService) {
        this.plantaService = plantaService;
    }

    @PostMapping("/arestas")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Aresta> salvarArestas(@RequestBody @NotEmpty List<@Valid ArestaRequest> arestas) {
        return plantaService.salvarArestas(arestas);
    }

    @GetMapping("/arestas")
    public List<Aresta> listarArestas() {
        return plantaService.listarArestas();
    }
}
