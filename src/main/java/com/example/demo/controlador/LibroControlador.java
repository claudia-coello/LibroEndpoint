package com.example.demo.controlador;

import com.example.demo.modelo.Libro;
import com.example.demo.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Clase controlador para usuario,
 * @author Claudia Coello
 * @version 1.0
 * @see Libro
 */
@RestController//marca la clase como controlador rest, devuelve JSON o texto
@RequestMapping("/libros")//es la ruta del controlador

public class LibroControlador {

    /**
     * Clase para endpoint get Libros
     * @return Arrglo de Libros
     */
    /*@Autowired
    @GetMapping//endpoint tipo get
    public String listarLibros(){
        return "ENdpoint";
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro){
        return LibroServicio.guardar(libro);
    }*/
}
