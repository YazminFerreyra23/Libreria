/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.Libreria.Repositorio;

import com.egg.Libreria.Entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yazminferreyra
 */
@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String > {
    
}
