package com.example.tf.DTO;

import javax.validation.constraints.NotNull;

import com.example.tf.domain.Produto;

import io.swagger.annotations.ApiModelProperty;

public class ItemPedidoDTO_1 {

	
	@NotNull
	@ApiModelProperty(value = "Quantidade de ItemPedido")
	private Integer quantidadeItemPedido; 
	
	@NotNull
	@ApiModelProperty(value = "Percentual de desconto do ItemPedido")
	private Double percentualDescontoItemPedido; 
	
	@NotNull
	@ApiModelProperty(value = "Identificador único do produto")
	private Produto produto;
	
	public ItemPedidoDTO_1() {
		super();
	}

	public ItemPedidoDTO_1(Integer quantidadeItemPedido, Double percentualDescontoItemPedido, Produto produto) {
		super();
		this.quantidadeItemPedido = quantidadeItemPedido;
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
		this.produto = produto;
	}

	public Integer getQuantidadeItemPedido() {
		return quantidadeItemPedido;
	}

	public void setQuantidadeItemPedido(Integer quantidadeItemPedido) {
		this.quantidadeItemPedido = quantidadeItemPedido;
	}

	public Double getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(Double percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
