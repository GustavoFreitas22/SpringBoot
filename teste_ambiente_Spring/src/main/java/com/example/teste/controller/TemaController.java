package com.example.teste.controller;

import java.util.List;

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

import com.example.teste.model.Tema;
import com.example.teste.repository.temaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class TemaController {
	@Autowired
	private temaRepository repositorio;
	
	@GetMapping("/all")
	public ResponseEntity<List<Tema>> getAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable long id){
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/desc/{descricao}")
	public ResponseEntity<List<Tema>> getByDesc(@PathVariable String descricao){
		return ResponseEntity.ok(repositorio.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping("/post")
	public ResponseEntity<Tema> post(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(tema));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Tema> put (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(tema));
	}
	
	@DeleteMapping("/remove/{id}")
	public String delete(@PathVariable long id) {
		repositorio.deleteById(id);
		
		return "Tema excluido com sucesso!";
	}
}
