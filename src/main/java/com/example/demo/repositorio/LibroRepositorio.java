package com.example.demo.repositorio;

import com.example.demo.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Clase repositorio para Libro, comunicado con la bd
 * @author Claudia Coello
 * @version 1.1
 * @see com.example.demo.servicio.LibroServicio
 */

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    List<Libro> findLibroByTitulo(String titulo);
}
