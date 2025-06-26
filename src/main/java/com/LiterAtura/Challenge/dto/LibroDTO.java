package com.LiterAtura.Challenge.dto;

import com.LiterAtura.Challenge.models.Autor;
import jakarta.persistence.ManyToOne;

public record LibroDTO(
        String titulo,
        String idiomas,
        Integer descargas,
        AutorDTO autor
){}
