package com.arturober.springeventos.entidades.dto;

import java.util.List;

public class ResponseEventosDto {
	private List<EventoDto> eventos;

	public ResponseEventosDto(List<EventoDto> eventos) {
		this.eventos = eventos;
	}

	public List<EventoDto> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoDto> eventos) {
		this.eventos = eventos;
	}
}
