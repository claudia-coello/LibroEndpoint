package com.example.demo.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

/**
 * Clase para Libro, entidad principal, constructor, getters y setters
 * @author Claudia Coello
 * @version 1.0
 */

@Entity//la clase es una tabla
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrement
    private Long id;

    @NotBlank(message = "El titulo no puede estar vacío")
    @Size(max = 300)
    private String titulo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPublicacion;

    @NotBlank
    private String autor="Desconocido";

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock=0;

    public Libro(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
