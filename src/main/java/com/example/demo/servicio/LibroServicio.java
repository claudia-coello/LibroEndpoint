package com.example.demo.servicio;

import com.example.demo.excepciones.LibroNoEncontradoException;
import com.example.demo.excepciones.StockInvalidoException;
import com.example.demo.modelo.Libro;
import com.example.demo.repositorio.LibroRepositorio;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * Clase de servicio para Usuario, logica, validaciones y reglas de negocio.
 * @author Claudia Coello
 * @version 1.0
 * @see LibroRepositorio
 */
@Service
public class LibroServicio {
    private final LibroRepositorio repo;
    private final ObjectMapper objectMapper;

    public LibroServicio(LibroRepositorio repo, ObjectMapper ob){
        this.repo=repo;
        this.objectMapper=ob;
    }

    /**
     * Clase que guardar libro/s
     * @param libro
     */
    public void crearLibros(Libro libro) {
        if (libro.getStock()<0) throw new StockInvalidoException("El stock no puede ser un numero negativo.");
        if (libro.getId() != null) throw new IllegalArgumentException("No se debe enviar el id al crear un libro");
        repo.save(libro);
    }

    /**
     * Clase que devuelve todos los libros encontrados
     * @return Lista de tipo libro
     */
    public List<Libro> listarLibros(){
        List<Libro> librosEncontrados = repo.findAll();
        if (librosEncontrados.isEmpty()) throw new LibroNoEncontradoException("No se encontró ningún libro.");
        return librosEncontrados;
    }

    /**
     * Clase que devuelve un libro si coincide con algun id
     * @param id
     * @return Libro
     */
    public Libro buscarLibroPorId(Long id){
        return repo.findById(id).orElseThrow(() -> new LibroNoEncontradoException("Ningún libro encontrado con ese id"));
    }

    /**
     * Clase que devuelve un libro si coincide con algún título
     * @param titulo
     * @return Libro
     */
    public List<Libro> buscarLibroPorTitulo(String titulo){
        List<Libro> librosEncontrados = repo.findLibroByTitulo(titulo);
        if (librosEncontrados.isEmpty()) throw new LibroNoEncontradoException("No se encontró ningún libro con ese titulo");
        return librosEncontrados;
    }

    /**
     * Clase para eliminar un libro por su id
     * @param id
     */
    public void eliminarLibroPorId(Long id){
        if (!repo.existsById(id)) throw new LibroNoEncontradoException("Libro no encontrado con ese id");
        repo.deleteById(id);
    }

    /**
     * Clase para actualizar un libro
     * @param id
     * @param cambios
     */
    public void actualizarLibro(Long id, Map<String, Object> cambios){
        if (cambios.containsKey("id")) throw new IllegalArgumentException("No se permite modificar el id.");
        Libro libro = repo.findById(id).orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado con ese id."));
        if (libro.getStock() < 0) throw new StockInvalidoException("El stock no puede ser negativo.");
        objectMapper.updateValue(libro, cambios);
        repo.save(libro);
    }


}
