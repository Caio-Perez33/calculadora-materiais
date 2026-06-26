package br.com.obra.calculadora.service;

import br.com.obra.calculadora.domain.Aresta;
import br.com.obra.calculadora.domain.Orcamento;
import br.com.obra.calculadora.dto.ConcretoRequest;
import br.com.obra.calculadora.dto.ConcretoResponse;
import br.com.obra.calculadora.dto.OrcamentoRequest;
import br.com.obra.calculadora.dto.OrcamentoResponse;
import br.com.obra.calculadora.dto.TijoloRequest;
import br.com.obra.calculadora.dto.TijoloResponse;
import br.com.obra.calculadora.repository.OrcamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrcamentoService {

    private final OrcamentoRepository orcamentoRepository;
    private final CalculadoraService calculadoraService;
    private final ArestaMapper arestaMapper;

    public OrcamentoService(OrcamentoRepository orcamentoRepository,
                            CalculadoraService calculadoraService,
                            ArestaMapper arestaMapper) {
        this.orcamentoRepository = orcamentoRepository;
        this.calculadoraService = calculadoraService;
        this.arestaMapper = arestaMapper;
    }

    @Transactional
    public OrcamentoResponse gerarOrcamento(OrcamentoRequest request) {
        ConcretoResponse concreto = calculadoraService.calcularVolumeConcreto(
                new ConcretoRequest(request.arestas(), request.alturaBaldrame())
        );
        TijoloResponse tijolos = calculadoraService.calcularQuantidadeTijolos(
                new TijoloRequest(
                        request.arestas(),
                        request.alturaTijolo(),
                        request.larguraTijolo(),
                        request.comprimentoTijolo()
                )
        );

        double custoConcreto = concreto.volumeConcretoM3() * request.valorConcretoPorM3();
        double custoTijolos = tijolos.quantidadeTijolos() * request.valorTijolo();
        double custoMateriais = custoConcreto + custoTijolos;
        double valorLucro = custoMateriais * (request.margemLucroPercentual() / 100.0);
        double valorTotal = custoMateriais + valorLucro;

        Orcamento orcamento = new Orcamento();
        orcamento.setNumeroOrcamento(gerarNumeroOrcamento());
        orcamento.setNomeUsuario(request.nomeUsuario());
        orcamento.setDataCriacao(LocalDateTime.now());
        orcamento.setAlturaBaldrame(request.alturaBaldrame());
        orcamento.setAlturaTijolo(request.alturaTijolo());
        orcamento.setLarguraTijolo(request.larguraTijolo());
        orcamento.setComprimentoTijolo(request.comprimentoTijolo());
        orcamento.setValorConcretoPorM3(request.valorConcretoPorM3());
        orcamento.setValorTijolo(request.valorTijolo());
        orcamento.setMargemLucroPercentual(request.margemLucroPercentual());
        orcamento.setQuantidadeArestas(request.arestas().size());
        orcamento.setVolumeConcretoM3(concreto.volumeConcretoM3());
        orcamento.setAreaTotalParedesM2(tijolos.areaTotalParedesM2());
        orcamento.setAreaFaceTijoloM2(tijolos.areaFaceTijoloM2());
        orcamento.setQuantidadeTijolos(tijolos.quantidadeTijolos());
        orcamento.setVolumeAproximadoTijolosM3(tijolos.volumeAproximadoTijolosM3());
        orcamento.setCustoConcreto(arredondar(custoConcreto));
        orcamento.setCustoTijolos(arredondar(custoTijolos));
        orcamento.setCustoMateriais(arredondar(custoMateriais));
        orcamento.setValorLucro(arredondar(valorLucro));
        orcamento.setValorTotal(arredondar(valorTotal));

        List<Aresta> arestas = request.arestas().stream()
                .map(arestaMapper::toEntity)
                .toList();
        orcamento.setArestas(arestas);

        return toResponse(orcamentoRepository.save(orcamento));
    }

    public List<OrcamentoResponse> listarTodos() {
        return orcamentoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public OrcamentoResponse buscarPorNumero(String numeroOrcamento) {
        return orcamentoRepository.findByNumeroOrcamentoIgnoreCase(numeroOrcamento)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Orcamento nao encontrado: " + numeroOrcamento));
    }

    public List<OrcamentoResponse> buscarPorNomeUsuario(String nomeUsuario) {
        return orcamentoRepository.findByNomeUsuarioContainingIgnoreCaseOrderByDataCriacaoDesc(nomeUsuario).stream()
                .map(this::toResponse)
                .toList();
    }

    private OrcamentoResponse toResponse(Orcamento orcamento) {
        return new OrcamentoResponse(
                orcamento.getId(),
                orcamento.getNumeroOrcamento(),
                orcamento.getNomeUsuario(),
                orcamento.getDataCriacao(),
                orcamento.getQuantidadeArestas(),
                orcamento.getVolumeConcretoM3(),
                orcamento.getAreaTotalParedesM2(),
                orcamento.getAreaFaceTijoloM2(),
                orcamento.getQuantidadeTijolos(),
                orcamento.getVolumeAproximadoTijolosM3(),
                orcamento.getCustoConcreto(),
                orcamento.getCustoTijolos(),
                orcamento.getCustoMateriais(),
                orcamento.getValorLucro(),
                orcamento.getValorTotal()
        );
    }

    private String gerarNumeroOrcamento() {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int sufixo = ThreadLocalRandom.current().nextInt(100, 1000);
        return "ORC-" + data + "-" + sufixo;
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
