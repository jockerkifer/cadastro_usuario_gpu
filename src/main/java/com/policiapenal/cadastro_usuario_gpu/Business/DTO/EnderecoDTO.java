package com.policiapenal.cadastro_usuario_gpu.Business.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {
	
	private Long id;
	private String logradouro;
	private String cep;
	private String estado;
	private String cidade;

}
