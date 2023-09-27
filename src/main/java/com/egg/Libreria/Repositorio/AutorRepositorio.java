package com.egg.Libreria.Repositorio;

import com.egg.Libreria.Entidades.Autor;
import com.egg.Libreria.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepositorio extends JpaRepository<Autor, String> {

       @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Autor buscarPorNombre(String nombre);

   
}
