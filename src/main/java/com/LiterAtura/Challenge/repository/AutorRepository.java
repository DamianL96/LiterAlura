package com.LiterAtura.Challenge.repository;

import com.LiterAtura.Challenge.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

  Optional<Autor> findByNombreIgnoreCaseContaining(String nombre);
}
