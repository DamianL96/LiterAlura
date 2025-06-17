package com.LiterAtura.Challenge.main;

import com.LiterAtura.Challenge.models.RLibro;
import com.LiterAtura.Challenge.models.RRespuestaApi;
import com.LiterAtura.Challenge.services.APIConnection;
import com.LiterAtura.Challenge.services.TransformJsonToClass;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

  private Scanner teclado = new Scanner(System.in);
  private APIConnection apiConnection = new APIConnection();;
  private TransformJsonToClass transformJsonToClass = new TransformJsonToClass();
  private RRespuestaApi respuestaApi;
  private String json;

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
            
            0- Salir
            """;
    System.out.println(opciones);
  }

  private void buscarLibroXTitulo(){
    System.out.println("INgresar nombre del libro:");
    var titulo = teclado.nextLine();

    System.out.println("Buscando...");
    json = apiConnection.connect("https://gutendex.com/books/?search="+titulo.replace(" ","%20"));
    respuestaApi = transformJsonToClass.transformar(json, RRespuestaApi.class);
    RLibro libroBuscado= respuestaApi.libros().getFirst();
    System.out.println(libroBuscado.toString());
    System.out.println("Operacion finalizada.");
  }

  private void mostrarLibros(){
  }

}
