package com.example.demo.repositorio;

import com.example.demo.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Clase repositorio para Libro, comunicado con la bd
 * @author Claudia Coello
 * @version 1.1
 * @see LibroRepositorio
 */

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    //obtenemos "SQL" automaticamente
}
