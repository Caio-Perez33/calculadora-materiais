package br.com.obra.calculadora.service;

import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.dto.ConcretoRequest;
import br.com.obra.calculadora.dto.ConcretoResponse;
import br.com.obra.calculadora.dto.TijoloRequest;
import br.com.obra.calculadora.dto.TijoloResponse;
import br.com.obra.calculadora.dto.VerticeRequest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraServiceTest {

    private final CalculadoraService service = new CalculadoraService();

    @Test
    void deveCalcularVolumeConcretoDaFundacao() {
        ArestaRequest parede1 = aresta("a12", 10.0, 0.20, 3.0, false, false);
        ArestaRequest parede2 = aresta("a23", 5.0, 0.15, 3.0, false, false);
        ConcretoRequest request = new ConcretoRequest(List.of(parede1, parede2), 0.30);

        ConcretoResponse response = service.calcularVolumeConcreto(request);

        assertEquals(2, response.quantidadeArestas());
        assertEquals(0.825, response.volumeConcretoM3());
    }

    @Test
    void deveCalcularQuantidadeDeTijolosDescontandoPortaEJanela() {
        ArestaRequest parede = aresta("a12", 4.0, 0.15, 3.0, true, true);
        TijoloRequest request = new TijoloRequest(List.of(parede), 0.19, 0.14, 0.39);

        TijoloResponse response = service.calcularQuantidadeTijolos(request);

        assertEquals(1, response.quantidadeArestas());
        assertEquals(9.12, response.areaTotalParedesM2());
        assertEquals(0.074, response.areaFaceTijoloM2());
        assertEquals(124, response.quantidadeTijolos());
    }

    private ArestaRequest aresta(String codigo, double comprimento, double largura,
                                 double alturaParede, boolean porta, boolean janela) {
        return new ArestaRequest(
                codigo,
                new VerticeRequest("V1", 0.0, 0.0),
                new VerticeRequest("V2", comprimento, 0.0),
                comprimento,
                largura,
                alturaParede,
                porta,
                porta ? 0.80 : 0.0,
                porta ? 2.10 : 0.0,
                janela,
                janela ? 1.20 : 0.0,
                janela ? 1.00 : 0.0
        );
    }
}
