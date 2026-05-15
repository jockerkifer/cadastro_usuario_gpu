package com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="endereco")
@Builder
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="logradouro",length = 100)
	private String logradouro;
	@Column(name="cep",length = 9)
	private String cep;
	@Column(name="estado",length = 2)
	private String estado;
	@Column(name="cidade",length = 100)
	private String cidade;
	@Column(name = "usuario_id")
	private Long usuario_id;
}

