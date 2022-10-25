package com.example.tf.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tf.login.domain.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}
