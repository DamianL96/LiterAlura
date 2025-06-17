package com.LiterAtura.Challenge.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RRespuestaApi(
        @JsonAlias("results")List<RLibro> libros
){}
