package com.example.teste.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.teste.model.UserLogin;
import com.example.teste.model.Usuario;
import com.example.teste.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;
	
	public Usuario CadastrarUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repositorio.save(usuario);
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<Usuario> usuario = repositorio.findByUserName(user.get().getUserName());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUserName()+":"+ user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeadres= "Basic" + new String(encodedAuth);
				
				user.get().setToken(authHeadres);
				user.get().setNome(usuario.get().getNome());
				
				return user;
			}
		}
		return null;
	}
}
