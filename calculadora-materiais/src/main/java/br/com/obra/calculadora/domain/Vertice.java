package br.com.obra.calculadora.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vertice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private Double coordenadaX;
    private Double coordenadaY;

    protected Vertice() {
    }

    public Vertice(String nome, Double coordenadaX, Double coordenadaY) {
        this.nome = nome;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }
}
