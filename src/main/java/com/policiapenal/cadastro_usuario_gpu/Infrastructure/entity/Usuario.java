package com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuario")
@Builder
public class Usuario implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome",length = 100)
	private String nome;
	@Column(name="email",length = 100)
	private String email;
	@Column(name="senha")
	private String senha;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_id",referencedColumnName = "id")
	private List<Endereco> enderecos;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_id",referencedColumnName = "id")
	private List<Telefone> telefones;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}
	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

}
