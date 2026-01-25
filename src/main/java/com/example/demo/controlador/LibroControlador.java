package com.example.demo.controlador;

import com.example.demo.modelo.Libro;
import com.example.demo.servicio.LibroServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Clase controlador para usuario,
 * @author Claudia Coello
 * @version 1.0
 * @see LibroServicio
 */
@RestController//marca la clase como controlador rest, devuelve JSON o texto
@RequestMapping("/libros")//es la ruta del controlador

public class LibroControlador {

    private final LibroServicio libroServicio;
    public LibroControlador(LibroServicio libroServicio){
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
    @PostMapping("/crear")
    public ResponseEntity<Map<String, String>> crearLibros(@RequestBody List<@Valid Libro> libros){
        libros.forEach(libroServicio::crearLibros);
        return ResponseEntity.ok(Map.of("mensaje", "Libros creados correctamente"));
    }

    /**
     * Clase para buscar libros según un id
     * @param id
     * @return Libro
     */
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarLibroPorId(@PathVariable Long id){
       return ResponseEntity.ok(libroServicio.buscarLibroPorId(id));
    }

    /**
     * Clase para buscar por título del libro
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
    public ResponseEntity<Map<String, String>> eliminarLibroPorId(@PathVariable Long id){
        libroServicio.eliminarLibroPorId(id);
        return ResponseEntity.ok(Map.of("mensaje", "Libro eliminado correctamente"));
    }


    /**
     * Clase para actualizar un libro por su id
     * @param id
     * @param cambios
     * @return Libro
     */
    @PatchMapping("/actualizar/{id}")
    public ResponseEntity<Map<String, String>> actualizarLibro(@PathVariable Long id, @RequestBody Map<String, Object> cambios){
        libroServicio.actualizarLibro(id, cambios);
        return ResponseEntity.ok(Map.of("mensaje", "Libro actualizado correctamente"));
    }

}
