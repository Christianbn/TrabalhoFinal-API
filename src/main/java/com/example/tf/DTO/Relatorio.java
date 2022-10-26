package com.example.tf.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.tf.domain.ItemPedido;
import com.example.tf.domain.Pedido;

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

	public Relatorio(Pedido pedido, List<ItemPedido> itemPedido) {
        this.idPedido = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.valorTotalPedido = pedido.getValorTotalPedido();
        this.itemRelatorio = new ArrayList<>();
        for (ItemPedido itens : itemPedido) {
            this.itemRelatorio.add(new ItemRelatorio(itens));
        }
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

	@Override
	public String toString() {
		return "Relatorio \n idPedido=" + idPedido + "\n dataPedido=" + dataPedido + "\n valorTotalPedido="
				+ valorTotalPedido + "\n itemRelatorio=" + itemRelatorio;
	}
	
	
	
}
