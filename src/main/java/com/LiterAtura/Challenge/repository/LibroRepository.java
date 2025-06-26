package com.LiterAtura.Challenge.repository;

import com.LiterAtura.Challenge.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

  List<Libro> findByIdiomas(String idioma);

  List<Libro> findAll();

  Long countByIdiomas(String idioma);

  @Query("SELECT l FROM Libro l ORDER BY l.descargas DESC LIMIT 10")
  List<Libro> top10MasDescargados();
}
