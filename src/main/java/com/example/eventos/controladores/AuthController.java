package com.example.eventos.controladores;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.example.eventos.controladores.peticiones.LoginData;
import com.example.eventos.controladores.respuestas.TokenRespuesta;
import com.example.eventos.entidades.Usuario;
import com.example.eventos.seguridad.SecurityConstants;
import com.example.eventos.servicios.UsuariosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private UsuariosService usuariosService;

    public AuthController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenRespuesta> postMethodName(@RequestBody @Valid LoginData loginData) throws NoSuchAlgorithmException {
        Usuario usuario = usuariosService.login(loginData);
		
		if(usuario != null) { // Login OK
			return ResponseEntity.ok().body(new TokenRespuesta(getToken(usuario)));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid Usuario usuario) throws NoSuchAlgorithmException {
        usuariosService.insert(usuario);
    }

    private String getToken(Usuario user) {	
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("id", user.getId());
		data.put("correo", user.getCorreo());
		data.put("authorities", Arrays.asList("ROLE_USER"));
		
		String token = Jwts.builder().setId("springEventos")
				.setSubject(user.getNombre()).addClaims(data)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30*24*60*60)) // Caduca en un mes
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY).compact();
		
		return token;
	}
}
