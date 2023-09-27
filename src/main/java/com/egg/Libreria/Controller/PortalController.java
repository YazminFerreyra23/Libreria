
package com.egg.Libreria.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yazminferreyra
 */
@Controller
@RequestMapping("/")
public class PortalController {
  
    @GetMapping("/")
    public String index(){
      
        return "index.html";   
    }
    
    
}
