package com.egg.Libreria.Repositorio;

import com.egg.Libreria.Entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutorRepositorio extends JpaRepository<Autor, String> {

       @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Autor buscarPorNombre(String nombre);

   
}
