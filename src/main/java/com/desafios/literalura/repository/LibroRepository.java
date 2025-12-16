package com.desafios.literalura.repository;

import com.desafios.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("""
            SELECT DISTINCT l FROM Libro l LEFT JOIN FETCH l.autores LEFT JOIN FETCH l.lenguajes
            """)
    List<Libro> buscarTodosConAutoresYLenguajes();
}
