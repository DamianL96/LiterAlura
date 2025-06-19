package com.LiterAtura.Challenge.repository;

import com.LiterAtura.Challenge.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
