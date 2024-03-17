package com.egg.Libreria.Controller;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Usuario;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.AutorService;
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

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {

        try {
            autorService.crearAutor(nombre);
        } catch (MiException ex) {
            Logger.getLogger(AutorController.class.getName()).log(Level.SEVERE, null, ex);
            return "Autor_Form.html";
        }

        //System.out.println("Nombre:" + nombre);
        return "redirect:/";

    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/registrar")
    public String registrar(HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "Autor_Form.html";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/inicio")
    public String mostrarIndex() {
        return "index.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap modelo) {
        List<Autor> autores = autorService.listarAutor();
        modelo.addAttribute("autores", autores);
        return "Autor_List.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("autor", autorService.getOne(id));
        return "Autor_Modificar.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        try {
            autorService.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "Autor_Modificar.html";
        }

    }

}
