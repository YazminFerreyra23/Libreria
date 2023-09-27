package com.egg.Libreria.Controller;

import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.AutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") 
public class AutorController {
    
 @Autowired
 private AutorService autorService;
   @GetMapping("/registrar")
   public String registrar(){
       return "Autor_Form.html";
    
}
   @PostMapping("/registro")
   public String registro(@RequestParam String nombre){
     
       
     try {
         autorService.crearAutor(nombre);
     } catch (MiException ex) {
         Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
       return "Autor_Form.html";
     }
       
       //System.out.println("Nombre:" + nombre);
       return "index.html";
       
       
       
   }
   
   @GetMapping("/inicio")
    public String mostrarIndex() {
        return "index.html";
    }
}
