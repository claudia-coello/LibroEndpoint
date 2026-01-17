package com.example.demo.Repositorio;

import com.example.demo.Modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Clase repositorio para Libro, comunicado con la bd
 * @author Claudia Coello
 * @version 1.1
 * @see LibroRepositorio
 */

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    /*@Override
    List<Libro> findByNombre(String nombre);*/
}
