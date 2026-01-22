package com.example.demo.servicio;

import com.example.demo.excepciones.RecursoNoEncontradoException;
import com.example.demo.excepciones.StockInvalidoException;
import com.example.demo.modelo.Libro;
import com.example.demo.repositorio.LibroRepositorio;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase de servisio para Usuario, logica, validaciones y reglas de negocio.
 * @author Claudia Coello
 * @version 1.0
 * @see com.example.demo.controlador.LibroControlador
 * @see LibroRepositorio
 */
@Service
public class LibroServicio {
    private LibroRepositorio repo;
    private ObjectMapper objectMapper;

    public LibroServicio(LibroRepositorio repo, ObjectMapper ob){
        this.repo=repo;
        this.objectMapper=ob;
    }

    /**
     * Clase que guardar libro/s
     * @param libro
     * @return Libro
     */
    public Libro guardarLibro(Libro libro) {
        if (libro.getStock()<0) throw new StockInvalidoException("El stock no puede ser un numero negativo.");
        return repo.save(libro);
    }

    /**
     * Clase que devuelve todos los libros encontrados
     * @return Lista de tipo libro
     */
    public List<Libro> listarLibros(){
        return repo.findAll();
    }

    /**
     * Clase que devuelve un libro si coincide con algun id
     * @param id
     * @return Libro
     */
    public Libro buscarLibroPorId(Long id){
        return repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado con ese id"));
    }

    /**
     * Clase que devuelve un libro si coincide con algun titulo
     * @param titulo
     * @return Libro
     */
    public List<Libro> buscarLibroPorTitulo(String titulo){
        List<Libro> librosEncontrados = repo.findLibroByTitulo(titulo);

        if (librosEncontrados.isEmpty()) throw new RecursoNoEncontradoException("No se encontro ningun libro con ese titulo");
        return librosEncontrados;
    }

    /**
     * Clase para eliminar un libro por su id
     * @param id
     */
    public void eliminarLibroPorId(Long id){
        if (!repo.existsById(id)) throw new RecursoNoEncontradoException("Libro no encontrado con ese id");
        repo.deleteById(id);
    }

    /**
     * Clase para actualizar un libro
     * @param id
     * @param cambios
     * @return Libro
     */
    public Libro actualizarLibro(Long id, Map<String, Object> cambios){
        Libro libro = repo.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Libro no encontrado con ese id"));
        objectMapper.updateValue(libro, cambios);
        return repo.save(libro);
    }

}
