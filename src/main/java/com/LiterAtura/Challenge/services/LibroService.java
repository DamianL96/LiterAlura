package com.LiterAtura.Challenge.services;

import com.LiterAtura.Challenge.dto.AutorDTO;
import com.LiterAtura.Challenge.dto.LibroDTO;
import com.LiterAtura.Challenge.models.Libro;
import com.LiterAtura.Challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

  @Autowired
  private LibroRepository libroRepository;

  @Autowired
  private AutorService autorService;

  //transformar clase a dto
  private List<LibroDTO> convierteLibroALibroDTO(List<Libro> libros){
    return libros.stream()
            .map(l -> new LibroDTO(
                    l.getTitulo(),
                    l.getIdiomas(),
                    l.getDescargas(),
                    autorService.convierteADTO(l.getAutor())))
            .toList();
  }

  //mostrar todos los libros
  public  List<LibroDTO> devolverTodosLosLibros(){
    return convierteLibroALibroDTO(libroRepository.findAll());
  }

  //filtrar libros por idioma
  public List<LibroDTO> filtrarLibroPorIdioma(String idioma){
    return convierteLibroALibroDTO(libroRepository.findByIdiomas(idioma));
  }


  //cantidad de libros por idioma
  public Long devolverCantidadDeLibrosPorIdioma(String idioma){
    return libroRepository.countByIdiomas(idioma);
  }

  public DoubleSummaryStatistics estadisticasDeIdioma(String idioma){
    List<LibroDTO> libros = filtrarLibroPorIdioma(idioma);
    DoubleSummaryStatistics est = libros.stream()
            .filter(l -> l.descargas() > 0.0)
            .collect(Collectors.summarizingDouble(LibroDTO::descargas));
    return est;
  }
}
