package com.alura.literalura.model;

import jakarta.persistence.*;

@Embeddable
public class Autor {
    private String nombre;
    private int fechaNacimiento;
    private int fechaDefuncion;

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento() != null ?  Integer.parseInt(datosAutor.fechaNacimiento()) : 0;
        this.fechaDefuncion = datosAutor.fechaDefuncion() != null ? Integer.parseInt(datosAutor.fechaDefuncion()) : 0;
    }

    public Autor() {
    }

    @Override
    public String toString() {
        return  nombre + '\n' +
                "-Año de Nacimiento:" + fechaNacimiento + '\n' +
                "-Año de Defuncion:" + fechaDefuncion;
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
}
