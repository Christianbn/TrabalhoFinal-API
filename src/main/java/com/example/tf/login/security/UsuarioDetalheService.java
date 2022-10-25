package com.example.tf.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.tf.login.domain.Usuario;
import com.example.tf.login.repository.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService{

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario==null) {
			throw new RuntimeException();
		}
		return new UsuarioDetalhe(usuario);
	}
}
