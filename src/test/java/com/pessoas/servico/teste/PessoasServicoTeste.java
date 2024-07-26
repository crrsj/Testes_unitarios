package com.pessoas.servico.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.pessoas.dto.PessoaDto;
import com.pessoas.entidade.Pessoa;
import com.pessoas.repositorio.PessoaRepositorio;
import com.pessoas.servico.PessoaServico;

@SpringBootTest
public class PessoasServicoTeste {
	
	private static final String EMAIL = "carlos@gmail.com";
	private static final String TELEFONE = "1234";
	private static final String CPF = "123";
	private static final String NOME = "Carlos";
	private static final long ID = 1L;
	private Pessoa pessoa;
	private PessoaDto pessoaDto;
	private Optional<Pessoa>optionalPessoa;
	
	@InjectMocks
	private PessoaServico pessoaServico;
	@Mock
	private PessoaRepositorio pessoaRepositorio;
	
	@BeforeEach
	void iniciar() {
		MockitoAnnotations.openMocks(this);
		iniciarClasses();
	}
    
	@DisplayName("Quando cadastrar pessoa retorne sucesso")
	@Test
	void quandoCadastrarPessoaRetorneSucesso() {
		when(pessoaRepositorio.save(pessoa)).thenReturn(pessoa);
		var resposta = pessoaServico.cadastrarPessoa(pessoaDto);
		assertNotNull(resposta);
		assertEquals(Pessoa.class, resposta.getClass());
		assertEquals(ID, resposta.getId());
		assertEquals(NOME, resposta.getNome());
		assertEquals(CPF, resposta.getCpf());
		assertEquals(TELEFONE, resposta.getTelefone());
		assertEquals(EMAIL, resposta.getEmail());
	}
	
	
	
	
	private void iniciarClasses() {
		
		pessoa = new Pessoa(ID,NOME,CPF,TELEFONE,EMAIL);		
		pessoaDto = new PessoaDto(ID,NOME,CPF,TELEFONE,EMAIL);
		optionalPessoa = Optional.of(new Pessoa(ID,NOME,CPF,TELEFONE,EMAIL));
	}
}
