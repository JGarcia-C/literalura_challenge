package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private String numeroDescargas;
    @Embedded
    private Autor autor;


    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idioma = String.join(", ", datosLibros.idiomas());
        this.numeroDescargas = String.valueOf(datosLibros.numeroDescargas());
        if (datosLibros.autors() != null && !datosLibros.autors().isEmpty()) {
            this.autor = new Autor(datosLibros.autors().get(0));
        }
    }

    public Libro() {
    }

    @Override
    public String toString() {
        return "---------LIBRO---------\n" +
                "-Titulo:" + titulo + '\n' +
                "-Autor:" + (autor != null ? autor.toString() : "No especificado") + '\n' +
                "-Idioma:" + idioma + '\n' +
                "-Numero de Descargas:" + numeroDescargas + '\n';
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getNumeroDescargas() {
        return numeroDescargas;
    }
}
