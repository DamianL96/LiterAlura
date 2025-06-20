package com.LiterAtura.Challenge.main;

import com.LiterAtura.Challenge.models.*;
import com.LiterAtura.Challenge.repository.AutorRepository;
import com.LiterAtura.Challenge.repository.LibroRepository;
import com.LiterAtura.Challenge.services.APIConnection;
import com.LiterAtura.Challenge.services.TransformJsonToClass;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Menu {

  private Scanner teclado = new Scanner(System.in);
  private APIConnection apiConnection = new APIConnection();;
  private TransformJsonToClass transformJsonToClass = new TransformJsonToClass();
  private RRespuestaApi respuestaApi;
  private String json;

  private List<Libro> librosBuscados = new ArrayList<>();

  private LibroRepository libroRepositorio;
  private AutorRepository autorRepositorio;

  private Optional<Autor> autorDB;

  public Menu (LibroRepository libroRepository, AutorRepository autorRepository){
    this.libroRepositorio = libroRepository;
    this.autorRepositorio = autorRepository;
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
            6- autor por nombre
            7- libros por idioma
            
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
    //librosBuscados.add(new Libro(libroBuscado));

    Libro libro = new Libro(libroBuscado);

    autorDB = autorRepositorio.findByNombreIgnoreCaseContaining(libro.getAutor().getNombre());//buscamos el autor en db

    if(autorDB.isPresent()){        //si el autor existe, lo incorporamos al libro
      libro.setAutor(autorDB.get());
      System.out.println("-Autor Existente-");

    }else {
      autorRepositorio.save(libro.getAutor()); //si no lo agregamos a la db
      System.out.println("-Autor Nuevo-");
    }

    libroRepositorio.save(libro); //por ultimo guardamos el autor

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

  private void autorXNombre(){
    System.out.println("Ingresar nombre de autor:");
    var autor = teclado.nextLine();
    autorDB = autorRepositorio.findByNombreIgnoreCaseContaining(autor);

    if(autorDB.isPresent()){
      System.out.println(autorDB);
    }else {
      System.out.println("No se encontro el autor");
    }
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

  private void mostrarIdiomas(){
    var idiomas= """
            1- ingles
            2- español
            3- frances
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
        buscarLibrosXIdioma("fr");
        break;

      default:
        System.out.println("Idioma no encontrado");
        break;
    }
  }

  private void buscarLibrosXIdioma(String idioma){
    List<Libro> libros = libroRepositorio.findByIdiomas(idioma);
    libros.forEach(System.out::println);
  }

}


