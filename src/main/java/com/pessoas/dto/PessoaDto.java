package com.pessoas.dto;

import com.pessoas.entidade.Pessoa;

public record PessoaDto(Long id,String nome, String cpf,String telefone,String email) {

	public PessoaDto(Pessoa cadastro) {
		this(cadastro.getId(),cadastro.getNome(),cadastro.getCpf(),cadastro.getTelefone(),cadastro.getEmail());
	}

}
