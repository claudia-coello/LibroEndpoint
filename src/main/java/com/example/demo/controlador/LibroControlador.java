package com.example.demo.controlador;

import com.example.demo.modelo.Libro;
import com.example.demo.servicio.LibroServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase controlador para usuario,
 * @author Claudia Coello
 * @version 1.0
 * @see Libro
 */
@RestController//marca la clase como controlador rest, devuelve JSON o texto
@RequestMapping("/libros")//es la ruta del controlador

public class LibroControlador {

    @Autowired
    private LibroServicio servicio;

    /**
     * Clase listar todos los libros
     * @return Arreglo de Libro
     */
    @GetMapping//endpoint tipo get
    public List<Libro> listarLibros(){
        return servicio.listarLibros();
    }

    /**
     * Clase para crear o actualizar libro
     * @param libros
     * @return Libro
     */
    @PostMapping("/bulk")
    public List<Libro> guardarLibros(@Valid @RequestBody List<Libro> libros){
        return libros.stream()
                .map(servicio::guardarLibro)
                .collect(Collectors.toList());
    }


    /**
     * Clase para buscar libros segun un id
     * @param id
     * @return Libro
     */
    @GetMapping("/{id}")
    public Libro buscarLibroPorId(@PathVariable Long id){
       return servicio.buscarLibroPorId(id);
    }

    /**
     * Clase para buscar por titulo del libro
     * @param titulo
     * @return Lista de libros(puede ser solo uno)
     */
    @GetMapping("/titulo/{titulo}")
    public List<Libro> buscarLibroPorTitulo(@PathVariable String titulo){
        return servicio.buscarLibroPorTitulo(titulo);
    }

    /**
     * Clase para eliminar un libro por su id
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibroPorId(@PathVariable Long id){
        servicio.eliminarLibroPorId(id);
        return ResponseEntity.noContent().build();
    }


}
