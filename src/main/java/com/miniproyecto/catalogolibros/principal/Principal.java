package com.miniproyecto.catalogolibros.principal;

import com.miniproyecto.catalogolibros.model.Autor;
import com.miniproyecto.catalogolibros.model.Datos;
import com.miniproyecto.catalogolibros.model.Libro;
import com.miniproyecto.catalogolibros.repository.AutorRepository;
import com.miniproyecto.catalogolibros.repository.LibroRepository;
import com.miniproyecto.catalogolibros.service.ConsumirAPI;
import com.miniproyecto.catalogolibros.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumirAPI consumirAPI = new ConsumirAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL = "https://gutendex.com/books/?search=";
    private LibroRepository repositorioLibros;
    private AutorRepository repositorioAutores;
    private List<Libro> libros;
    private List<Autor> autores;

    // Constructor
    public Principal(LibroRepository repositorioLibros, AutorRepository repositorioAutores){
        this.repositorioLibros = repositorioLibros;
        this.repositorioAutores = repositorioAutores;
    }

    public void mostrarElMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                    1 - Buscar libros por título o autor
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorNombre();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivosEnUnDeterminadoAnio();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar el catálogo de libros");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private Datos obtenerDatosDeAPI(){
        System.out.println("Escriba el título del libro o autor a buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumirAPI.obtenerDatos(URL + nombreLibro.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibroPorNombre(){
        Datos datos = obtenerDatosDeAPI();
        if(datos.listaResultados().isEmpty()) {
            System.out.println("No se encontraron libros con ese nombre");
        }
        datos.listaResultados().stream()
                .limit(1)
                .forEach(l ->{
                    Libro libro = new Libro(l);
                    Autor autor = new Autor(l.autores());
                    autor.setLibro(libro);
                    libro.setAutores(List.of(autor));
                    System.out.println(libro);
                    try {
                        repositorioLibros.save(libro);

                    }catch (Exception e){
                        System.out.println("El libro ya está registrado");
                    }
                });
    }

    private void mostrarLibrosRegistrados(){
        libros = repositorioLibros.findAll();
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados");
        }else{
            libros.forEach(System.out::println);
        }
    }

    private void mostrarAutoresRegistrados(){
        autores = repositorioAutores.findAll();
        if(autores.isEmpty()){
            System.out.println("No hay autores registrados");
        }else{
            autores.forEach(System.out::println);
        }
    }

    private void mostrarAutoresVivosEnUnDeterminadoAnio(){
        System.out.println("Escriba el año a buscar: ");
        var anio = teclado.nextLine();
        autores = repositorioAutores.autoresVivos(anio);
        if(autores.isEmpty()){
            System.out.println("No hay autores registrados o vivos en ese año");
        }else{
            autores.forEach(System.out::println);
        }
    }

    private void mostrarLibrosPorIdioma(){
        System.out.println("Escriba el idioma a buscar: ");
        var idioma = teclado.nextLine();
        libros = repositorioLibros.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma");
        } else {
            libros.forEach(System.out::println);
        }
    }

}
