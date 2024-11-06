package com.rumana.casamusical;

import com.rumana.casamusical.principal.Principal;
import com.rumana.casamusical.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasamusicalApplication implements CommandLineRunner {

	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(CasamusicalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	Principal principal = new Principal(repositorio);
		principal.muestraElMenu();

	}
}9

