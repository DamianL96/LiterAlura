package com.LiterAtura.Challenge.models;

import java.util.Objects;

public class Autor {
  private String nombre;
  private Integer nacimiento;
  private Integer muerte;

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
