package com.egg.Libreria.Service;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yazminferreyra
 */
@Service
public class AutorService {
    
    @Autowired
    AutorRepositorio autorRepositorio ;
    
    @Transactional
    public void crearAutor(String nombre) throws MiException{
        
        validar(nombre);
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);
        
    }
    public List<Autor> listarAutor(){
       
        return autorRepositorio.findAll();
    }
    
    public void modificarAutor(String nombre, String id){
        
        Optional<Autor> respuesta =autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            
            autorRepositorio.save(autor);
        }
        
        
        
    }

    private void validar(String nombre) throws MiException{
        
        
        if(nombre.isEmpty() || nombre==null){
            
            throw new MiException(" ' El nombre no puede ser nulo o estar vacio '  ");
            
        }
    }
    
}
