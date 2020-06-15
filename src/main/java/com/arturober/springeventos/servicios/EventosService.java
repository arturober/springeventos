package com.arturober.springeventos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.arturober.springeventos.entidades.Evento;
import com.arturober.springeventos.entidades.dto.EventoDto;
import com.arturober.springeventos.entidades.dto.InsertEventoDto;
import com.arturober.springeventos.repositorios.EventosRepository;
import com.arturober.springeventos.repositorios.UsersRepository;
import com.arturober.springeventos.utilidades.ImageUtils;

@Service
public class EventosService {
	@Autowired
	private EventosRepository eventosRepository;
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	public ImageUtils imageUtils;
	
	public List<Evento> findAll() {
		return (List<Evento>)eventosRepository.findAll();
	}
	
	public Evento find(Long id) throws NotFoundException {
		return eventosRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}
	
	public Evento insert(InsertEventoDto eventoDto, Long idCreator) {
		Evento evento = new Evento();
		evento.setName(eventoDto.getName());
		evento.setDate(eventoDto.getDate());
		evento.setDescription(eventoDto.getDescription());
		evento.setPrice(eventoDto.getPrice());
		evento.setImage(imageUtils.saveImageBase64("eventos", eventoDto.getImage()));
		evento.setCreator(usersRepository.findById(idCreator).get());
		
		return eventosRepository.save(evento);
	}
	
	public void delete(Long id) {
		eventosRepository.deleteById(id);
	}
}
