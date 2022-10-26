package com.example.tf.domain;

import java.time.LocalDate;
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

import com.example.tf.DTO.PedidoDTO_POST;
import com.example.tf.enums.PedidoStatus;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_pedido", nullable = false)
	@ApiModelProperty(value = "Identificador único do pedido")
	private Long idPedido;
	
	@NotNull(message = "Este campo não pode ser nulo.")
	@Column(name= "data_pedido", nullable = false)
	@ApiModelProperty(value = "Data do pedido")
	private LocalDate dataPedido;

	@Column(name= "data_entrega_pedido")
	@ApiModelProperty(value = "Data de entrega do pedido")
	private LocalDate dataEntregaPedido;
	
	@Column(name= "data_envio_pedido")
	@ApiModelProperty(value = "Data de envio do pedido")
	private LocalDate dataEnvioPedido;
	
	@Column(name="status", nullable = false, length = 2)
	@ApiModelProperty(value = "Status do pedido")
	private PedidoStatus status; 

	
	@Column(name="valor_total_pedido", nullable = false, length = 20)
	@ApiModelProperty(value = "Valor total do pedido")
	private Double valorTotalPedido;
	
	@NotNull(message = "Este campo não pode ser nulo.")
	@ManyToOne()
	@JoinColumn(name = "id_cliente")
	@ApiModelProperty(value = "Identificador único do cliente")
	private Cliente cliente;
	
	public Pedido() {
		
	}

	public Pedido(PedidoDTO_POST pedidoDTO) {
		if(pedidoDTO.getDataPedido().isBefore(LocalDate.now())) {
			this.dataPedido = LocalDate.now();
			this.dataEnvioPedido = LocalDate.now().plusDays(1);
			this.dataEntregaPedido = LocalDate.now().plusDays(7);
		}else {
			this.dataPedido = pedidoDTO.getDataPedido();
			this.dataEnvioPedido = pedidoDTO.getDataEnvioPedido();
			this.dataEntregaPedido = pedidoDTO.getDataEntregaPedido();
		}
		this.dataEnvioPedido = pedidoDTO.getDataEnvioPedido();
		this.status = pedidoDTO.getStatus();
		this.cliente = pedidoDTO.getCliente();
		this.valorTotalPedido = 0.0;
	}
	
	public Pedido(PedidoDTO_POST pedidoDTO, long id) {
		if(pedidoDTO.getDataPedido().isBefore(LocalDate.now())) {
			this.dataPedido = LocalDate.now();
			this.dataEnvioPedido = LocalDate.now().plusDays(1);
			this.dataEntregaPedido = LocalDate.now().plusDays(7);
		}else {
			this.dataPedido = pedidoDTO.getDataPedido();
			this.dataEnvioPedido = pedidoDTO.getDataEnvioPedido();
			this.dataEntregaPedido = pedidoDTO.getDataEntregaPedido();
		}
		this.status = pedidoDTO.getStatus();
		this.cliente = pedidoDTO.getCliente();
		this.idPedido = id;
		this.valorTotalPedido = 0.0;
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

	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(idPedido, other.idPedido);
	}
	
}
