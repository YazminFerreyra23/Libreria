/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.Controller;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Editorial;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.AutorService;
import com.egg.Libreria.Service.EditorialService;
import com.egg.Libreria.Service.LibroService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author yazminferreyra
 */
@Controller
@RequestMapping("/libro")

public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private EditorialService editorialService;

    @GetMapping("/registrar")
    public String registrar( ModelMap libroModelo) {
        List<Autor> autores = autorService.listarAutor();
        List<Editorial> editoriales = editorialService.editorialLista();
        libroModelo.addAttribute("autores", autores);
        libroModelo.addAttribute("editoriales", editoriales);
        return "Libro_Form.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam Long isbn,
            @RequestParam Integer ejemplares,
            @RequestParam String nombre, @RequestParam String idAutor, @RequestParam String idEditorial,
            ModelMap modelo
    ) {
        try {
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
        } catch (MiException ex) {
         modelo.put("Error", ex.getMessage());
                return "Libro_Form.html";
        }

        //System.out.println("Nombre:" + nombre);
        return "index.html";
 

    }

}
