package br.senac.sp.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.senac.sp.security.tokens", "br.senac.sp.filmes"})
public class FilmesLegendaryVideoLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmesLegendaryVideoLibraryApplication.class, args);
	}

}
