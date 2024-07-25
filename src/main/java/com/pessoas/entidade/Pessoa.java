package com.pessoas.entidade;

import com.pessoas.dto.PessoaDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pessoas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    
    public Pessoa(PessoaDto pessoa) {
		this.id = pessoa.id();
		this.nome = pessoa.nome();
		this.cpf = pessoa.cpf();
		this.telefone = pessoa.telefone();
		this.email = pessoa.email();
		
	}
	
}
