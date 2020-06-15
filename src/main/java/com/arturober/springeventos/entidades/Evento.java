package com.arturober.springeventos.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {
	private static final long serialVersionUID = -7391928595706040903L;

	@Id // Clave primaria y autogenerada en MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;
	
	@Column(length = 2000, nullable = false)
	private String description;
	
	@Column(length = 100, nullable = false)
	private String image;
	
	@Column(scale = 10, precision = 2, nullable = false)
	private double price;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creatorId")
	private User creator;

	public Evento() {
	}
	
	public Evento(Long id, String name, String description, String image, double price, LocalDate date, User creator) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.date = date;
		this.creator = creator;
	}
	
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

}
