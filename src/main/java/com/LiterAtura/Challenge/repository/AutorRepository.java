package com.LiterAtura.Challenge.repository;

import com.LiterAtura.Challenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

  List<Autor> findAll();

  Optional<Autor> findByNombreIgnoreCaseContaining(String nombre);

  @Query("SELECT a FROM Autor a WHERE a.nacimiento < :anio AND a.muerte > :anio")
  List<Autor> autorPorAnio(Integer anio);
}
