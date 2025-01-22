package com.miniproyecto.catalogolibros.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaNacimiento;
    private String fechaDefuncion;

    // Un autor puede tener varios libros
    @ManyToOne
    private Libro libro;

    // Constructor por defecto
    public Autor() {
    }

    // Constructor personalizado
    public Autor(List<DatosAutor> autores) {
        autores.forEach(a -> {
            this.nombre = a.nombre();
            this.fechaNacimiento = a.fechaNacimiento();
            this.fechaDefuncion = a.fechaDefuncion();
        });
    }

    @Override
    public String toString() {
        return """
                ------ AUTOR -------
                Nombre: %s
                Fecha de nacimiento: %s
                Fecha de defunción: %s
                Libros: %s
                --------------------
                """.formatted(nombre, fechaNacimiento, fechaDefuncion, libro.getTitulo());
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
