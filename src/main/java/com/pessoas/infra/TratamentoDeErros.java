package com.pessoas.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pessoas.dto.MensagemDeErro;

@RestControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>idNaoEncontrado(){
		var erro = new MensagemDeErro(HttpStatus.NOT_FOUND, "ID não encontrado !");
		return new ResponseEntity<>(erro,HttpStatus.NOT_FOUND);
		
	}

}
