package com.LiterAtura.Challenge.main;

import com.LiterAtura.Challenge.models.*;
import com.LiterAtura.Challenge.services.APIConnection;
import com.LiterAtura.Challenge.services.TransformJsonToClass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

  private Scanner teclado = new Scanner(System.in);
  private APIConnection apiConnection = new APIConnection();;
  private TransformJsonToClass transformJsonToClass = new TransformJsonToClass();
  private RRespuestaApi respuestaApi;
  private String json;

  private List<Libro> librosBuscados = new ArrayList<>();

  public void run(){

    cicloOpciones();


  }

  private void cicloOpciones(){
    var opcion = -1;

    while (opcion != 0){

      mostrarOpciones();
      System.out.println("Seleccione una opcion:");
      try{
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
          case 1:
            buscarLibroXTitulo();
            break;

          case 2:
            mostrarLibros();
            break;

          case 3:
            filtrarPorIdioma();
            break;

          case 4:
            listarAutores();
            break;

          case 5:
            listarAutoresVivosxAnio();
            break;

          case 0:
            System.out.println("Cerrando la app...");
            break;

          default:
            System.out.println("Opcion invalida");
        }

      }catch(InputMismatchException e){
        System.out.println("Solo ingresar numeros.");
        System.out.println("Cerrando la app...");
        break;
      }

    }

  }

  private void mostrarOpciones(){
    var opciones = """
            1- Buscar libros
            2- Mostrar libros
            3- Filtrar Por Idioma
            4- Listar autores
            5- Buscar autor por anio
            
            0- Salir
            """;
    System.out.println(opciones);
  }

  private void buscarLibroXTitulo(){
    System.out.println("Ingresar nombre del libro:");
    var titulo = teclado.nextLine();

    System.out.println("Buscando...");
    json = apiConnection.connect("https://gutendex.com/books/?search="+titulo.replace(" ","%20"));

    respuestaApi = transformJsonToClass.transformar(json, RRespuestaApi.class);
    RLibro libroBuscado= respuestaApi.libros().getFirst();
    System.out.println(libroBuscado);

    librosBuscados.add(new Libro(libroBuscado));
    System.out.println("Operacion finalizada.");
  }

  private void mostrarLibros(){
    librosBuscados.forEach(System.out::println);
  }

  private void filtrarPorIdioma(){
    System.out.println("Ingresar idioma (en - es):");
    var idioma = teclado.nextLine();

    librosBuscados.stream()
            .filter(l -> l.getIdiomas().equalsIgnoreCase(idioma))
            .forEach(System.out::println);
  }

  private void listarAutores(){
    List<String> autores = librosBuscados.stream()
            .map(Libro::getAutor)
            .map(Autor::getNombre)
            .map(String::toUpperCase)
            .distinct()
            .toList();
    autores.forEach(System.out::println);
  }

  private void listarAutoresVivosxAnio(){
    System.out.println("Ingresar año:");
    var anio = teclado.nextInt();

    List<Autor> autoresVivos = librosBuscados.stream()
            .map(Libro::getAutor)
            .distinct()//aca?
            .filter(a -> a.getNacimiento() <= anio && a.getMuerte() > anio)
            .toList();

    autoresVivos.forEach(System.out::println);
  }

}
