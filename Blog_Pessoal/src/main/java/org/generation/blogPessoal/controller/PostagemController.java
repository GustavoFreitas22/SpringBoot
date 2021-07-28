package org.generation.blogPessoal.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.JpaRepositoryNameSpaceHandler;
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

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	@Autowired
	private PostagemRepository repositorio;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable long id){ 
		return repositorio.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		/**
		 * FindById retorna um OPTIONAL
		 * Por conta disso temos que maperar o retorno (Usando o ".map()")
		 * Dentro do map passamos uma Lambda (Quase igual uma callback do JS) ela diz que se a reposta foi OK(200) ira retornar o Objeto
		 * Se a opção não for encontrada ela retorna um NOT FOUND(404)
		 * O .build serve para montar a resposta da requisição no body*/		
				
	}
	
	@GetMapping("/title/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitle(@PathVariable String titulo){
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
		
		/**
		 * Nesse caso o metodo precisa ser uma List, para conseguir titulos iguais
		 * */
	}
	
	@PostMapping("/post")
	public ResponseEntity<Postagem> post(@RequestBody Postagem post){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(post));
		
		/**
		 * O @RequestBody faz com que os dados consigam ser capturados do body da aplicação
		 * 
		 * Da mesma forma retornamos um Status HTTP para (CREATED) o sucesso
		 * Passamos o parametro para o repositorio e usamos o " .save " para criar no banco de dados.
		 * */
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Postagem> put (@RequestBody Postagem edit){
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(edit));
		
		/**
		 * Como vamos editar, passamos dos dados como ID e retornamos um 200(Ok)
		 * 
		 * Assim como no post usamos o repositorio.save() para enviar os dados a serem salvos para o banco de dados*/
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id) {
		repositorio.deleteById(id);
		
		/**
		 * Essa função não ira retornar nada, apenas ira apagar o ID passado na URL
		 * */
	}
}
