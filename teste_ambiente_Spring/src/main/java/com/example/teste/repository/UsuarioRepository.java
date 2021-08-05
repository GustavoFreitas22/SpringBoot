package com.example.teste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.teste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUserName (String userName);
}
