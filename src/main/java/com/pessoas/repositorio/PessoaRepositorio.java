package com.pessoas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoas.entidade.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Long> {

}
