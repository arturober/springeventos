package com.arturober.springeventos.entidades.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arturober.springeventos.entidades.Evento;
import com.arturober.springeventos.entidades.User;

public class EventoDto implements Serializable {
	private Long id;
	private String name;
	private String description;
	private String image;
	private double price;
	private LocalDate date;
	private UserDto creator;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public UserDto getCreator() {
		return creator;
	}
	public void setCreator(UserDto creator) {
		this.creator = creator;
	}
	
	public static EventoDto fromEntity(Evento evento) {
		EventoDto dto = new EventoDto();
		dto.setDate(evento.getDate());
		dto.setId(evento.getId());
		dto.setDescription(evento.getDescription());
		dto.setName(evento.getName());
		dto.setPrice(evento.getPrice());
		dto.setImage(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + evento.getImage());
		dto.setCreator(UserDto.fromEntity(evento.getCreator()));
		
		return dto;
	}
}
