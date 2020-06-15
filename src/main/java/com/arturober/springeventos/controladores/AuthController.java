package com.arturober.springeventos.controladores;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arturober.springeventos.entidades.User;
import com.arturober.springeventos.entidades.dto.UserLoginDto;
import com.arturober.springeventos.entidades.dto.UserRegisterDto;
import com.arturober.springeventos.security.SecurityConstants;
import com.arturober.springeventos.servicios.UsersService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserLoginDto userLogin) throws NoSuchAlgorithmException {
		Map<String, Object> resp = new HashMap<>();
		
		User user = usersService.login(userLogin.getEmail(), userLogin.getPassword());
		
		if(user != null) {
			resp.put("accessToken", getToken(user));
			return ResponseEntity.ok().body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody UserRegisterDto userDto) throws NoSuchAlgorithmException {
		usersService.register(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null); 
	}
	
	@GetMapping("/validate")
	public void validateToken() {}
	
	private String getToken(User user) {	
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", user.getId());
		data.put("email", user.getEmail());
		data.put("authorities", Arrays.asList("ROLE_USER"));
		
		String token = Jwts.builder().setId("springEventos")
				.setSubject(user.getName()).addClaims(data)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
		
		return token;
	}
	
}
