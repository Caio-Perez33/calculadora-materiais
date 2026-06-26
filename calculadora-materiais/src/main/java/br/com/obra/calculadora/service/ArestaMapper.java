package br.com.obra.calculadora.service;

import br.com.obra.calculadora.domain.Aresta;
import br.com.obra.calculadora.domain.Vertice;
import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.dto.VerticeRequest;
import org.springframework.stereotype.Component;

@Component
public class ArestaMapper {

    public Aresta toEntity(ArestaRequest request) {
        return new Aresta(
                request.codigo(),
                toEntity(request.verticeInicial()),
                toEntity(request.verticeFinal()),
                request.comprimento(),
                request.largura(),
                request.alturaParede(),
                request.possuiPorta(),
                request.larguraPorta(),
                request.alturaPorta(),
                request.possuiJanela(),
                request.larguraJanela(),
                request.alturaJanela()
        );
    }

    private Vertice toEntity(VerticeRequest request) {
        return new Vertice(request.nome(), request.coordenadaX(), request.coordenadaY());
    }
}
