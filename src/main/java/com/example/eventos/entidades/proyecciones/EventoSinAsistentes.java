package com.example.eventos.entidades.proyecciones;

import java.time.LocalDate;

public interface EventoSinAsistentes {
    int getId();
    String getTitulo();
    String getDescripcion();
    double getPrecio();
    LocalDate getFecha();
    String getImagen();
    void setImagen(String imagen);
}
