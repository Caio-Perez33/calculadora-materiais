package br.com.obra.calculadora.service;

import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.dto.ConcretoRequest;
import br.com.obra.calculadora.dto.ConcretoResponse;
import br.com.obra.calculadora.dto.TijoloRequest;
import br.com.obra.calculadora.dto.TijoloResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public ConcretoResponse calcularVolumeConcreto(ConcretoRequest request) {
        double volume = request.arestas().stream()
                .mapToDouble(aresta -> aresta.largura() * request.alturaBaldrame() * aresta.comprimento())
                .sum();

        return new ConcretoResponse(request.arestas().size(), arredondar(volume));
    }

    public TijoloResponse calcularQuantidadeTijolos(TijoloRequest request) {
        double areaTotalParedes = request.arestas().stream()
                .mapToDouble(ArestaRequest::areaParedeLiquida)
                .sum();

        double areaFaceTijolo = request.alturaTijolo() * request.comprimentoTijolo();
        long quantidadeTijolos = (long) Math.ceil(areaTotalParedes / areaFaceTijolo);
        double volumeAproximado = quantidadeTijolos
                * request.alturaTijolo()
                * request.larguraTijolo()
                * request.comprimentoTijolo();

        return new TijoloResponse(
                request.arestas().size(),
                arredondar(areaTotalParedes),
                arredondar(areaFaceTijolo),
                quantidadeTijolos,
                arredondar(volumeAproximado)
        );
    }

    private double arredondar(double valor) {
        return Math.round(valor * 1000.0) / 1000.0;
    }
}
