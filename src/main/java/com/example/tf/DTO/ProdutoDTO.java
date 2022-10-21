package com.example.tf.DTO;

import com.example.tf.domain.Categoria;

public class ProdutoDTO {

	
	private String nomeProduto; 
	private String descricaoProduto ;
	private Integer quantidadeEstoqueProduto;
	private Double valorUnitarioProduto;
	private Categoria categoria;
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String nomeProduto, String descricaoProduto, Integer quantidadeEstoqueProduto,
			Double valorUnitarioProduto, Categoria categoria) {
		super();
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.quantidadeEstoqueProduto = quantidadeEstoqueProduto;
		this.valorUnitarioProduto = valorUnitarioProduto;
		this.categoria = categoria;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getQuantidadeEstoqueProduto() {
		return quantidadeEstoqueProduto;
	}

	public void setQuantidadeEstoqueProduto(Integer quantidadeEstoqueProduto) {
		this.quantidadeEstoqueProduto = quantidadeEstoqueProduto;
	}

	public Double getValorUnitarioProduto() {
		return valorUnitarioProduto;
	}

	public void setValorUnitarioProduto(Double valorUnitarioProduto) {
		this.valorUnitarioProduto = valorUnitarioProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
