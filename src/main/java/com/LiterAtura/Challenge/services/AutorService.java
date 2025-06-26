package com.LiterAtura.Challenge.services;

import com.LiterAtura.Challenge.dto.AutorDTO;
import com.LiterAtura.Challenge.models.Autor;
import com.LiterAtura.Challenge.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

  @Autowired
  AutorRepository autorRepository;

  //Trasnsformar clase a dto
  private List<AutorDTO> convierteAutorAAutorDTO(List<Autor> autores){
    return autores.stream()
            .map(a -> new AutorDTO(
                    a.getNombre(),
                    a.getNacimiento(),
                    a.getMuerte()))
            .toList();
  }

  public AutorDTO convierteADTO(Autor autor){
    return new AutorDTO(
            autor.getNombre(),
            autor.getNacimiento(),
            autor.getMuerte());
  }

  //listar autores
  public List<AutorDTO> devolverTodosLosAutores(){
    return convierteAutorAAutorDTO(autorRepository.findAll());
  }

  //listar autor por anio
  public List<AutorDTO> devolverAutorPorAnio(Integer anio){
    return convierteAutorAAutorDTO(autorRepository.autorPorAnio(anio));
  }

  //buscar autor por nombre
  public AutorDTO devolverAutorPorNombre(String nombre){
    Optional<Autor> autor=autorRepository.findByNombreIgnoreCaseContaining(nombre);
    if(autor.isPresent()){
      return convierteADTO(autor.get());
    }
    return null;
  }

  public void guardarAutorEnDB(Autor autor){
    autorRepository.save(autor);
  }

  public Autor autorExistenteEnDB(String nombre){
    Optional<Autor> autorDB = autorRepository.findByNombreIgnoreCaseContaining(nombre); //Buscamos al autor

    if(autorDB.isPresent()){ //si existe lo devolvemos
      return autorDB.get();
    }
    return null;
  }
}
