package br.com.obra.calculadora.controller;

import br.com.obra.calculadora.dto.ConcretoRequest;
import br.com.obra.calculadora.dto.ConcretoResponse;
import br.com.obra.calculadora.dto.TijoloRequest;
import br.com.obra.calculadora.dto.TijoloResponse;
import br.com.obra.calculadora.service.CalculadoraService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calculos")
public class CalculoController {

    private final CalculadoraService calculadoraService;

    public CalculoController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @PostMapping("/concreto")
    public ConcretoResponse calcularVolumeConcreto(@RequestBody @Valid ConcretoRequest request) {
        return calculadoraService.calcularVolumeConcreto(request);
    }

    @PostMapping("/tijolos")
    public TijoloResponse calcularQuantidadeTijolos(@RequestBody @Valid TijoloRequest request) {
        return calculadoraService.calcularQuantidadeTijolos(request);
    }
}
