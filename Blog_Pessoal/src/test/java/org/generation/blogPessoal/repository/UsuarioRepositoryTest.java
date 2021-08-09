package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.AssertTrue;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Para falar que são testes e diz que pode abrir em qualquer porta
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Vai testar classes
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repositorioTest;
	
	@BeforeAll // isso aqui vai acontcer antes de eu testar
	public void start () {
		LocalDate data = LocalDate.parse("2000-08-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		  Usuario usuario = new Usuario(0, "João da Silva", "joao@email.com.br", "13465278", data);
		    if(repositorioTest.findByUsuario(usuario.getUsuario()).isEmpty())
		    	repositorioTest.save(usuario);
		    
		    usuario = new Usuario(0, "Frederico da Silva", "frederico@email.com.br", "13465278", data);
			if(!repositorioTest.findByUsuario(usuario.getUsuario()).isPresent())
				repositorioTest.save(usuario);

	        usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278", data);
	        if(!repositorioTest.findByUsuario(usuario.getUsuario()).isPresent())
	            repositorioTest.save(usuario);


	}
	
	@Test
	@DisplayName("Retorna o nome")
	public void findBynomeRetornaNome() {
		Usuario user = repositorioTest.findByNome("João da Silva");
		assertTrue(user.getNome().equals("João da Silva"));
	}
	
	@AfterAll
	public void end() {
		System.out.println("Fim dos testes");
	}
}
