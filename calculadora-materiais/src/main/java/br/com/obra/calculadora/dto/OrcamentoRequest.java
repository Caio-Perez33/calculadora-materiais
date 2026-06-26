package br.com.obra.calculadora.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record OrcamentoRequest(
        @NotBlank String nomeUsuario,
        @NotEmpty List<@Valid ArestaRequest> arestas,
        @Positive double alturaBaldrame,
        @Positive double alturaTijolo,
        @Positive double larguraTijolo,
        @Positive double comprimentoTijolo,
        @PositiveOrZero double valorConcretoPorM3,
        @PositiveOrZero double valorTijolo,
        @PositiveOrZero double margemLucroPercentual
) {
}
