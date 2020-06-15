package com.arturober.springeventos.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arturober.springeventos.entidades.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	@Query("select u from User u where email = ?1 and password = ?2")
	public User login(String email, String password);
}
