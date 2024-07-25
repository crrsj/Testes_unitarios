package com.pessoas.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pessoas.dto.PessoaDto;
import com.pessoas.entidade.Pessoa;
import com.pessoas.repositorio.PessoaRepositorio;

@Service
public class PessoaService {
	
	private PessoaRepositorio pessoaRepositorio;
	
	public Pessoa cadastrarPessoa(PessoaDto pessoa) {
		var cadastrar = new Pessoa(pessoa);
		return pessoaRepositorio.save(cadastrar);
		
	}
	
	public List<Pessoa>ListarPessoas(){
		return pessoaRepositorio.findAll();
	}

}
