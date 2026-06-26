package br.com.obra.calculadora.repository;

import br.com.obra.calculadora.domain.Comodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComodoRepository extends JpaRepository<Comodo, Long> {
}
