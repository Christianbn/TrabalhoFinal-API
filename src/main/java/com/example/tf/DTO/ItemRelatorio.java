package com.example.tf.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.tf.domain.ItemPedido;

import io.swagger.annotations.ApiModelProperty;

public class ItemRelatorio {
	

	@ApiModelProperty(value = "Identificador único do ItemPedido")
	private Long idProduto;
	
	@NotBlank
	@Size(max = 30)
	@ApiModelProperty(value = "Nome do produto")
	private String nomeProduto;
	
	@NotNull
	@ApiModelProperty(value = "Quantidade de ItemPedido")
	private Integer quantidadeItemPedido;
	
	@NotNull
	@ApiModelProperty(value = "Preço de venda do ItemPedido")
	private Double precoVendaItemPedido;
	
	@NotNull
	@ApiModelProperty(value = "Valor bruto do ItemPedido")
	private Double valorBrutoItemPedido; 
	
	@NotNull
	@ApiModelProperty(value = "Percentual de desconto do ItemPedido")
	private Double percentualDescontoItemPedido; 
	
	@NotNull
	@ApiModelProperty(value = "Valor líquido do ItemPedido")
	private Double valorLiquidoItemPedido;
	
	public ItemRelatorio() {
		super();
	}

	public ItemRelatorio(ItemPedido itens) {
        this.idProduto = itens.getProduto().getIdProduto();
        this.nomeProduto = itens.getProduto().getNomeProduto();
        this.quantidadeItemPedido = itens.getQuantidadeItemPedido();
        this.precoVendaItemPedido = itens.getPrecoVendaItemPedido();
        this.valorBrutoItemPedido = itens.getValorBrutoItemPedido();
        this.percentualDescontoItemPedido = itens.getPercentualDescontoItemPedido();
        this.valorLiquidoItemPedido = itens.getValorLiquidoItemPedido();
    }

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQuantidadeItemPedido() {
		return quantidadeItemPedido;
	}

	public void setQuantidadeItemPedido(Integer quantidadeItemPedido) {
		this.quantidadeItemPedido = quantidadeItemPedido;
	}

	public Double getPrecoVendaItemPedido() {
		return precoVendaItemPedido;
	}

	public void setPrecoVendaItemPedido(Double precoVendaItemPedido) {
		this.precoVendaItemPedido = precoVendaItemPedido;
	}

	public Double getValorBrutoItemPedido() {
		return valorBrutoItemPedido;
	}

	public void setValorBrutoItemPedido(Double valorBrutoItemPedido) {
		this.valorBrutoItemPedido = valorBrutoItemPedido;
	}

	public Double getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(Double percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public Double getValorLiquidoItemPedido() {
		return valorLiquidoItemPedido;
	}

	public void setValorLiquidoItemPedido(Double valorLiquidoItemPedido) {
		this.valorLiquidoItemPedido = valorLiquidoItemPedido;
	} 
	
}
