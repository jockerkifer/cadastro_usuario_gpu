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
public class TelefoneDTO {
	
	private Long id;
	private String ddd;
	private String numero;
}
	
	

