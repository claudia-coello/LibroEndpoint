package com.example.demo.excepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para manejar excepciones.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Clase para manejar recursos no encontrados con status 400
     * @param r
     * @return
     */
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<String> recursoNoEncontradoHandler(LibroNoEncontradoException r){
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

    /**
     *Clase para manejar excepciones generales
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("mensajes", errores);

        return ResponseEntity.badRequest().body(response);
    }
}
