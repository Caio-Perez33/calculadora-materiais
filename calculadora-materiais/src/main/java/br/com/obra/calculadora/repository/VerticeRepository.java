package br.com.obra.calculadora.repository;

import br.com.obra.calculadora.domain.Vertice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerticeRepository extends JpaRepository<Vertice, Long> {
}
