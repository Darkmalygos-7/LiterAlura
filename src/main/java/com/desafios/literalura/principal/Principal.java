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

}
