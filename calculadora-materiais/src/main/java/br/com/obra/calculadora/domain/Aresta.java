package br.com.obra.calculadora.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Aresta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vertice verticeInicial;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vertice verticeFinal;

    @Positive
    private double comprimento;

    @Positive
    private double largura;

    @Positive
    private double alturaParede;

    private boolean possuiPorta;
    private double larguraPorta;
    private double alturaPorta;

    private boolean possuiJanela;
    private double larguraJanela;
    private double alturaJanela;

    protected Aresta() {
    }

    public Aresta(String codigo, Vertice verticeInicial, Vertice verticeFinal, double comprimento,
                  double largura, double alturaParede, boolean possuiPorta, double larguraPorta,
                  double alturaPorta, boolean possuiJanela, double larguraJanela, double alturaJanela) {
        this.codigo = codigo;
        this.verticeInicial = verticeInicial;
        this.verticeFinal = verticeFinal;
        this.comprimento = comprimento;
        this.largura = largura;
        this.alturaParede = alturaParede;
        this.possuiPorta = possuiPorta;
        this.larguraPorta = larguraPorta;
        this.alturaPorta = alturaPorta;
        this.possuiJanela = possuiJanela;
        this.larguraJanela = larguraJanela;
        this.alturaJanela = alturaJanela;
    }

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

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Vertice getVerticeInicial() {
        return verticeInicial;
    }

    public void setVerticeInicial(Vertice verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    public Vertice getVerticeFinal() {
        return verticeFinal;
    }

    public void setVerticeFinal(Vertice verticeFinal) {
        this.verticeFinal = verticeFinal;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAlturaParede() {
        return alturaParede;
    }

    public void setAlturaParede(double alturaParede) {
        this.alturaParede = alturaParede;
    }

    public boolean isPossuiPorta() {
        return possuiPorta;
    }

    public void setPossuiPorta(boolean possuiPorta) {
        this.possuiPorta = possuiPorta;
    }

    public double getLarguraPorta() {
        return larguraPorta;
    }

    public void setLarguraPorta(double larguraPorta) {
        this.larguraPorta = larguraPorta;
    }

    public double getAlturaPorta() {
        return alturaPorta;
    }

    public void setAlturaPorta(double alturaPorta) {
        this.alturaPorta = alturaPorta;
    }

    public boolean isPossuiJanela() {
        return possuiJanela;
    }

    public void setPossuiJanela(boolean possuiJanela) {
        this.possuiJanela = possuiJanela;
    }

    public double getLarguraJanela() {
        return larguraJanela;
    }

    public void setLarguraJanela(double larguraJanela) {
        this.larguraJanela = larguraJanela;
    }

    public double getAlturaJanela() {
        return alturaJanela;
    }

    public void setAlturaJanela(double alturaJanela) {
        this.alturaJanela = alturaJanela;
    }
}
