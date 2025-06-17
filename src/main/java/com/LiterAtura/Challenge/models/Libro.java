package com.LiterAtura.Challenge.models;

import jakarta.persistence.criteria.CriteriaBuilder;

public class Libro {
  private String titulo;
  private String autor;
  private String idiomas;
  private Integer descargas;

  public Libro(){}

  public Libro(RLibro record) {
    this.titulo = record.titulo();;
    this.autor = String.valueOf(record.autores());
    this.idiomas = record.idiomas().getFirst();
    this.descargas = record.descargas();
  }

  @Override
  public String toString() {
    return "Libro{" +
            "titulo='" + titulo + '\'' +
            ", autor='" + autor + '\'' +
            ", idiomas='" + idiomas + '\'' +
            ", descargas=" + descargas +
            '}';
  }

  public String getTitulo() {
    return titulo;
  }

  public String getAutor() {
    return autor;
  }

  public String getIdiomas() {
    return idiomas;
  }

  public Integer getDescargas() {
    return descargas;
  }
}
