package com.example.teste.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.teste.model.Usuario;
import com.example.teste.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepositorio;
	
	@Override
	public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException{
		Optional<Usuario> user = userRepositorio.findByUserName(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "NOT FOUND."));
		
		return user.map(UserDetailsImpl::new).get();
	}
}
