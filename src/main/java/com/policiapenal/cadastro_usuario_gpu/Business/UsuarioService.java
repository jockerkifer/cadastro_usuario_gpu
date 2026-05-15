package com.policiapenal.cadastro_usuario_gpu.Business;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.policiapenal.cadastro_usuario_gpu.Business.Converter.UsuarioConverter;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.EnderecoDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.TelefoneDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.UsuarioDTO;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Endereco;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Telefone;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Usuario;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.exception.ConflictException;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.exception.ResourceNotFoundException;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.repository.EnderecoRepository;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.repository.TelefoneRepository;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.repository.UsuarioRepository;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final EnderecoRepository enderecoRepository;
	private final TelefoneRepository telefoneRepository;
	private final UsuarioConverter usuarioConverter;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	
	public Usuario salvarDados(UsuarioDTO usuarioDTO) {
		emailEncontrado(usuarioDTO.getEmail());
		usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
		Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
		return usuarioRepository.save(usuario);
		
	}
	
	public void emailEncontrado(String email) {
		try {
			boolean existe = buscarEmail(email);
			if(existe) {
				throw new ConflictException("Email encontrado!!! "+email);
			}
			
		}catch(ConflictException e) {
			throw new ConflictException("Email não localizado!!! "+e.getCause());
		}
	}
	
	public boolean buscarEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public UsuarioDTO listarUsuarioEmail(String email){
		
		try {
			return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(() -> new  ResourceNotFoundException("Email não localizado!!! "+email)));
			
		}catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Email não localizado!!! "+email);
			
		}
		
	}
	
	public EnderecoDTO salvarDadosEndereco(String token , EnderecoDTO enderecoDTO) {
		String email = jwtUtil.extractUsername(token.substring(7));
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new  ResourceNotFoundException("Email não localizado!!! "+email));
		Endereco endereco = usuarioConverter.paraEnderecoSalvar(enderecoDTO, usuario.getId());
		return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
	}
	
	public TelefoneDTO salvarDadosTelefone(String token,TelefoneDTO telefoneDTO ) {
		String email = jwtUtil.extractUsername(token.substring(7));
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new  ResourceNotFoundException("Email não localizado!!! "+email));
		Telefone telefone = usuarioConverter.paraTelefoneSalvar(telefoneDTO, usuario.getId());
		return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
	}
	
	public UsuarioDTO autalizaDadosUsuario(String token , UsuarioDTO usuarioDTO) {
		String email = jwtUtil.extractUsername(token.substring(7));
		usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()): null);
		Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("email não encontrado!!! "+email));
		usuario = usuarioConverter.autalizaDadosUsuario(usuarioDTO, usuario);
		return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
	}
	
	public EnderecoDTO atualizaDadosEndereco(Long id, EnderecoDTO enderecoDTO) {
		Endereco endEntity = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não localizado!!! " +id));
		Endereco endereco = usuarioConverter.atualizaDadosEndereco(enderecoDTO, endEntity);
		return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
	}
	
	public TelefoneDTO atualizaDadosTelefone(Long id , TelefoneDTO telefoneDTO) {
		Telefone telefone = telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não localizado!!! " +id));
		telefone = usuarioConverter.atualizaDadosTelefone(telefoneDTO, telefone);
		return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
	}
			
	public void deletarUsuarioEmail(String email) {
		usuarioRepository.deleteByEmail(email);
	}
}