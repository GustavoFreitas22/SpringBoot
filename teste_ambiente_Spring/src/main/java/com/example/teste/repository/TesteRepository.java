package com.example.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teste.model.testeModel;

@Repository
public interface TesteRepository extends JpaRepository<testeModel, Long>{
	// TODO fazer os metodos de consulta
	
	public List<testeModel> findAllByNomeContainingIgnoreCase(String nome);
	
}
