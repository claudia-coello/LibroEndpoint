package com.example.demo;

import com.example.demo.modelo.Libro;
import com.example.demo.repositorio.LibroRepositorio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(LibroRepositorio repo){
		return args -> {
			Libro libro = new Libro();

			libro.setAutor("Claudia Coello");
			libro.setStock(10);
			libro.setTitulo("El ");
			libro.setFechaPublicacion(LocalDate.of(1900,11,19));

			repo.save(libro);
			System.out.println("Libros registrados: " + repo.count());//cuenta cuentos registros existen
		};
	}

}
