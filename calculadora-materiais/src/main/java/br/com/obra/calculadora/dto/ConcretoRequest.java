package br.com.obra.calculadora.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ConcretoRequest(
        @NotEmpty List<@Valid ArestaRequest> arestas,
        @Positive double alturaBaldrame
) {
}
