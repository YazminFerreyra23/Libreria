package com.egg.Libreria.Controller;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Editorial;
import com.egg.Libreria.Entidades.Usuario;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.AutorService;
import com.egg.Libreria.Service.EditorialService;
import java.util.List;
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
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;



    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
            ModelMap modelo
    ) {
        try {
            editorialService.crearEditorial(nombre);
            modelo.put("exito", "El editorial fue cargado correctamente ");

            //return "Libro_Form.html";
        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());

            return "Editorial_Form.html";
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
        }else

        return "redirect:/";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {
        List<Editorial> editoriales = editorialService.editorialLista();
        modelo.addAttribute("editoriales", editoriales);
        return "Editorial_List.html";

    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialService.getOne(id));
        return "Editorial_Modificar.html";

    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {

        try {
            editorialService.modificarEditorial(id, nombre);
            return "redirect:../lista";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "Editorial_Modificar.html";
        }

    }

}
