package com.LiterAtura.Challenge.models;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Objects;

public class Libro {
  private String titulo;
  private Autor autor;
  private String idiomas;
  private Integer descargas;

  public Libro(){}

  public Libro(RLibro record) {
    this.titulo = record.titulo();;
    this.autor = new Autor(record.autores().getFirst());
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

  public Autor getAutor() {
    return autor;
  }

  public String getIdiomas() {
    return idiomas;
  }

  public Integer getDescargas() {
    return descargas;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Libro libro = (Libro) o;
    return Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor) && Objects.equals(idiomas, libro.idiomas) && Objects.equals(descargas, libro.descargas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titulo, autor, idiomas, descargas);
  }
}
