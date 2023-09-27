package com.egg.Libreria.Repositorio;

import com.egg.Libreria.Entidades.Editorial;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author yazminferreyra
 */
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e")
    List<Editorial> findAllEditoriales();
    
    @Query("SELECT e FROM Editorial e WHERE e.id = :editorialId")
    Optional<Editorial> findEditorialById(@Param("editorialId") String id);
    
    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    List<Editorial> findEditorialesByNombre(@Param("nombre") String nombre);

}
