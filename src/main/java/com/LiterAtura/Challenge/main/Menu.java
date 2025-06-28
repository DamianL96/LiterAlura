package com.LiterAtura.Challenge.main;

import com.LiterAtura.Challenge.models.*;
import com.LiterAtura.Challenge.services.APIConnection;
import com.LiterAtura.Challenge.services.AutorService;
import com.LiterAtura.Challenge.services.LibroService;
import com.LiterAtura.Challenge.services.TransformJsonToClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Menu {

  @Autowired
  private LibroService libroService;

  @Autowired
  private AutorService autorService;

  private Scanner teclado = new Scanner(System.in);
  private APIConnection apiConnection = new APIConnection();;
  private TransformJsonToClass transformJsonToClass = new TransformJsonToClass();
  private RRespuestaApi respuestaApi;
  private String json;

  private Optional<Autor> autorDB;

  public Menu (LibroService libroService, AutorService autorService){
    this.libroService = libroService;
    this.autorService = autorService;
  }

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

          case 6:
            autorXNombre();
            break;

          case 7:
            cantidadLibrosXIdioma();
            break;

          case 8:
            top10LibrosMasDescargados();
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
            1- Buscar libro en API
            2- Mostrar libros
            3- Filtrar Por Idioma
            4- Listar autores
            5- Buscar autor por anio
            6- Buscar autor por nombre
            7- Estadisticas de descargas por idioma
            8- Top 10 Libros mas descargados
            
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

    try{
      RLibro libroBuscado= respuestaApi.libros().getFirst();
      Libro libro = new Libro(libroBuscado);
      libroService.guardarLibro(libro);
      System.out.println(libroBuscado);

    }catch (NoSuchElementException e){
      System.out.println("\n -Ups... Parece que ese libro no se encuentra en la API... \n");
    }
  }

  private void mostrarLibros(){
    libroService.devolverTodosLosLibros().forEach(System.out::println);
  }

  private void filtrarPorIdioma(){
    System.out.println("Ingresar idioma (en - es - nl):");
    var idioma = teclado.nextLine();

    libroService.filtrarLibroPorIdioma(idioma).forEach(System.out::println);
  }

  private void autorXNombre(){
    System.out.println("Ingresar nombre de autor:");
    var autor = teclado.nextLine();
    System.out.println(autorService.devolverAutorPorNombre(autor));;
  }

  private void listarAutores(){
    autorService.devolverTodosLosAutores().forEach(System.out::println);
  }

  private void listarAutoresVivosxAnio(){
    System.out.println("Ingresar año:");
    var anio = teclado.nextInt();
    autorService.devolverAutorPorAnio(anio).forEach(System.out::println);
  }

  private void mostrarIdiomas(){
    var idiomas= """
            1- ingles
            2- español
            3- neerlandes
            """;
    System.out.println(idiomas);
  }

  private void cantidadLibrosXIdioma(){
    mostrarIdiomas();
    System.out.println("Seleccionar idioma (ingresar numero): ");
    var idiomaElegido = teclado.nextInt();
    teclado.nextLine();

    switch (idiomaElegido){
      case 1:
        buscarLibrosXIdioma("en");
        break;

      case 2:
        buscarLibrosXIdioma("es");
        break;

      case 3:
        buscarLibrosXIdioma("nl");
        break;

      default:
        System.out.println("Idioma no encontrado");
        break;
    }
  }

  private void buscarLibrosXIdioma(String idioma){
    System.out.println("Total de libros en ("+idioma+") : " +libroService.devolverCantidadDeLibrosPorIdioma(idioma));
    estadisticasLibrosPorIdiomas(idioma);
  }

  private void estadisticasLibrosPorIdiomas(String idioma){
    DoubleSummaryStatistics est= libroService.estadisticasDeIdioma(idioma);
    System.out.println("Media de descargas por libro: "+est.getAverage());
    System.out.println("Mayor cantidad de descargas por libro: "+est.getMax());
    System.out.println("Menor cantidad de descargas por libro: "+est.getMin());
  }

  private void top10LibrosMasDescargados(){
    libroService.top10Descargas().forEach(System.out::println);
  }

}


