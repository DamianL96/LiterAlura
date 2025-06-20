package com.LiterAtura.Challenge.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.Cascade;

import java.util.Objects;

@Entity
@Table(name="libros")
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String idiomas;
  private Integer descargas;


  @ManyToOne
  //@JoinColumn(name="autor_id")
  private Autor autor;

  public Libro(){}

  public Libro(RLibro record) {

    if(record.titulo().length() > 254){
      this.titulo = record.titulo().substring(0,250);
    }else {
      this.titulo = record.titulo();
    }
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

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public void setDescargas(Integer descargas) {
    this.descargas = descargas;
  }

  public void setIdiomas(String idiomas) {
    this.idiomas = idiomas;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
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
