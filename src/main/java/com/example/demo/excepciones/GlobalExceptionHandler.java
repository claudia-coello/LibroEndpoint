package com.example.demo.excepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase para manejar excepciones especificas: RecursoNoEncontradoException y StockInvalidoException
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Clase para manejar recursos no encontrados con status 400
     * @param r
     * @return
     */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> recursoNoEncontradoHandler(RecursoNoEncontradoException r){
        return ResponseEntity.status(400).body(r.getMessage());
    }

    /**
     * Clase para manejar stocks negativos con status 400
     * @param s
     * @return
     */
    @ExceptionHandler(StockInvalidoException.class)
    public ResponseEntity<String> stockInvalidoHandler(StockInvalidoException s){
        return ResponseEntity.status(400).body(s.getMessage());
    }
}
