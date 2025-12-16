package com.desafios.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Lenguajes> lenguajes = new HashSet<>();
    private Integer numeroDeDescargas;

    public Libro(){
    }
    public Libro(DatosLibros datosLibros){
        this.titulo = datosLibros.titulo();
        this.autores = datosLibros.autores().stream()
                .map(Autor::new).toList();
        this.lenguajes = datosLibros.lenguajesCodigo().stream()
                .map(Lenguajes::fromCodigo).collect(Collectors.toSet());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Set<Lenguajes> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Set<Lenguajes> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
