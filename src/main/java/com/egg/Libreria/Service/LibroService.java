package com.egg.Libreria.Service;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Editorial;
import com.egg.Libreria.Entidades.Libro;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Repositorio.AutorRepositorio;
import com.egg.Libreria.Repositorio.EditorialRepositorio;
import com.egg.Libreria.Repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class LibroService {

    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    
    public void crearLibro(Long isbn, String titulo, Integer ejemplares,String idAutor,String idEditorial) throws MiException {
   
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        
        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);

    }

    public List<Libro> listarLibros(){
        
       
        
        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }
    
    public void modificarLibro (Long isbn, String titulo, String idAutor,String idEditorial,Integer ejemplares) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if(respuestaEditorial.isPresent()){
            editorial.getClass();
        }
        
  
        
        if(respuestaAutor.isPresent()){
          autor = respuestaAutor.get();
               
    }
        
              if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
        }
    }
    
    public void validar(Long isbn,String titulo,Integer ejemplares,String idAutor,String idEditorial ) throws MiException{
        
              
        if(isbn==null){
            throw new MiException("El isbn no puede ser nulo");
   
        }
        
        if(titulo.isEmpty() || titulo == null){
            throw new MiException("El titulo no puede ser nulo o estar vacio");
        }
        
        if(ejemplares == null){
            throw new MiException("los ejemplares no pueden ser nulos");
        }
        
        if(idAutor.isEmpty() || idAutor == null){
            throw  new MiException("El idAutor no puede ser nulo o estar vacio");
            
        }
        
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiException("El idEditorial no puede ser nulo o estar vacio");
        }
   
    }
}
