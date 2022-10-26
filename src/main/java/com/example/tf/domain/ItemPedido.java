package com.example.tf.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.example.tf.DTO.ItemPedidoDTO_1;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "itempedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido", nullable = false)
	@ApiModelProperty(value = "Identificador único do ItemPedido")
	private Long idItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name = "quantidade_item_pedido", nullable = false)
	@ApiModelProperty(value = "Quantidade de ItemPedido")
	private Integer quantidadeItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name = "preco_venda_item_pedido", nullable = false)
	@ApiModelProperty(value = "Preço de venda do ItemPedido")
	private Double precoVendaItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name = "percentual_desconto_item_pedido", nullable = false)
	@ApiModelProperty(value = "Percentual de desconto do ItemPedido")
	private Double percentualDescontoItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name = "valor_bruto_item_pedido", nullable = false)
	@ApiModelProperty(value = "Valor bruto do ItemPedido")
	private Double valorBrutoItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name = "valor_liquido_item_pedido", nullable = false)
	@ApiModelProperty(value = "Valor líquido do ItemPedido")
	private Double valorLiquidoItemPedido;

	@NotNull(message = "Este campo não pode ser nulo.")
	@ManyToOne
	@JoinColumn(name = "id_produto")
	@ApiModelProperty(value = "Identificador único do produto")
	private Produto produto;

	@NotNull(message = "Este campo não pode ser nulo.")
	@ManyToOne()
	@JoinColumn(name = "id_pedido")
	@ApiModelProperty(value = "Identificador único do pedido")
	private Pedido pedido;

	public ItemPedido(ItemPedidoDTO_1 i, Pedido pedido, Produto produto) {
		this.produto = produto;
		this.quantidadeItemPedido = i.getQuantidadeItemPedido();
		this.percentualDescontoItemPedido = i.getPercentualDescontoItemPedido();
		this.precoVendaItemPedido = produto.getValorUnitarioProduto();
		this.valorBrutoItemPedido = (produto.getValorUnitarioProduto() * quantidadeItemPedido);
		this.valorLiquidoItemPedido = (valorBrutoItemPedido
				- (valorBrutoItemPedido * (percentualDescontoItemPedido / 100)));
		this.pedido = pedido;
	}

	public ItemPedido(ItemPedidoDTO_1 i, Pedido pedido, Produto produto, Long id) {
		this.produto = produto;
		this.quantidadeItemPedido = i.getQuantidadeItemPedido();
		this.percentualDescontoItemPedido = i.getPercentualDescontoItemPedido();
		this.precoVendaItemPedido = produto.getValorUnitarioProduto();
		this.valorBrutoItemPedido = (produto.getValorUnitarioProduto() * quantidadeItemPedido);
		this.valorLiquidoItemPedido = (valorBrutoItemPedido
				- (valorBrutoItemPedido * (percentualDescontoItemPedido / 100)));
		this.pedido = pedido;
		this.idItemPedido = id;
	}

	public ItemPedido() {

	}

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
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

	public Double getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(Double percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public Double getValorBrutoItemPedido() {
		return valorBrutoItemPedido;
	}

	public void setValorBrutoItemPedido(Double valorBrutoItemPedido) {
		this.valorBrutoItemPedido = valorBrutoItemPedido;
	}

	public Double getValorLiquidoItemPedido() {
		return valorLiquidoItemPedido;
	}

	public void setValorLiquidoItemPedido(Double valorLiquidoItemPedido) {
		this.valorLiquidoItemPedido = valorLiquidoItemPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idItemPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(idItemPedido, other.idItemPedido);
	}

}
