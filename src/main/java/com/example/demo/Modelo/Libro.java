package com.example.demo.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Clase para Libro, entidad principal
 * @author Claudia Coello
 * @Version 1.0
 */

@Entity//la clase es una tabla
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremental
    private Long id;

    @NotNull
    @Size
    private String titulo;

    private Date fechaPublicacion;

    private String autor;

    @Min(0)
    @Max(1000000000)
    private int stock;
}
