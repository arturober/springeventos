package com.example.eventos.servicios;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import com.example.eventos.controladores.peticiones.LoginData;
import com.example.eventos.entidades.Usuario;
import com.example.eventos.entidades.proyecciones.UsuarioSinEventos;
import com.example.eventos.repositorios.UsuariosRepository;

import org.springframework.stereotype.Service;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepo;

    public UsuariosService(UsuariosRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    public Usuario login(LoginData data) throws NoSuchAlgorithmException {
        return usuariosRepo.findFirstByCorreoAndPassword(data.getCorreo(), encodePassword(data.getPassword()));
    }

    public List<UsuarioSinEventos> getAll() {
        return usuariosRepo.findBy();
    }

    public Usuario insert(Usuario usuario)  throws NoSuchAlgorithmException {
        usuario.setPassword(encodePassword(usuario.getPassword()));
        return usuariosRepo.save(usuario);
    }

    public void delete(int id) {
        usuariosRepo.deleteById(id);
    }

    private String encodePassword(String pass) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
		String encodedPass = Base64.getEncoder().encodeToString(hash);
		return encodedPass;
	}
}
