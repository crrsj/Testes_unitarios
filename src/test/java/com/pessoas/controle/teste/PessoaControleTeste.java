package com.pessoas.controle.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pessoas.controle.PessoaControle;
import com.pessoas.dto.PessoaDto;
import com.pessoas.entidade.Pessoa;
import com.pessoas.servico.PessoaServico;

    @SpringBootTest
    class PessoaControleTeste {
	
	private static final String EMAIL = "carlos@gmail.com";
	private static final String TELEFONE = "1234";
	private static final String CPF = "123";
	private static final String NOME = "Carlos";
	private static final long ID = 1L;
	private Pessoa pessoa;
	private PessoaDto pessoaDto;
	
	
	@Mock
	private PessoaServico pessoaServico;
	
	@InjectMocks
	private PessoaControle pessoaControle;
	
	
	@BeforeEach
	void iniciar() {
		MockitoAnnotations.openMocks(this);
		iniciarClasses();
	
	}
	
	@Test
	@DisplayName("Quando cadastrar pessoa retorne sucesso")
	void quandoCadastrarPessoaRetorneSucesso() {
		when(pessoaServico.cadastrarPessoa(pessoaDto)).thenReturn(pessoa);
		ResponseEntity<PessoaDto> resposta = pessoaControle.cadastrarPessoa(pessoaDto);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertNotNull(resposta.getHeaders().get("Location"));
	}
	@Test
	@DisplayName("Quando buscar por id retorne sucesso")
	void quandoBuscarPorIdRetorneSucesso() {
		when(pessoaServico.buscarPorId(anyLong())).thenReturn(pessoa);
		ResponseEntity<PessoaDto>resposta = pessoaControle.buscarPorId(ID);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(PessoaDto.class, resposta.getBody().getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ID, resposta.getBody().id());
		assertEquals(NOME, resposta.getBody().nome());
		assertEquals(CPF, resposta.getBody().cpf());
		assertEquals(TELEFONE, resposta.getBody().telefone());
		assertEquals(EMAIL, resposta.getBody().email());
	}
	
	@Test
	@DisplayName("Quando buscar todos retorne sucesso")
	void quandoBuscarTodosRetorneSucesso() {
		when(pessoaServico.ListarPessoas()).thenReturn(List.of(pessoa));		
		ResponseEntity<List<PessoaDto>>resposta = pessoaControle.listarPessoas();
		assertNotNull(resposta);
		assertNotNull(resposta.getBody());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ResponseEntity.class, resposta.getClass());		
		assertEquals(PessoaDto.class, resposta.getBody().get(0).getClass());
		assertEquals(ID, resposta.getBody().get(0).id());
		assertEquals(NOME, resposta.getBody().get(0).nome());
		assertEquals(TELEFONE, resposta.getBody().get(0).telefone());
		assertEquals(CPF, resposta.getBody().get(0).cpf());
		assertEquals(EMAIL, resposta.getBody().get(0).email());
	}
	
	@Test
	@DisplayName("Quando atualizar retorne sucesso")
	void quandoAtualizarRetorneSucesso() {
		when(pessoaServico.atualizarCadastro(pessoaDto,ID)).thenReturn(pessoa);
		ResponseEntity<PessoaDto>resposta = pessoaControle.atualizarCadastro(pessoaDto, ID);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(PessoaDto.class, resposta.getBody().getClass());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(ID, resposta.getBody().id());
		assertEquals(NOME, resposta.getBody().nome());
		assertEquals(CPF, resposta.getBody().cpf());
		assertEquals(TELEFONE, resposta.getBody().telefone());
		assertEquals(EMAIL, resposta.getBody().email());
	}
	
	@Test
	@DisplayName("excluir com sucesso")
	void excluirComSucesso() {
		doNothing().when(pessoaServico).excluir(anyLong());
		ResponseEntity<Void> resposta = pessoaControle.excluir(ID);
		assertNotNull(resposta);
		assertEquals(ResponseEntity.class, resposta.getClass());
		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
		verify(pessoaServico,times(1)).excluir(anyLong());
		
		
	}
	
	private void iniciarClasses() {
		
		pessoa = new Pessoa(ID,NOME,CPF,TELEFONE,EMAIL);		
		pessoaDto = new PessoaDto(ID,NOME,CPF,TELEFONE,EMAIL);
		
	
}
}
