package com.egg.Libreria.Service;

import com.egg.Libreria.Entidades.Editorial;
import com.egg.Libreria.Repositorio.EditorialRepositorio;
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
public class EditorialService {
    
    @Autowired
    EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearEditorial(String nombre){
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        
        editorialRepositorio.save(editorial);
    }
    
    public List<Editorial> editorialLista(){
    
        
   
        return editorialRepositorio.findAll();
    
}
    
    public void modificarEditorial(String id,String nombre){
        
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);
        
        if(respuestaEditorial.isPresent()){
            Editorial editorial = respuestaEditorial.get();
            
            editorialRepositorio.save(editorial);
        }
        
    }
    
}
