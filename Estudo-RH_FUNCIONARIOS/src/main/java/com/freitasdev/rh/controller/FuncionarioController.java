package com.freitasdev.rh.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freitasdev.rh.entity.Funcionario;
import com.freitasdev.rh.repository.FuncionarioRepository;

@RestController
@RequestMapping("/func")
@CrossOrigin("*")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> getAll(){
		return ResponseEntity.ok(repositorio.findAll());
	}
}
