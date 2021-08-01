package com.freitasdev.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freitasdev.games.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//public List<Produto>findAllPrecoContainingIgnoreCase(double preco);
}
