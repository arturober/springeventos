package com.example.eventos.repositorios;

import java.util.List;

import com.example.eventos.entidades.Usuario;
import com.example.eventos.entidades.proyecciones.UsuarioSinEventos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuario, Integer> {
    List<UsuarioSinEventos> findBy();
    Usuario findFirstByCorreoAndPassword(String correo, String password);
}
