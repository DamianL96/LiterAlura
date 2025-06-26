package com.LiterAtura.Challenge;

import com.LiterAtura.Challenge.main.Menu;
import com.LiterAtura.Challenge.models.RLibro;
import com.LiterAtura.Challenge.models.RRespuestaApi;
import com.LiterAtura.Challenge.repository.AutorRepository;
import com.LiterAtura.Challenge.repository.LibroRepository;
import com.LiterAtura.Challenge.services.APIConnection;
import com.LiterAtura.Challenge.services.TransformJsonToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Autowired
	private Menu menu;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menu.run();
	}
}
