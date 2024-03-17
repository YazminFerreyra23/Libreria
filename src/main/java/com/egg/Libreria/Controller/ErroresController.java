/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.Controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author yazminferreyra
 */


@Controller
public class ErroresController implements ErrorController {

    @RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");

        String errorMsg = "";

        int httErrorCode = getErrorCode(httpRequest);

        switch (httErrorCode) {

            case 400: {

                errorMsg = "El recurso solicitado no existe. ";
                break;
            }
            case 403: {

                errorMsg = " No tiene permiso para acceder al recurso. ";
                break;
            }

            case 401: {

                errorMsg = " No se encuentra autorizado. ";
                break;
            }

            case 404: {

                errorMsg = " El recurso solicitado no fue encontrado. ";
                break;
            }

            case 500 : {

                errorMsg = "Ocurrio un error interno ";
                break;
                }
        }

        errorPage.addObject("codigo", httErrorCode);
        errorPage.addObject("mensaje", errorMsg);

        return errorPage;
    }



    private int getErrorCode(HttpServletRequest httpRequest) {
       return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");

    }
    
    public String getErrorPath() {
        return "/Error.html";
    }
    
    
}
