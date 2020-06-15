package com.arturober.springeventos.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arturober.springeventos.entidades.Evento;

@Repository
public interface EventosRepository extends CrudRepository<Evento, Long> {

}
