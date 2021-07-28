package com.example.teste.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/all")
	public ResponseEntity<List <testeModel>> getAll(){
		return ResponseEntity.ok(teste.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<testeModel> getById(@PathVariable long id){
		/**
		 *  Optional<testeModel> test = teste.findById(id);
		
		if(test.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(test.get());*/
		
		return teste.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	
	public ResponseEntity<List<testeModel>> GetByName(@PathVariable String nome){
		return ResponseEntity.ok(teste.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping("/cadastro")
	public ResponseEntity<testeModel> post (@RequestBody testeModel testeObj){
		return ResponseEntity.status(HttpStatus.CREATED).body(teste.save(testeObj));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<testeModel> put (@RequestBody testeModel testeObj){
		return ResponseEntity.status(HttpStatus.OK).body(teste.save(testeObj));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		teste.deleteById(id);
	}
}
