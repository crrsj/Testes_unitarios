package com.pessoas.servico;

import java.util.List;
import java.util.Optional;

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

	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa>buscar = pessoaRepositorio.findById(id);
		return buscar.get();
	}
	
	public Pessoa atualizarCadastro(PessoaDto pessoa,Long id) {
		var atualizar = new Pessoa(pessoa);
		atualizar.setId(id);
		return pessoaRepositorio.save(atualizar);
	}
	
	public void excluir(Long id) {
		pessoaRepositorio.deleteById(id);
	}
}
