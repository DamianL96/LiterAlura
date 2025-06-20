package com.LiterAtura.Challenge.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name="autores")
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String nombre;
  private Integer nacimiento;
  private Integer muerte;

  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL) //el nombre del atributo Autor en Libro
  private List<Libro> libros;

  public Autor(){}

  public Autor(RAutor record){

    this.nombre = record.nombre();
    this.nacimiento = record.nacimiento();
    this.muerte = record.muerte();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getNacimiento() {
    return nacimiento;
  }

  public void setNacimiento(Integer nacimiento) {
    this.nacimiento = nacimiento;
  }

  public Integer getMuerte() {
    return muerte;
  }

  public void setMuerte(Integer muerte) {
    this.muerte = muerte;
  }

  @Override
  public String toString() {
    return "Autor{" +
            "nombre='" + nombre + '\'' +
            ", nacimiento=" + nacimiento +
            ", muerte=" + muerte +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Autor autor = (Autor) o;
    return Objects.equals(nombre, autor.nombre) && Objects.equals(nacimiento, autor.nacimiento) && Objects.equals(muerte, autor.muerte);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, nacimiento, muerte);
  }
}
