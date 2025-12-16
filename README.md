# 📚 LiterAlura

Proyecto desarrollado como **Challenge del programa Oracle Next Education (ONE) G9**, enfocado en el consumo de APIs, persistencia de datos con JPA y manejo de relaciones entre entidades usando **Java + Spring Boot**.

---

## 🎯 Objetivo del Proyecto

Construir una aplicación de consola que permita:

- Consumir la **API pública de Gutendex**
- Registrar libros y autores en una base de datos **PostgreSQL**
- Consultar información almacenada usando **Spring Data JPA**
- Aplicar buenas prácticas de diseño y modelado de datos

---

## 🚀 Funcionalidades Implementadas

✔ Configuración del entorno Java y Spring Boot  
✔ Consumo de la API de **Gutendex**  
✔ Construcción dinámica de solicitudes HTTP para buscar libros  
✔ Conversión de respuestas JSON usando **Jackson**  
✔ Persistencia de libros y autores en **PostgreSQL**  
✔ Consulta de todos los libros con sus autores e idiomas  
✔ Consulta de todos los autores con sus libros  
✔ Consulta de autores vivos en un año determinado  
✔ Consulta de libros por idioma usando **Enum**

---

## 🗂️ Estructura del Proyecto

```
src/main/java/com/desafios/literalura
│
├── model
│   ├── Autor.java                # Entidad Autor y su constructor
│   ├── Libro.java                # Entidad Libro y su constructor
│   ├── Datos.java                # Record que mapea la respuesta general de Gutendex
│   ├── DatosAutores.java         # Record para transformar autores de un libro
│   ├── DatosLibros.java          # Record para transformar libros individuales
│   ├── Lenguajes.java            # Enum de idiomas con código, nombre completo y menú
│
├── principal
│   └── Principal.java            # Interacción entre el usuario y la aplicación
│
├── repository
│   ├── AutorRepository.java      # Consultas JPA relacionadas con autores
│   └── LibroRepository.java      # Consultas JPA relacionadas con libros
│
├── service
│   ├── ConsumoAPI.java           # Llamados HTTP a la API Gutendex
│   ├── ConvierteDatos.java       # Conversión JSON → Records usando Jackson
│   └── IConvierteDatos.java      # Interfaz para la conversión de datos
│
└── LiterAluraApplication.java    # Clase principal que ejecuta la aplicación
```

---

## 🌐 API Utilizada

**Gutendex**
- URL base: `https://gutendex.com/books/`
- Permite consultar libros de dominio público
- Devuelve información en formato JSON

---

## 🧠 Manejo de Idiomas

Se implementó un `enum Lenguajes` que:
- Define el código ISO del idioma (EN, ES, FR, PT)
- Incluye el nombre completo del idioma
- Muestra un menú interactivo para seleccionar el idioma al buscar libros

---

## 🗄️ Base de Datos

Se utiliza **PostgreSQL** junto con **Spring Data JPA** para:

- Guardar libros y autores
- Manejar relaciones `@ManyToMany`
- Ejecutar consultas personalizadas con `@Query`

---

## ⚙️ Configuración del Proyecto

Archivo `application.properties`:

```properties
spring.application.name=literalura

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://${DB_HOST}/literalura
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

# Dialecto de Hibernate
hibernate.dialect=org.hibernate.dialect.HSQLDialect

# Configuración JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
```

### 🔎 Explicación

- **spring.application.name**: Nombre de la aplicación
- **spring.datasource.url**: URL de conexión a PostgreSQL
- **DB_HOST**: Host y puerto de la base de datos (variable de entorno)
- **DB_USERNAME / DB_PASSWORD**: Credenciales de la base de datos
- **hibernate.dialect**: Dialecto SQL usado por Hibernate
- **ddl-auto=update**: Crea o actualiza tablas automáticamente
- **show-sql**: Muestra las consultas SQL en consola
- **format-sql**: Da formato legible a las consultas

---

## 🧪 Ejecución del Proyecto

1. Configura PostgreSQL y crea una base de datos llamada `literalura`
2. Define las variables de entorno:
    - `DB_HOST`
    - `DB_USERNAME`
    - `DB_PASSWORD`
3. Ejecuta la aplicación
4. Usa el menú interactivo en consola

---

## 🏁 Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jackson
- Maven

---

## 👨‍🎓 Proyecto Educativo

Este proyecto hace parte del **Challenge LiterAlura**
del programa **Oracle Next Education (ONE) G9**,  
orientado a fortalecer habilidades en backend con Java y Spring.

---

✨ ¡Proyecto completado con éxito! ✨
