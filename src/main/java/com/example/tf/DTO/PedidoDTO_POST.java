package com.example.tf.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.example.tf.domain.Cliente;
import com.example.tf.enums.PedidoStatus;

import io.swagger.annotations.ApiModelProperty;

public class PedidoDTO_POST {

	@NotNull(message = "Este campo não pode ser nulo.")
	@ApiModelProperty(value = "Data do pedido")
	private LocalDate dataPedido;

	@ApiModelProperty(value = "Data de entrega do pedido")
	private LocalDate dataEntregaPedido;

	@ApiModelProperty(value = "Data de envio do pedido")
	private LocalDate dataEnvioPedido;

	@Enumerated(EnumType.ORDINAL)
	@ApiModelProperty(value = "Status do pedido")
	private PedidoStatus status;

	@NotNull(message = "Este campo não pode ser nulo.")
	@ApiModelProperty(value = "Identificador único do cliente")
	private Cliente cliente;

	@ApiModelProperty(value = "Adiciona os itens que contém no pedido")
	private List<ItemPedidoDTO_1> itemPedidoDTO;

	public PedidoDTO_POST() {
		super();
	}

	public PedidoDTO_POST(LocalDate dataPedido, LocalDate dataEntregaPedido, LocalDate dataEnvioPedido,
			PedidoStatus status, Cliente cliente, List<ItemPedidoDTO_1> itemPedidoDTO_1) {
		super();
		this.dataPedido = dataPedido;
		this.dataEntregaPedido = dataEntregaPedido;
		this.dataEnvioPedido = dataEnvioPedido;
		this.status = status;
		this.cliente = cliente;
		this.itemPedidoDTO = itemPedidoDTO_1;
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

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedidoDTO_1> getItemPedidoDTO_1() {
		return itemPedidoDTO;
	}

	public void setItemPedidoDTO_POST(List<ItemPedidoDTO_1> itemPedidoDTO_1) {
		this.itemPedidoDTO = itemPedidoDTO_1;
	}

}
