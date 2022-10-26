package com.example.tf.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.tf.domain.Categoria;

import io.swagger.annotations.ApiModelProperty;

public class ProdutoDTO {

	@NotBlank
	@Size(max = 30)
	@ApiModelProperty(value = "Nome do produto")
	private String nomeProduto; 
	
	@NotBlank
	@Size(max = 200)
	@ApiModelProperty(value = "Descrição do produto")
	private String descricaoProduto ;
	
	@NotNull
	@ApiModelProperty(value = "Quantidade de produtos em estoque")
	private Integer quantidadeEstoqueProduto;
	
	@NotNull
	@ApiModelProperty(value = "Valor unitário do produto")
	private Double valorUnitarioProduto;
	
	@NotNull
	@ApiModelProperty(value = "Identificador único da categoria")
	private Categoria categoria;
	
	@ApiModelProperty(value = "URL da imagem do produto", readOnly = true)
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
