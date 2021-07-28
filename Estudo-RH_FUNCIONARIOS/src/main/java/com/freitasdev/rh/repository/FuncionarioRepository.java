package com.freitasdev.rh.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitasdev.rh.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	public List<Funcionario>findAllByNomeContainingIgnoreCase(String nome); // Query de banco de dados
}
