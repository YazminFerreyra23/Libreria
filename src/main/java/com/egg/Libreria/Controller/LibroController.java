/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.Controller;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Editorial;
import com.egg.Libreria.Entidades.Libro;
import com.egg.Libreria.Entidades.Usuario;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.AutorService;
import com.egg.Libreria.Service.EditorialService;
import com.egg.Libreria.Service.LibroService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial,
            ModelMap modelo
    ) {
        try {
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("exito", "El libro fue cargado correctamente ");

            //return "Libro_Form.html";
        } catch (MiException ex) {

            List<Autor> autores = autorService.listarAutor();
            List<Editorial> editoriales = editorialService.editorialLista();
            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());

            return "Libro_Form.html";
        }

        //System.out.println("Nombre:" + nombre);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/registrar")
    public String registrar(HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        if (logueado.getRol().toString().equals("ADMIN")) {

            return "Editorial_Form.html";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Libro> libros = libroService.listarLibros();
        modelo.addAttribute("libros", libros);
        return "Libro_List.html";
    }

    @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {

        modelo.put("libro", libroService.getOne(isbn));
        List<Autor> autores = autorService.listarAutor();
        List<Editorial> editoriales = editorialService.editorialLista();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);
        return "Libro_Modificar.html";

    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo) {

        try {
            List<Autor> autores = autorService.listarAutor();
            List<Editorial> editoriales = editorialService.editorialLista();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            libroService.modificarLibro(isbn, titulo, idAutor, idEditorial, ejemplares);
            return "redirect:../lista";
        } catch (Exception ex) {
            List<Autor> autores = autorService.listarAutor();
            List<Editorial> editoriales = editorialService.editorialLista();

            modelo.put("error", ex.getMessage());

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);

            return "Libro_Modificar.html";
        }

    }

    @GetMapping("/buscarPorTitulo")
    public String buscarPorTitulo(@RequestParam String titulo, ModelMap modelo) {

        List<Libro> libroTitulos = libroService.buscarPorTitulo(titulo);

        modelo.addAttribute("titulo", libroTitulos);

        return "busqueda.html";
    }

}
