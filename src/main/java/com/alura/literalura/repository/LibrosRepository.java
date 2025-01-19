package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloAndAutorNombre(String titulo, String autorNombre);

    @Query("""
    SELECT l FROM Libro l
    WHERE l.autor.fechaNacimiento IS NOT NULL
    AND (:year BETWEEN l.autor.fechaNacimiento AND 
    COALESCE(NULLIF(l.autor.fechaDefuncion, 0), l.autor.fechaNacimiento + 100))
    """)
    List<Libro> findAutoresVivosEnAnio(@Param("year") int year);

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findByIdioma(@Param("idioma") String idioma);
}
