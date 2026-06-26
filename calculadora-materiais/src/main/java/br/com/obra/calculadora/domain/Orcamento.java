package br.com.obra.calculadora.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numeroOrcamento;

    @NotBlank
    private String nomeUsuario;

    private LocalDateTime dataCriacao;

    @Positive
    private double alturaBaldrame;

    @Positive
    private double alturaTijolo;

    @Positive
    private double larguraTijolo;

    @Positive
    private double comprimentoTijolo;

    @PositiveOrZero
    private double valorConcretoPorM3;

    @PositiveOrZero
    private double valorTijolo;

    @PositiveOrZero
    private double margemLucroPercentual;

    private int quantidadeArestas;
    private double volumeConcretoM3;
    private double areaTotalParedesM2;
    private double areaFaceTijoloM2;
    private long quantidadeTijolos;
    private double volumeAproximadoTijolosM3;
    private double custoConcreto;
    private double custoTijolos;
    private double custoMateriais;
    private double valorLucro;
    private double valorTotal;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "orcamento_aresta",
            joinColumns = @JoinColumn(name = "orcamento_id"),
            inverseJoinColumns = @JoinColumn(name = "aresta_id")
    )
    private List<Aresta> arestas = new ArrayList<>();

    public Orcamento() {
    }

    public Long getId() {
        return id;
    }

    public String getNumeroOrcamento() {
        return numeroOrcamento;
    }

    public void setNumeroOrcamento(String numeroOrcamento) {
        this.numeroOrcamento = numeroOrcamento;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public double getAlturaBaldrame() {
        return alturaBaldrame;
    }

    public void setAlturaBaldrame(double alturaBaldrame) {
        this.alturaBaldrame = alturaBaldrame;
    }

    public double getAlturaTijolo() {
        return alturaTijolo;
    }

    public void setAlturaTijolo(double alturaTijolo) {
        this.alturaTijolo = alturaTijolo;
    }

    public double getLarguraTijolo() {
        return larguraTijolo;
    }

    public void setLarguraTijolo(double larguraTijolo) {
        this.larguraTijolo = larguraTijolo;
    }

    public double getComprimentoTijolo() {
        return comprimentoTijolo;
    }

    public void setComprimentoTijolo(double comprimentoTijolo) {
        this.comprimentoTijolo = comprimentoTijolo;
    }

    public double getValorConcretoPorM3() {
        return valorConcretoPorM3;
    }

    public void setValorConcretoPorM3(double valorConcretoPorM3) {
        this.valorConcretoPorM3 = valorConcretoPorM3;
    }

    public double getValorTijolo() {
        return valorTijolo;
    }

    public void setValorTijolo(double valorTijolo) {
        this.valorTijolo = valorTijolo;
    }

    public double getMargemLucroPercentual() {
        return margemLucroPercentual;
    }

    public void setMargemLucroPercentual(double margemLucroPercentual) {
        this.margemLucroPercentual = margemLucroPercentual;
    }

    public int getQuantidadeArestas() {
        return quantidadeArestas;
    }

    public void setQuantidadeArestas(int quantidadeArestas) {
        this.quantidadeArestas = quantidadeArestas;
    }

    public double getVolumeConcretoM3() {
        return volumeConcretoM3;
    }

    public void setVolumeConcretoM3(double volumeConcretoM3) {
        this.volumeConcretoM3 = volumeConcretoM3;
    }

    public double getAreaTotalParedesM2() {
        return areaTotalParedesM2;
    }

    public void setAreaTotalParedesM2(double areaTotalParedesM2) {
        this.areaTotalParedesM2 = areaTotalParedesM2;
    }

    public double getAreaFaceTijoloM2() {
        return areaFaceTijoloM2;
    }

    public void setAreaFaceTijoloM2(double areaFaceTijoloM2) {
        this.areaFaceTijoloM2 = areaFaceTijoloM2;
    }

    public long getQuantidadeTijolos() {
        return quantidadeTijolos;
    }

    public void setQuantidadeTijolos(long quantidadeTijolos) {
        this.quantidadeTijolos = quantidadeTijolos;
    }

    public double getVolumeAproximadoTijolosM3() {
        return volumeAproximadoTijolosM3;
    }

    public void setVolumeAproximadoTijolosM3(double volumeAproximadoTijolosM3) {
        this.volumeAproximadoTijolosM3 = volumeAproximadoTijolosM3;
    }

    public double getCustoConcreto() {
        return custoConcreto;
    }

    public void setCustoConcreto(double custoConcreto) {
        this.custoConcreto = custoConcreto;
    }

    public double getCustoTijolos() {
        return custoTijolos;
    }

    public void setCustoTijolos(double custoTijolos) {
        this.custoTijolos = custoTijolos;
    }

    public double getCustoMateriais() {
        return custoMateriais;
    }

    public void setCustoMateriais(double custoMateriais) {
        this.custoMateriais = custoMateriais;
    }

    public double getValorLucro() {
        return valorLucro;
    }

    public void setValorLucro(double valorLucro) {
        this.valorLucro = valorLucro;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }
}
