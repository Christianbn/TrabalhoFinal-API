package com.example.tf.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class Relatorio {
	
	@ApiModelProperty(value = "Identificador único do pedido")
	private Long idPedido;
	
	@NotNull
	@ApiModelProperty(value = "Data do pedido")
	private LocalDate dataPedido;
	
	@ApiModelProperty(value = "Valor total do pedido")
	private Double valorTotalPedido;
	
	@ApiModelProperty(value = "Relatório")
	private List<ItemRelatorio> itemRelatorio;
	
	public Relatorio() {
		super();
	}

	public Relatorio(Long idPedido, LocalDate dataPedido, Double valorTotalPedido,
			List<ItemRelatorio> itemRelatorio) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorTotalPedido = valorTotalPedido;
		this.itemRelatorio = itemRelatorio;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public List<ItemRelatorio> getItemRelatorio() {
		return itemRelatorio;
	}

	public void setItemRelatorio(List<ItemRelatorio> itemRelatorio) {
		this.itemRelatorio = itemRelatorio;
	}
	
	
	
}
