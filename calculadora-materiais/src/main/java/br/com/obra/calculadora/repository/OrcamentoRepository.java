package br.com.obra.calculadora.repository;

import br.com.obra.calculadora.domain.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    Optional<Orcamento> findByNumeroOrcamentoIgnoreCase(String numeroOrcamento);

    List<Orcamento> findByNomeUsuarioContainingIgnoreCaseOrderByDataCriacaoDesc(String nomeUsuario);
}
