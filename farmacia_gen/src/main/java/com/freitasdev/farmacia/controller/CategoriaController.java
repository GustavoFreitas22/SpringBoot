package com.freitasdev.farmacia.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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

import com.freitasdev.farmacia.model.Categoria;
import com.freitasdev.farmacia.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repositorio;
	
	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable long id){
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> getByDesc(@PathVariable String descricao){
		return ResponseEntity.ok(repositorio.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping("/create")
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(categoria));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable long id) {
		repositorio.deleteById(id);
		return "Categoria deleta com sucesso";
	}
}
