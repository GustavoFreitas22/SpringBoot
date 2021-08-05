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

import com.freitasdev.farmacia.model.Produto;
import com.freitasdev.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	@GetMapping("/all")
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id){
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/preco/{preco}")
	public ResponseEntity<List<Produto>> getByPreco(@PathVariable double preco){
		return ResponseEntity.ok(repositorio.findAllByPrecoContaining(preco));
	}
	
	@PostMapping("/post")
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(produto));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(produto));
	}
	
	@DeleteMapping("/{id}")
	public String deleteProd(@PathVariable long id) {
		repositorio.deleteById(id);
		return "Produto deletado com sucesso";
	}
}
