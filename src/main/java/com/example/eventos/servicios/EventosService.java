package com.example.eventos.servicios;

import java.util.List;

import javax.transaction.Transactional;

import com.example.eventos.entidades.Evento;
import com.example.eventos.entidades.Usuario;
import com.example.eventos.entidades.proyecciones.EventoConAsistentes;
import com.example.eventos.entidades.proyecciones.EventoSinAsistentes;
import com.example.eventos.repositorios.EventosRepository;
import com.example.eventos.repositorios.UsuariosRepository;
import com.example.eventos.utils.ImageUtils;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class EventosService {
    private final EventosRepository eventosRepo;
    private final UsuariosRepository usuariosRepo;
    private final ImageUtils imageUtils;

    public EventosService(EventosRepository eventosRepo, UsuariosRepository usuariosRepo, ImageUtils imageUtils) {
        this.eventosRepo = eventosRepo;
        this.usuariosRepo = usuariosRepo;
        this.imageUtils = imageUtils;
    }

    public List<EventoSinAsistentes> getAll() {
        return eventosRepo.findBy();
    }

    public EventoConAsistentes getEvento(int id) {
        return eventosRepo.findConAsistentesById(id);
    }

    public Evento insertEvento(Evento evento) {
        String ruta = imageUtils.saveImageBase64("eventos", evento.getImagen());
        evento.setImagen(ruta);
        return eventosRepo.save(evento);
    }

    public void asistirEvento(int idEvento, int idUsuario) throws NotFoundException {
        Evento ev = eventosRepo.findById(idEvento).orElse(null);
        if(ev == null) {
            throw new NotFoundException("Evento no encontrado");
        }
        if(!ev.getAsistentes().stream().filter(u -> u.getId() == idUsuario).findFirst().isPresent()) {
            Usuario u = usuariosRepo.findById(idUsuario).get();
            ev.addUsuario(u);
            eventosRepo.save(ev);
        }

    }

    public void noAsistirEvento(int idEvento, int idUsuario) throws NotFoundException {
        Evento ev = eventosRepo.findById(idEvento).orElse(null);
        if(ev == null) {
            throw new NotFoundException("Evento no encontrado");
        }
        Usuario u = usuariosRepo.findById(idUsuario).get();
        ev.removeUsuario(u);
        eventosRepo.save(ev);
    }

}
