package com.miniproyecto.catalogolibros;

import com.miniproyecto.catalogolibros.principal.Principal;
import com.miniproyecto.catalogolibros.repository.AutorRepository;
import com.miniproyecto.catalogolibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogolibrosApplication implements CommandLineRunner {

	private LibroRepository repositorioLibro;
	private AutorRepository repositorioAutor;

	@Autowired
	public CatalogolibrosApplication(LibroRepository repositorioLibro, AutorRepository repositorioAutor) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioAutor = repositorioAutor;
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogolibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioLibro, repositorioAutor);
		principal.mostrarElMenu();
	}
}
