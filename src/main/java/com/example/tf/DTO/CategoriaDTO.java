package com.example.tf.DTO;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.tf.domain.Categoria;

import io.swagger.annotations.ApiModelProperty;

public class CategoriaDTO {
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 30)
	@ApiModelProperty(value = "Nome da categoria", required = true)
	private String nomeCategoria;
	
	@NotBlank(message = "Este campo não pode estar em branco.")
	@Size(max = 200)
	@ApiModelProperty(value = "Descrição da categoria", required = true)
	private String descricaoCategoria;
	
	public CategoriaDTO() {
		super();
	}

	
	public CategoriaDTO(String nomeCategoria,String descricaoCategoria) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.descricaoCategoria = descricaoCategoria;
	}


	public CategoriaDTO(Optional<Categoria> categoria) {
		this.nomeCategoria = categoria.get().getNomeCategoria();
		this.descricaoCategoria = categoria.get().getDescricaoCategoria();
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
	
}
