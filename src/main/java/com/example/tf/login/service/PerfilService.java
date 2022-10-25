package com.example.tf.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.login.domain.Perfil;
import com.example.tf.login.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository perfilRepository;
	
	public Perfil buscar(Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);
		return perfil.get();
	}

}
