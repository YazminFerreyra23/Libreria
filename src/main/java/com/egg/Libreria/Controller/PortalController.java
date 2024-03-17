package com.egg.Libreria.Controller;

import com.egg.Libreria.Entidades.Usuario;
import com.egg.Libreria.Exception.MiException;
import com.egg.Libreria.Service.UsuarioService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author yazminferreyra
 */
@Controller
@RequestMapping()
public class PortalController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {

        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {

        return "UsuarioForm.html";

    }

    @PostMapping("/registro")
    public String registro(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email, @RequestParam String password,
            String password2, ModelMap modelo) throws Exception {

        try {

            usuarioService.registrar(archivo, nombre, email, password, password2);

            modelo.put("exito", "Usuario registrado");

            return "index.html";

        } catch (Exception e) {

            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "UsuarioLogin.html";

        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,
            ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalido");
        }
        return "UsuarioLogin.html";
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/inicio")

    public String inicio(HttpSession session) {

        Usuario logueado = (Usuario) session.getAttribute("usuariosession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "redirect:/admin/dashboard";
        }
        return "/Inicio.html";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/perfil")
    public String perfil(ModelMap modelo, HttpSession session) {
        
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        modelo.put("usuario", usuario);
        return "Usuario_Modificar.html";
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/perfil/{id}")
    public String actualizar(MultipartFile archivo,@PathVariable String id ,@RequestParam String nombre,String email,
    @RequestParam String password,@RequestParam  String password2,ModelMap modelo,HttpSession session) throws MiException{
        try {
            Usuario usuarioActualizado = usuarioService.actualizar(archivo, password2, nombre, email, password, password2);
            usuarioService.actualizar(archivo,id,nombre,email,password,password2);
           modelo.put("exito","usuario actualizado" );
           return"redirect:/";
           
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);
            
              return "Usuario_Modificar.html";
        }
      
        
    }

}
