package com.example.tf.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tf.login.domain.UsuarioPerfil;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, Long>{

}
