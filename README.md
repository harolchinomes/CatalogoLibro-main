# Proyecto de Catálogo de Libros

Este es un proyecto de un catálogo de libros desarrollado con Java, Spring Boot y una base de datos relacional. Permite almacenar y consultar libros y autores de una API externa, gestionar información sobre ellos, y realizar búsquedas a través de varios criterios.

## Descripción

El proyecto tiene como objetivo crear un sistema para gestionar un catálogo de libros y autores. La aplicación se conecta con la API pública de [Gutendex](https://gutendex.com/), que ofrece acceso a libros de dominio público, y almacena la información de libros y autores en una base de datos relacional.

Los usuarios pueden:
- Buscar libros por título o autor.
- Listar libros y autores registrados en el sistema.
- Filtrar autores por aquellos que están vivos en un año determinado.
- Filtrar libros por idioma.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación.
- **Spring Boot**: Framework para crear aplicaciones Java basadas en Spring.
- **JPA (Java Persistence API)**: Para interactuar con la base de datos.
- **Hibernate**: Implementación de JPA para la persistencia de datos.
- **REST API (Gutendex)**: Para obtener información sobre libros y autores.

## Estructura del Proyecto

El proyecto se organiza de la siguiente manera:

- **`model`**: Contiene las clases que representan los datos que se gestionan (libros, autores).
- **`repository`**: Contiene las interfaces para interactuar con la base de datos utilizando JPA.
- **`service`**: Contiene la lógica de negocio, como la conexión con la API externa.
- **`principal`**: Contiene la lógica para ejecutar la aplicación y mostrar el menú de opciones al usuario.

## Instrucciones para Ejecutar

### Requisitos

- Java 17 o superior
- Maven o Gradle (dependiendo de la configuración del proyecto)
- Una base de datos configurada (puede ser H2, MySQL, PostgreSQL, etc.)

### Pasos para ejecutar el proyecto

1. Clona el repositorio:

    ```bash
    git clone https://github.com/tu-usuario/catalogo-libros.git
    ```

2. Ingresa al directorio del proyecto:

    ```bash
    cd catalogo-libros
    ```

3. Si estás utilizando **Maven**, puedes compilar el proyecto con:

    ```bash
    mvn clean install
    ```

    O si usas **Gradle**:

    ```bash
    gradle build
    ```

4. Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

    O con Gradle:

    ```bash
    gradle bootRun
    ```

5. La aplicación se ejecutará en `http://localhost:8080` por defecto.

### Endpoints

La aplicación proporciona los siguientes servicios:

- **GET /libros**: Lista todos los libros registrados en la base de datos.
- **GET /autores**: Lista todos los autores registrados en la base de datos.
- **POST /buscar-libro**: Permite buscar un libro por su título o autor a través de la API externa de Gutendex.

## Funcionalidades

### Menú Principal

El usuario interactúa con la aplicación mediante un menú interactivo en la consola que permite:

1. Buscar libros por título o autor.
2. Listar libros registrados.
3. Listar autores registrados.
4. Filtrar autores vivos en un determinado año.
5. Filtrar libros por idioma.

### Flujo de búsqueda de libros

1. El usuario ingresa el título del libro o nombre del autor que desea buscar.
2. La aplicación hace una consulta a la API de Gutendex.
3. Los resultados obtenidos se muestran al usuario y se registran en la base de datos si aún no están registrados.

### Persistencia de Datos

Los libros y autores se almacenan en la base de datos, lo que permite que la información se mantenga disponible incluso después de reiniciar la aplicación.

## Contribución

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama para tu nueva característica (`git checkout -b feature/nueva-caracteristica`).
3. Realiza los cambios necesarios y haz commit de tus cambios (`git commit -am 'Agrega nueva característica'`).
4. Empuja a tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un pull request en este repositorio.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

¡Gracias por usar el catálogo de libros! Si tienes alguna pregunta o sugerencia, no dudes en abrir un *issue* en GitHub.
