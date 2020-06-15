package com.arturober.springeventos.servicios;

import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arturober.springeventos.entidades.User;
import com.arturober.springeventos.entidades.dto.UserRegisterDto;
import com.arturober.springeventos.repositorios.UsersRepository;
import com.arturober.springeventos.utilidades.ImageUtils;
import com.arturober.springeventos.utilidades.SecurityUtils;

@Service
public class UsersService {
	@Autowired
	public UsersRepository usersRepository;
	@Autowired
	public ImageUtils imageUtils;
	@Autowired
	public SecurityUtils securityUtils;
	
	public User login(String email, String password) throws NoSuchAlgorithmException {
		return this.usersRepository.login(email, securityUtils.encodePassword(password));
	}
	
	public void register(UserRegisterDto userDto) throws NoSuchAlgorithmException {
		User user = new User();
		user.setAvatar(imageUtils.saveImageBase64("users", userDto.getAvatar()));
		user.setPassword(securityUtils.encodePassword(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		
		this.usersRepository.save(user);
	}
	
}
