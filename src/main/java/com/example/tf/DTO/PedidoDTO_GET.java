package com.example.tf.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.example.tf.domain.Cliente;
import com.example.tf.domain.Pedido;
import com.example.tf.enums.PedidoStatus;

import io.swagger.annotations.ApiModelProperty;

public class PedidoDTO_GET {
	
	
	@ApiModelProperty(value = "Identificador único do pedido")
	private Long idPedido;
	
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
	
	@ApiModelProperty(value = "Lista de itens que contém no pedido")
	private List<ItemPedidoDTO_GET> itemPedido;
	
	public PedidoDTO_GET() {
		super();
	}

	public PedidoDTO_GET(Long idPedido, LocalDate dataPedido, LocalDate dataEntregaPedido, LocalDate dataEnvioPedido,
			PedidoStatus status, Cliente cliente, List<ItemPedidoDTO_GET> itemPedido) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.dataEntregaPedido = dataEntregaPedido;
		this.dataEnvioPedido = dataEnvioPedido;
		this.status = status;
		this.cliente = cliente;
		this.itemPedido = itemPedido;
	}
	
	public PedidoDTO_GET(Pedido pedido, List<ItemPedidoDTO_GET> itemPedido) {
		this.idPedido = pedido.getIdPedido();
		this.dataPedido = pedido.getDataPedido();
		this.dataEntregaPedido = pedido.getDataEntregaPedido();
		this.dataEnvioPedido = pedido.getDataEnvioPedido();
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
		this.itemPedido = itemPedido;
		
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

	public List<ItemPedidoDTO_GET> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedidoDTO(List<ItemPedidoDTO_GET> itemPedido) {
		this.itemPedido = itemPedido;
	}
	
}
