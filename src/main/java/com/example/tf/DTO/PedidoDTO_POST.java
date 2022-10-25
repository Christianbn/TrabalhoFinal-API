package com.example.tf.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.tf.domain.Cliente;

import io.swagger.annotations.ApiModelProperty;

public class PedidoDTO_POST {
	
	@NotNull
	@ApiModelProperty(value = "Data do pedido")
	private LocalDate dataPedido;
	
	@ApiModelProperty(value = "Data de entrega do pedido")
	private LocalDate dataEntregaPedido;
	
	@ApiModelProperty(value = "Data de envio do pedido")
	private LocalDate dataEnvioPedido;
	
	@NotBlank
	@Size(max = 1)
	@ApiModelProperty(value = "Status do pedido")
	private String status;
	
	@NotNull
	@ApiModelProperty(value = "Identificador único do cliente")
	private Cliente cliente;
	
	@ApiModelProperty(value = "Adiciona os itens que contém no pedido")
	private List<ItemPedidoDTO_1> itemPedidoDTO_1;
	
	public PedidoDTO_POST() {
		super();
	}

	public PedidoDTO_POST(LocalDate dataPedido, LocalDate dataEntregaPedido, LocalDate dataEnvioPedido, String status,
			Cliente cliente, List<ItemPedidoDTO_1> itemPedidoDTO_1) {
		super();
		this.dataPedido = dataPedido;
		this.dataEntregaPedido = dataEntregaPedido;
		this.dataEnvioPedido = dataEnvioPedido;
		this.status = status;
		this.cliente = cliente;
		this.itemPedidoDTO_1 = itemPedidoDTO_1;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntregaPedido() {
		return dataEntregaPedido;
	}

	public void setDataEntregaPedido(LocalDate dataEntregaPedido) {
		this.dataEntregaPedido = dataEntregaPedido;
	}

	public LocalDate getDataEnvioPedido() {
		return dataEnvioPedido;
	}

	public void setDataEnvioPedido(LocalDate dataEnvioPedido) {
		this.dataEnvioPedido = dataEnvioPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO_1> getItemPedidoDTO_1() {
		return itemPedidoDTO_1;
	}

	public void setItemPedidoDTO_POST(List<ItemPedidoDTO_1> itemPedidoDTO_1) {
		this.itemPedidoDTO_1 = itemPedidoDTO_1;
	}
	
}
