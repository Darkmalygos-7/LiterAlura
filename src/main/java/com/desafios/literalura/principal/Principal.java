package com.desafios.literalura.principal;

import com.desafios.literalura.model.*;
import com.desafios.literalura.repository.AutorRepository;
import com.desafios.literalura.repository.LibroRepository;
import com.desafios.literalura.service.ConsumoAPI;
import com.desafios.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private LibroRepository repository;
    private AutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repository = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    1  - Buscar Libro Por Titulo
                    2  - Listar Libros Registrados
                    3  - Listar Autores Registrados
                    4  - Listar Autores Vivos En Determinado Año
                    5  - Listar Libros Por Idioma
                    
                    0  - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 0:
                    System.out.println("Cerrando Aplicación....");
                    break;
                default:
                    System.out.println("Opción Inválida");
            }
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que desea registrar");
        var nombreLibro = teclado.nextLine();
        var url = URL_BASE+"search="+nombreLibro.replace(" ","+");
        var json = consumoAPI.obtenerDatos(url);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        if (datos.resultados().isEmpty()){
            System.out.println("Libro no encontrado.");
            return;
        }
        DatosLibros datosLibro = datos.resultados().get(0);
        List<Lenguajes> lenguajes = datosLibro.getLenguajes();
        if (lenguajes != null && !lenguajes.isEmpty()) {
            System.out.println("Lenguajes disponibles:");
            for (Lenguajes lenguaje : lenguajes) {
                System.out.println("- " + lenguaje.getNOMBRE_COMPLETO() + " (" + lenguaje.name() + ")");
            }
        }
        System.out.println(datosLibro);
        Libro libro = new Libro(datosLibro);
        Set<Autor> autores = new HashSet<>();
        for (Autor autor: libro.getAutores()){
            Optional<Autor> autorExistente = autorRepository.findByNombre(autor.getNombre());
            if (autorExistente.isPresent()){
                autores.add(autorExistente.get());
            }else {
                autores.add(autorRepository.save(autor));
            }
        }
        libro.setAutores(new ArrayList<>(autores));
        repository.save(libro);
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = repository.buscarTodosConAutoresYLenguajes();
        if (libros.isEmpty()){
            System.out.println("No hay Libros Registrados");
            return;
        }
        libros.forEach(l -> {
            System.out.println("Titulo = "+l.getTitulo());
            System.out.println("Autores:");
            l.getAutores().forEach(a -> System.out.println(" - "+ a.getNombre()));
            System.out.println("Idiomas:");
            l.getLenguajes().forEach(lang -> System.out.println(" - "+lang.getNOMBRE_COMPLETO()));
            System.out.println("Cantidad de Descargas = "+ l.getNumeroDeDescargas());
            System.out.println("===================================");
        });
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.buscarAutoresYSusLibros();
        if (autores.isEmpty()){
            System.out.println("No hay Autores Registrados");
            return;
        }
        autores.forEach(a -> {
            System.out.println("Autor ="+a.getNombre());
            System.out.println("Año de Nacimiento = "+a.getFechaDeNacimiento());
            System.out.println("Año de Fallecimiento = "+a.getFechaDeFallecimiento());
            System.out.println("Libros:");
            a.getLibros().forEach(l -> System.out.println(" - "+l.getTitulo()));
            System.out.println("===================================");
        });
    }

}
