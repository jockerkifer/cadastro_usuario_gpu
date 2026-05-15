package com.policiapenal.cadastro_usuario_gpu.Business.Converter;

import java.util.List;
import org.springframework.stereotype.Component;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.EnderecoDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.TelefoneDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.UsuarioDTO;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Endereco;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Telefone;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Usuario;

@Component
public class UsuarioConverter {
	
	
	public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
		return Usuario.builder()
				.nome(usuarioDTO.getNome())
				.email(usuarioDTO.getEmail())
				.senha(usuarioDTO.getSenha())
				.enderecos(listarEndereco(usuarioDTO.getEnderecos()))
				.telefones(listarTelefone(usuarioDTO.getTelefones()))
				.build();
	}
	
	public Usuario autalizaDadosUsuario(UsuarioDTO usuarioDTO,Usuario usuario) {
		return Usuario.builder()
				.id(usuario.getId())
				.nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() :usuario.getNome())
				.email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getEmail())
				.senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
				.enderecos(usuario.getEnderecos())
				.telefones(usuario.getTelefones())
				.build();
		
	}
	
	public List<Endereco> listarEndereco(List<EnderecoDTO> enderecoDTO){
		return enderecoDTO.stream().map(this::paraEndereco).toList();
	}
	
	public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
		return Endereco.builder()
				.logradouro(enderecoDTO.getLogradouro())
				.cep(enderecoDTO.getCep())
				.estado(enderecoDTO.getEstado())
				.cidade(enderecoDTO.getCidade())
				.build();
	}
	
	public Endereco paraEnderecoSalvar(EnderecoDTO enderecoDTO, Long usuario_id) {
		return Endereco.builder()
				.logradouro(enderecoDTO.getLogradouro())
				.cep(enderecoDTO.getCep())
				.estado(enderecoDTO.getEstado())
				.cidade(enderecoDTO.getCidade())
				.usuario_id(usuario_id)
				.build();
		
	}
	
	public Endereco atualizaDadosEndereco(EnderecoDTO enderecoDTO,Endereco endereco) {
		return Endereco.builder()
				.id(endereco.getId())
				.logradouro(enderecoDTO.getLogradouro() != null ? enderecoDTO.getLogradouro() : endereco.getLogradouro())
				.cep(enderecoDTO.getCep() !=null ? enderecoDTO.getCep() : endereco.getCep())
				.estado(enderecoDTO.getEstado() !=null ? enderecoDTO.getEstado() : endereco.getEstado())
				.cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : endereco.getCidade())
				.build();
		
	}
	
	public List<Telefone> listarTelefone(List<TelefoneDTO> telefoneDTO){
		return telefoneDTO.stream().map(this::paraTelefone).toList();
	}
	
	public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
		return Telefone.builder()
				.ddd(telefoneDTO.getDdd())
				.numero(telefoneDTO.getNumero())
				.build();
	}
	
	public Telefone paraTelefoneSalvar(TelefoneDTO telefoneDTO, Long usuario_id) {
		return Telefone.builder()
				.ddd(telefoneDTO.getDdd())
				.numero(telefoneDTO.getNumero())
				.usuario_id(usuario_id)
				.build();
		
	}
	
	public Telefone atualizaDadosTelefone(TelefoneDTO telefoneDTO,Telefone telefone) {
		return Telefone.builder()
				.id(telefone.getId())
				.ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefone.getDdd())
				.numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefone.getNumero())
				.build();
	}
	
//	DTO para Entity
	
	public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
		return UsuarioDTO.builder()
				.nome(usuario.getNome())
				.email(usuario.getEmail())
				.senha(usuario.getSenha())
				.enderecos(listarEnderecoDTO(usuario.getEnderecos()))
				.telefones(listarTelefoneDTO(usuario.getTelefones()))
				.build();
	}
	
	public List<EnderecoDTO> listarEnderecoDTO(List<Endereco> endereco){
		return endereco.stream().map(this::paraEnderecoDTO).toList();
	}
	
	public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
		return EnderecoDTO.builder()
				.id(endereco.getId())
				.logradouro(endereco.getLogradouro())
				.cep(endereco.getCep())
				.estado(endereco.getEstado())
				.cidade(endereco.getCidade())
				.build();
	}
	
	public List<TelefoneDTO> listarTelefoneDTO(List<Telefone> telefone){
		return telefone.stream().map(this::paraTelefoneDTO).toList();
	}
	
	public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
		return TelefoneDTO.builder()
				.id(telefone.getId())
				.ddd(telefone.getDdd())
				.numero(telefone.getNumero())
				.build();
	}
}
