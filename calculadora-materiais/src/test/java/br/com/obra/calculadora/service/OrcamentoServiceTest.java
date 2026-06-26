package br.com.obra.calculadora.service;

import br.com.obra.calculadora.dto.ArestaRequest;
import br.com.obra.calculadora.dto.OrcamentoRequest;
import br.com.obra.calculadora.dto.OrcamentoResponse;
import br.com.obra.calculadora.dto.VerticeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrcamentoServiceTest {

    @Autowired
    private OrcamentoService orcamentoService;

    @Test
    void deveGerarOrcamentoESalvarNoBanco() {
        OrcamentoRequest request = new OrcamentoRequest(
                "Cliente Teste",
                List.of(arestaPadrao()),
                0.30,
                0.19,
                0.14,
                0.39,
                450.00,
                1.20,
                20.00
        );

        OrcamentoResponse response = orcamentoService.gerarOrcamento(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.numeroOrcamento()).startsWith("ORC-");
        assertThat(response.nomeUsuario()).isEqualTo("Cliente Teste");
        assertThat(response.volumeConcretoM3()).isEqualTo(0.6);
        assertThat(response.quantidadeTijolos()).isGreaterThan(0);
        assertThat(response.valorTotal()).isGreaterThan(response.custoMateriais());
    }

    @Test
    void deveBuscarOrcamentoPorNomeUsuario() {
        OrcamentoRequest request = new OrcamentoRequest(
                "Maria Souza",
                List.of(arestaPadrao()),
                0.30,
                0.19,
                0.14,
                0.39,
                450.00,
                1.20,
                10.00
        );
        orcamentoService.gerarOrcamento(request);

        List<OrcamentoResponse> encontrados = orcamentoService.buscarPorNomeUsuario("Maria");

        assertThat(encontrados).isNotEmpty();
        assertThat(encontrados.get(0).nomeUsuario()).contains("Maria");
    }

    private ArestaRequest arestaPadrao() {
        return new ArestaRequest(
                "a12",
                new VerticeRequest("V1", 0.0, 0.0),
                new VerticeRequest("V2", 10.0, 0.0),
                10.0,
                0.20,
                3.0,
                false,
                0,
                0,
                false,
                0,
                0
        );
    }
}
