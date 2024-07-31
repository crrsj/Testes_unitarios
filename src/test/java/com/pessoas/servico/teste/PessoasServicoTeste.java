package com.pessoas.servico.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
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
    class PessoasServicoTeste {
	
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
	
	@Test
	@DisplayName("Falha ao cadastrar pessoa")
	void falhaAoCadastrar() {
		doThrow(new RuntimeException()).when(pessoaRepositorio).save(pessoa);
		assertThrows(RuntimeException.class, ()-> pessoaServico.cadastrarPessoa(pessoaDto));
	}
	
	@Test
	@DisplayName("Quando buscar por id retorne sucesso")
	void quandoBuscarPorIdRetorneSucesso() {
		when(pessoaRepositorio.findById(anyLong())).thenReturn(optionalPessoa);
		var resposta = pessoaServico.buscarPorId(ID);
		assertNotNull(resposta);
		assertEquals(Pessoa.class, resposta.getClass());
		assertEquals(ID, resposta.getId());
		assertEquals(NOME, resposta.getNome());
		assertEquals(CPF, resposta.getCpf());
		assertEquals(TELEFONE, resposta.getTelefone());
		assertEquals(EMAIL, resposta.getEmail());
	}
	
	@Test
	@DisplayName("Falha ao buscar por id")
	void falhaAoBuscarPorId() {
		
		when(pessoaRepositorio.findById(anyLong())).thenThrow(new NoSuchElementException("ID não encontrado !"));
		
		try {
			pessoaServico.buscarPorId(ID);
		} catch (Exception e) {
			assertEquals(NoSuchElementException.class, e.getClass());
			assertEquals("ID não encontrado !", e.getMessage());
		}
	}
		
	@Test
	@DisplayName("Quando buscar Pessoas retorne sucesso")
	void quandoBuscarPessoasRetorneSucesso() {
		when(pessoaRepositorio.findAll()).thenReturn(List.of(pessoa));
		List<Pessoa>resposta = pessoaServico.ListarPessoas();
		 assertNotNull(resposta);
		 assertEquals(ID, resposta.get(0).getId());
		 assertEquals(NOME, resposta.get(0).getNome());
		 assertEquals(TELEFONE, resposta.get(0).getTelefone());
		 assertEquals(CPF, resposta.get(0).getCpf());
		 assertEquals(EMAIL, resposta.get(0).getEmail());
	}
	@Test
	@DisplayName("Sucesso ao excluir")
	void sucessoAoExcluir() {		
		doNothing().when(pessoaRepositorio).deleteById(anyLong());
		pessoaServico.excluir(ID);
		verify(pessoaRepositorio,times(1)).deleteById(ID);
		
	}
	
	private void iniciarClasses() {
		
		pessoa = new Pessoa(ID,NOME,CPF,TELEFONE,EMAIL);		
		pessoaDto = new PessoaDto(ID,NOME,CPF,TELEFONE,EMAIL);
		optionalPessoa = Optional.of(new Pessoa(ID,NOME,CPF,TELEFONE,EMAIL));
	}
}
