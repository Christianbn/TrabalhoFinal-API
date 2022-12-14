package com.example.tf.login.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tf.config.MailConfig;
import com.example.tf.exception.EmailException;
import com.example.tf.exception.SenhaException;
import com.example.tf.login.domain.Perfil;
import com.example.tf.login.domain.Usuario;
import com.example.tf.login.domain.UsuarioPerfil;
import com.example.tf.login.dto.UsuarioDTO;
import com.example.tf.login.dto.UsuarioInserirDTO;
import com.example.tf.login.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	@Autowired
	private MailConfig mailConfig;
	
	@Autowired
	private PerfilService perfilService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public List<UsuarioDTO> lista(){
		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Usuario: " + userDetail.getUsername() + " - " + userDetail.getPassword());
	       		
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for(int i = 0 ; i<usuarios.size(); i++) {
		    Usuario usuario = usuarios.get(i);
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		
		//return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
		return usuariosDTO;
	}
	
	@Transactional
	public UsuarioDTO inserir(UsuarioInserirDTO usuarioInserirDTO) throws EmailException, SenhaException {
		if (!usuarioInserirDTO.getSenha().equals(usuarioInserirDTO.getConfirmaSenha())) {
			throw new SenhaException();
		}
		Usuario usuarioBanco = usuarioRepository.findByEmail(usuarioInserirDTO.getEmail());
		if (usuarioBanco!=null) {
			throw new EmailException();
		}
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioInserirDTO.getNome());
		usuario.setEmail(usuarioInserirDTO.getEmail());
		usuario.setSenha(encoder.encode(usuarioInserirDTO.getSenha()));
		
		Set<UsuarioPerfil> perfis = new HashSet<>();
		for(Perfil perfil:usuarioInserirDTO.getPerfis()) {
			  perfil = perfilService.buscar(perfil.getId());
			  UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario,perfil,LocalDate.now());
			  perfis.add(usuarioPerfil);
		}
		usuario.setUsuarioPerfis(perfis);
		usuario = usuarioRepository.save(usuario);
		//mailConfig.sendMail(usuario.getEmail(), 
				//"Usuario cadastrado com sucesso", 
				//"Usuario " + usuario.getNome() + " cadastrado com o id: " + usuario.getId());
		return new UsuarioDTO(usuario);
	}
	
	

}
