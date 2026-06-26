package br.com.obra.calculadora.dto;

import java.time.LocalDateTime;

public record OrcamentoResponse(
        Long id,
        String numeroOrcamento,
        String nomeUsuario,
        LocalDateTime dataCriacao,
        int quantidadeArestas,
        double volumeConcretoM3,
        double areaTotalParedesM2,
        double areaFaceTijoloM2,
        long quantidadeTijolos,
        double volumeAproximadoTijolosM3,
        double custoConcreto,
        double custoTijolos,
        double custoMateriais,
        double valorLucro,
        double valorTotal
) {
    public Long getId() { return id; }
    public String getNumeroOrcamento() { return numeroOrcamento; }
    public String getNomeUsuario() { return nomeUsuario; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public int getQuantidadeArestas() { return quantidadeArestas; }
    public double getVolumeConcretoM3() { return volumeConcretoM3; }
    public double getAreaTotalParedesM2() { return areaTotalParedesM2; }
    public double getAreaFaceTijoloM2() { return areaFaceTijoloM2; }
    public long getQuantidadeTijolos() { return quantidadeTijolos; }
    public double getVolumeAproximadoTijolosM3() { return volumeAproximadoTijolosM3; }
    public double getCustoConcreto() { return custoConcreto; }
    public double getCustoTijolos() { return custoTijolos; }
    public double getCustoMateriais() { return custoMateriais; }
    public double getValorLucro() { return valorLucro; }
    public double getValorTotal() { return valorTotal; }
}
