/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.Entidades;

import com.egg.Libreria.Enum.Rol;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 *
 * @author yazminferreyra
 */
@Entity
@Getter
@Setter
@ToString
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    private String id ;
    private String userName;
    private String password;
    private String email;
    
 @OneToOne
 private Imagen imagen;
    
 @Enumerated(EnumType.STRING)
 private Rol rol;
}
