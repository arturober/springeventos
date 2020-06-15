package com.arturober.springeventos.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = -4674580525423373907L;
	
	@Id // Clave primaria y autogenerada en MySQL
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(length = 150, nullable = false)
	private String name;
	
	@Column(length = 150, nullable = false, unique = true)
	private String email;
	
	@Column(length = 150, nullable = false)
	private String password;
	
	@Column(length = 150, nullable = false)
	private String avatar;
	
	@OneToMany(mappedBy="creator")
	private List<Evento> eventos;
	
	public User() {}

	public User(Long id, String name, String email, String password, String avatar, List<Evento> eventos) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.eventos = new ArrayList<Evento>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
