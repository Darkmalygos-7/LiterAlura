package com.desafios.literalura.repository;

import com.desafios.literalura.model.Lenguajes;
import com.desafios.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("""
            SELECT DISTINCT l FROM Libro l LEFT JOIN FETCH l.autores LEFT JOIN FETCH l.lenguajes
            """)
    List<Libro> buscarTodosConAutoresYLenguajes();
    @Query("""
            SELECT DISTINCT l FROM Libro l JOIN FETCH l.autores WHERE :lenguaje MEMBER OF l.lenguajes
            """)
    List<Libro> buscarLibrosPorIdioma(@Param("lenguaje")Lenguajes lenguajes);
}
