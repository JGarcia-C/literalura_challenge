package com.alura.literalura.service;

public interface DTOConvertidos {
    <T> T obtenerDatos(String jason, Class<T> clase);
}
