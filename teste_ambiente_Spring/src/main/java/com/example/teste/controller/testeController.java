package com.example.teste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.model.testeModel;
import com.example.teste.repository.TesteRepository;

@RestController
@RequestMapping("/teste")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class testeController {
	
	@Autowired // Faz as funcionalidades da interface instaciarem automaticamente
	private TesteRepository teste;
	
	@GetMapping
	public ResponseEntity<List <testeModel>> getAll(){
		return ResponseEntity.ok(teste.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<testeModel> getById(@PathVariable long id){
		/** Optional<testeModel> test = teste.findById(id);
		
		if(test.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(test.get());*/
		
		return teste.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	/*@PostMapping
	public ResponseEntity<testeModel> post(@Valid @RequestBody coisa){
		
	}*/
}
