package com.example.eventos.repositorios;

import java.util.List;

import com.example.eventos.entidades.Evento;
import com.example.eventos.entidades.proyecciones.EventoConAsistentes;
import com.example.eventos.entidades.proyecciones.EventoSinAsistentes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends CrudRepository<Evento, Integer> {
    List<EventoSinAsistentes> findBy();
    EventoConAsistentes findConAsistentesById(int id);
}
