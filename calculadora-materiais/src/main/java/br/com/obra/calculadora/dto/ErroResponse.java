package br.com.obra.calculadora.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
        LocalDateTime horario,
        String mensagem,
        List<String> detalhes
) {
}
