package com.example.demo.Servicio;

import com.example.demo.Modelo.Libro;
import com.example.demo.Repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de servisio para Usuario, logica, validaciones y reglas de negocio.
 * @author Claudia Coello
 * @version 1.0
 */
@Service
public class LibroServicio {
    @Autowired
    private LibroRepositorio repo;
    public static Libro guardar(Libro libro) {
        return libro;
    }
}
