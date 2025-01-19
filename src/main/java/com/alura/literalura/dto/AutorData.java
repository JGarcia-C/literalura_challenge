package com.alura.literalura.dto;

import java.util.ArrayList;
import java.util.List;

public class AutorData {
    private final String nombre;
    private final int fechaNacimiento;
    private final int fechaDefuncion;
    private final List<String> libros;

    public AutorData(String nombre, int fechaNacimiento, int fechaDefuncion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDefuncion = fechaDefuncion;
        this.libros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getFechaDefuncion() {
        return fechaDefuncion;
    }

    public List<String> getLibros() {
        return libros;
    }

    public void agregarLibro(String titulo) {
        libros.add(titulo);
    }
}


