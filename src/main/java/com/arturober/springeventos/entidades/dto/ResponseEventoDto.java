package com.arturober.springeventos.entidades.dto;

public class ResponseEventoDto {
	private EventoDto evento;

	public ResponseEventoDto(EventoDto evento) {
		this.evento = evento;
	}

	public EventoDto getEvento() {
		return evento;
	}

	public void setEvento(EventoDto evento) {
		this.evento = evento;
	}
}
