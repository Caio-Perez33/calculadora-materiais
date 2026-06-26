package br.com.obra.calculadora.dto;

import jakarta.validation.constraints.NotBlank;

public record VerticeRequest(
        @NotBlank String nome,
        Double coordenadaX,
        Double coordenadaY
) {
}
