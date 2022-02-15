package com.example.eventos.controladores.respuestas;

import java.util.List;

import com.example.eventos.entidades.Evento;
import com.example.eventos.entidades.proyecciones.EventoSinAsistentes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class EventosRespuesta {
    List<Evento> eventos;    
}
