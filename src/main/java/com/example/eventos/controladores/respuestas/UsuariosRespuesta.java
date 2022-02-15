package com.example.eventos.controladores.respuestas;

import java.util.List;

import com.example.eventos.entidades.proyecciones.UsuarioSinEventos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class UsuariosRespuesta {
    private List<UsuarioSinEventos> usuarios;
}
