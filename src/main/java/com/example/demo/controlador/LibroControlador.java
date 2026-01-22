package com.example.demo.controlador;

import com.example.demo.modelo.Libro;
import com.example.demo.servicio.LibroServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    private LibroServicio libroServicio;
    private LibroControlador(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
    }
    /**
     * Clase listar todos los libros
     * @return Arreglo de Libro
     */
    @GetMapping//endpoint tipo get
    public ResponseEntity<List<Libro>> listarLibros(){
        return ResponseEntity.ok(libroServicio.listarLibros());
    }

    /**
     * Clase para crear o actualizar libro
     * @param libros
     * @return Libro
     */
    @PostMapping("/guardar")
    public ResponseEntity<List<Libro>> guardarLibros(@Valid @RequestBody List<Libro> libros){
        return ResponseEntity.ok(libros.stream().map(libroServicio::guardarLibro).toList());
    }


    /**
     * Clase para buscar libros segun un id
     * @param id
     * @return Libro
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibroPorId(@PathVariable Long id){
       return ResponseEntity.ok(libroServicio.buscarLibroPorId(id));
    }

    /**
     * Clase para buscar por titulo del libro
     * @param titulo
     * @return Lista de libros(puede ser solo uno)
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Libro>> buscarLibroPorTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(libroServicio.buscarLibroPorTitulo(titulo));
    }

    /**
     * Clase para eliminar un libro por su id
     * @param id
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarLibroPorId(@PathVariable Long id){
        libroServicio.eliminarLibroPorId(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Clase para actualizar un libro por su id
     * @param id
     * @param cambios
     * @return Libro
     */
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Map<String, Object> cambios){
        return ResponseEntity.ok(libroServicio.actualizarLibro(id, cambios));
    }
}
