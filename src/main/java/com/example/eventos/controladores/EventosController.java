package com.example.eventos.controladores;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import com.example.eventos.controladores.respuestas.EventoRespuesta;
import com.example.eventos.controladores.respuestas.EventosRespuesta;
import com.example.eventos.entidades.Evento;
import com.example.eventos.entidades.proyecciones.EventoConAsistentes;
import com.example.eventos.entidades.proyecciones.EventoSinAsistentes;
import com.example.eventos.servicios.EventosService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javassist.NotFoundException;

@RestController
@RequestMapping("/eventos")
public class EventosController {
    private final EventosService eventosService; 

    public EventosController(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @GetMapping
    public EventosRespuesta getAll() {
        List<Evento> eventos = eventosService.getAll()
            .stream()
            .map(e -> {
                Evento evento = new Evento(e);
                evento.setImagen(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + evento.getImagen());
                return evento;
            })
            .collect(Collectors.toList());
        return new EventosRespuesta(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoRespuesta> getEvento(@PathVariable int id) {
        EventoConAsistentes evento = eventosService.getEvento(id);
        if(evento != null) {
            evento.setImagen(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + evento.getImagen());
            EventoRespuesta respuesta = new EventoRespuesta(evento);
            return ResponseEntity.ok(respuesta); // 200
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
        
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento insertEvento(@RequestBody @Valid Evento evento) {
        Evento e = eventosService.insertEvento(evento);
        e.setImagen(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + e.getImagen());
        return e;
    }

    @PostMapping("/{idEvento}/asistir")
    public ResponseEntity<Void> asistirEvento(Authentication authentication, @PathVariable int idEvento) {
        int idUsuario = (Integer)authentication.getCredentials();
        try {
            eventosService.asistirEvento(idEvento, idUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEvento}/asistir")
    public ResponseEntity<Void> noAsistirEvento(Authentication authentication, @PathVariable int idEvento) {
        int idUsuario = (Integer)authentication.getCredentials();
        try {
            eventosService.noAsistirEvento(idEvento, idUsuario);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
