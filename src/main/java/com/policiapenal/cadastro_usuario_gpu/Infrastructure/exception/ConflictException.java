package com.policiapenal.cadastro_usuario_gpu.Infrastructure.exception;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException {

	public ConflictException(String mensagem) {
		super(mensagem);
	}
	
	public ConflictException(String mensagem,Throwable throwable) {
		super(mensagem,throwable);
	}
}
