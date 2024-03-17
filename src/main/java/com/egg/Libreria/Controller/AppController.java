/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

/**
 *
 * @author yazminferreyra
 */

    @Controller
public class AppController {
@Autowired
private JavaMailSender mailSender;
public void sendEmail() {
// use mailSender here...
}
}

