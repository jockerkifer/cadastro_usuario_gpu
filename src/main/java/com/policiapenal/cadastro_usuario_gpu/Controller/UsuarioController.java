package com.policiapenal.cadastro_usuario_gpu.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.policiapenal.cadastro_usuario_gpu.Business.UsuarioService;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.EnderecoDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.TelefoneDTO;
import com.policiapenal.cadastro_usuario_gpu.Business.DTO.UsuarioDTO;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.entity.Usuario;
import com.policiapenal.cadastro_usuario_gpu.Infrastructure.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<Usuario> salvarDados(@RequestBody UsuarioDTO usuarioDTO) {
		return ResponseEntity.ok(usuarioService.salvarDados(usuarioDTO));
		
	}
	
	@PostMapping("/login")
	public String login(@RequestBody UsuarioDTO usuarioDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha()));
		return "Bearer " +jwtUtil.generateToken(authentication.getName()); 
	}
	
		
	@GetMapping
	public ResponseEntity<UsuarioDTO> listarUsuarioEmail(@RequestParam("email") String  email ) {
		return ResponseEntity.ok(usuarioService.listarUsuarioEmail(email));
	}
	
	@PutMapping
	public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTO usuarioDTO ,@RequestHeader("Authorization") String token){
		return ResponseEntity.ok(usuarioService.autalizaDadosUsuario(token, usuarioDTO));
	}
	
	@PutMapping("/endereco")
	public ResponseEntity<EnderecoDTO> atualizaDadosEndereco(@RequestParam("id") Long id ,@RequestBody EnderecoDTO enderecoDTO){
		return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(id, enderecoDTO));
		
	}
	
	@PutMapping("/telefone")
	public ResponseEntity<TelefoneDTO> atualizaDadosTelefone(@RequestParam("id") Long id ,@RequestBody TelefoneDTO telefoneDTO){
		return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id, telefoneDTO));
		
	}
			
	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deletarUsuarioEmail(@PathVariable("email") String email){
		usuarioService.deletarUsuarioEmail(email);
		return ResponseEntity.ok().build();
	}
}
