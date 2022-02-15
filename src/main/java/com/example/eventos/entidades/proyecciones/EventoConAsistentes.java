package com.example.eventos.entidades.proyecciones;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public interface EventoConAsistentes {
    int getId();
    String getTitulo();
    String getDescripcion();
    double getPrecio();
    LocalDate getFecha();
    String getImagen();

    void setImagen(String imagen);

    List<UsuarioSinEventos> getAsistentes();

    @Value("#{target.asistentes.size()}")
    int getNumAsistentes();
    
    // default int getNumAsistentes() {
    //     return getAsistentes().size();
    // }
}
