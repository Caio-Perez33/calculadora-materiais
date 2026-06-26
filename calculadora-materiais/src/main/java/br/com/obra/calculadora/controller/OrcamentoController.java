package br.com.obra.calculadora.controller;

import br.com.obra.calculadora.dto.OrcamentoRequest;
import br.com.obra.calculadora.dto.OrcamentoResponse;
import br.com.obra.calculadora.service.OrcamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrcamentoResponse gerarOrcamento(@RequestBody @Valid OrcamentoRequest request) {
        return orcamentoService.gerarOrcamento(request);
    }

    @GetMapping
    public List<OrcamentoResponse> listarOuBuscarPorNome(@RequestParam(required = false) String nomeUsuario) {
        if (nomeUsuario == null || nomeUsuario.isBlank()) {
            return orcamentoService.listarTodos();
        }
        return orcamentoService.buscarPorNomeUsuario(nomeUsuario);
    }

    @GetMapping("/{numeroOrcamento}")
    public OrcamentoResponse buscarPorNumero(@PathVariable String numeroOrcamento) {
        return orcamentoService.buscarPorNumero(numeroOrcamento);
    }
}
