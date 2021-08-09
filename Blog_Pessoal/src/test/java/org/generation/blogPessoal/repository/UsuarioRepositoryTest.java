package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Para falar que o porjeto deve ser aberto em ambiente de testes e diz que pode abrir em qualquer porta
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Vai testar a classe toda de um vez, invez de metodo por metodo
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repositorioTest;
	
	@BeforeAll // isso aqui vai acontcer antes de todos os testes rodarem
	public void start () { // Method para criar dados no banco
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
	
	@Test // indica que será executado como um teste
	@DisplayName("Retorna o nome") // modifica o nome do teste no display
	public void findBynomeRetornaNome() {
		Usuario user = repositorioTest.findByNome("João da Silva");
		assertTrue(user.getNome().equals("João da Silva")); // assertTrue afirma que a condição é verdadeira
	}
	
	@Test
	@DisplayName("Quantidades de objetos")
	public void findUserNames() {
		List<Usuario> users = repositorioTest.findAllByNomeContainingIgnoreCase("Silva");
		
		assertEquals(2, users.size());
	}
	
	@AfterAll
	public void end() {
		System.out.println("Fim dos testes");
	}
}
