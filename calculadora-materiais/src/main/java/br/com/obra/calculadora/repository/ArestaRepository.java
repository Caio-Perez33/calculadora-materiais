package br.com.obra.calculadora.repository;

import br.com.obra.calculadora.domain.Aresta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArestaRepository extends JpaRepository<Aresta, Long> {
}
