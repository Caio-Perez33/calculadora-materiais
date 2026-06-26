package br.com.obra.calculadora.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ArestaRequest(
        @NotBlank String codigo,
        @NotNull @Valid VerticeRequest verticeInicial,
        @NotNull @Valid VerticeRequest verticeFinal,
        @Positive double comprimento,
        @Positive double largura,
        @Positive double alturaParede,
        boolean possuiPorta,
        double larguraPorta,
        double alturaPorta,
        boolean possuiJanela,
        double larguraJanela,
        double alturaJanela
) {
    public double areaParedeBruta() {
        return comprimento * alturaParede;
    }

    public double areaAberturas() {
        double area = 0;
        if (possuiPorta) {
            area += larguraPorta * alturaPorta;
        }
        if (possuiJanela) {
            area += larguraJanela * alturaJanela;
        }
        return area;
    }

    public double areaParedeLiquida() {
        return Math.max(0, areaParedeBruta() - areaAberturas());
    }
}
