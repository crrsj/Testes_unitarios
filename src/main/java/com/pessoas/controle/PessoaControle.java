package com.pessoas.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pessoas.dto.PessoaDto;

import com.pessoas.servico.PessoaServico;

@RestController
@RequestMapping("pessoa")
public class PessoaControle {

	@Autowired
	private PessoaServico pessoaServico;
	
	@PostMapping
	public ResponseEntity<PessoaDto>cadastrarPessoa(@RequestBody PessoaDto pessoa){
		var cadastro  = pessoaServico.cadastrarPessoa(pessoa);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
		.buildAndExpand(cadastro.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(cadastro));
	}
	@GetMapping
	public ResponseEntity<List<PessoaDto>>listarPessoas(){
		var listar  = pessoaServico.ListarPessoas().stream().map(PessoaDto::new).toList();
		return ResponseEntity.ok(listar);	
		
	}
}
