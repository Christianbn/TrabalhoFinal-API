package com.example.tf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tf.domain.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	//Optional<Categoria> findByNome(String nome);
}
