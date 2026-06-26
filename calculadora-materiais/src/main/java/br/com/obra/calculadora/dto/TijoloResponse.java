package br.com.obra.calculadora.dto;

public record TijoloResponse(
        int quantidadeArestas,
        double areaTotalParedesM2,
        double areaFaceTijoloM2,
        long quantidadeTijolos,
        double volumeAproximadoTijolosM3
) {
}
