package br.com.obra.calculadora.config;

import br.com.obra.calculadora.dto.ErroResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse tratarValidacao(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return new ErroResponse(LocalDateTime.now(), "Erro de validacao", detalhes);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResponse tratarConstraint(ConstraintViolationException ex) {
        List<String> detalhes = ex.getConstraintViolations().stream()
                .map(violacao -> violacao.getPropertyPath() + ": " + violacao.getMessage())
                .toList();

        return new ErroResponse(LocalDateTime.now(), "Erro de validacao", detalhes);
    }
}
