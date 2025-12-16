package com.desafios.literalura.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public enum Lenguajes {
    ES("Español"),
    EN("Inglés"),
    FR("Francés"),
    PT("Portugués");

    private final String NOMBRE_COMPLETO;
    Lenguajes(String NOMBRE_COMPLETO){this.NOMBRE_COMPLETO = NOMBRE_COMPLETO;}

    public String getNOMBRE_COMPLETO(){
        return this.NOMBRE_COMPLETO;
    }

    public static Lenguajes fromCodigo(String codigo){
        if (codigo == null) return null;
        String codigoUpper = codigo.toUpperCase();
        for (Lenguajes lenguaje: values()){
            if (lenguaje.name().equals(codigoUpper)){
                return lenguaje;
            }
        }
        return null;
    }

    public static List<Lenguajes> fromListaCodigos(List<String> codigos){
        if (codigos == null) return Collections.emptyList();
        List<Lenguajes> lenguajes = new ArrayList<>();
        for (String codigo : codigos){
            Lenguajes lenguaje = fromCodigo(codigo);
            if (lenguaje != null){
                lenguajes.add(lenguaje);
            }
        }
        return lenguajes;
    }

    public static void mostrarMenu(){
        System.out.println("========= Lenguas Disponibles =========");
        Lenguajes[] lenguajes = Lenguajes.values();
        for (int i = 0; i < lenguajes.length; i++) {
            System.out.printf("%2d. %s (%s)%n", i + 1, lenguajes[i].getNOMBRE_COMPLETO(), lenguajes[i].name());
        }
    }

    public static Lenguajes seleccionarLengua(Scanner lectura,String tipo){
        while (true){
            System.out.println("\n --- Seleccionar Lengua "+tipo.toUpperCase()+"---");
            mostrarMenu();
            System.out.println("Seleccione el numero del Lenguaje que desea buscar ");
            try {
                int seleccion = lectura.nextInt();
                lectura.nextLine();
                if(seleccion >= 1 && seleccion<= Lenguajes.values().length){
                    return Lenguajes.values()[seleccion - 1];
                }else{
                    System.out.println("Error: Seleccione un numero entre 1 y" + Lenguajes.values().length);
                }
            }catch (Exception e){
                System.out.println("Error: Entrada Invalida. Debe ingresar un numero.");
            }
        }
    }
}
