package br.com.obra.calculadora.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Positive
    private double largura;

    @Positive
    private double comprimento;

    @Positive
    private double altura;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "comodo_aresta",
            joinColumns = @JoinColumn(name = "comodo_id"),
            inverseJoinColumns = @JoinColumn(name = "aresta_id")
    )
    private List<Aresta> paredes = new ArrayList<>();

    protected Comodo() {
    }

    public Comodo(String nome, double largura, double comprimento, double altura) {
        this.nome = nome;
        this.largura = largura;
        this.comprimento = comprimento;
        this.altura = altura;
    }

    public double areaPiso() {
        return largura * comprimento;
    }

    public double volumeInterno() {
        return areaPiso() * altura;
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

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public List<Aresta> getParedes() {
        return paredes;
    }

    public void setParedes(List<Aresta> paredes) {
        this.paredes = paredes;
    }
}
