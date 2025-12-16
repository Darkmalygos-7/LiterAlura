package com.desafios.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        @JsonAlias("death_year") Integer fechaDeFallecimiento
) {
}
