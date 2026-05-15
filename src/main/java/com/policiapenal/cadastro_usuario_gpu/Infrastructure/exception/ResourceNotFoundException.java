package com.policiapenal.cadastro_usuario_gpu.Infrastructure.exception;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
		
	}
	
	public ResourceNotFoundException(String mensagem,Throwable throwable) {
		super(mensagem,throwable);
		
	}
	

}
