package com.example.eventos.controladores.respuestas;

import com.example.eventos.entidades.proyecciones.EventoConAsistentes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class EventoRespuesta {
    private EventoConAsistentes evento;
}
