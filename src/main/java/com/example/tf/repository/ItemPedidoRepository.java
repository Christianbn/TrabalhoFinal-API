package com.example.tf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tf.domain.ItemPedido;
import com.example.tf.domain.Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
	
	List<ItemPedido> findByPedido(Pedido pedido);
	
}
