package com.arturober.springeventos.entidades.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.arturober.springeventos.entidades.User;

public class UserDto implements Serializable {
    private Long id;
	private String name;
	private String email;
	private String avatar;
	
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
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public static UserDto fromEntity(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setAvatar(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + '/' + user.getAvatar());
		
		return dto;
	}
}
