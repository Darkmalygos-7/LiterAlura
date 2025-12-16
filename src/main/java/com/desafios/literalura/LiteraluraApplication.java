package com.desafios.literalura;

import com.desafios.literalura.principal.Principal;
import com.desafios.literalura.repository.AutorRepository;
import com.desafios.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private final LibroRepository repository;
    private final AutorRepository autorRepository;

    public LiteraluraApplication(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repository,autorRepository);
        principal.muestraMenu();
    }
}
