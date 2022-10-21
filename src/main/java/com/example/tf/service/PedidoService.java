package com.example.tf.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.domain.Pedido;
import com.example.tf.repository.PedidoRepository;

@Service
public class PedidoService {
		
		@Autowired
		PedidoRepository pedidoRepository;

		public List<Pedido> findAll() {
			return pedidoRepository.findAll();
		}

		public Optional<Pedido> findById(Long id) {
			return pedidoRepository.findById(id);
		}

		@Transactional
		public Pedido PostPedido(Pedido pedido) {
//			Optional<Pedido> pedidoTemp = PedidoRepository.findByNome(pedido.getNomePedido());
//			if (pedidoTemp.isPresent()) {
//				return null;
//			}

			pedidoRepository.save(pedido);
			return pedido;
		}

		public Optional<Pedido> PutPedido(Pedido pedido, Long id) {
			Optional<Pedido> pedidoTemp = pedidoRepository.findById(id);
			if (pedidoTemp.isPresent()) {
				pedido.setIdPedido(id);
				pedido = pedidoRepository.save(pedido);
				return Optional.ofNullable(pedido);
			}
			return null;
		}

		public Boolean Delete(Long id) {
			Optional<Pedido> pedidoTemp = pedidoRepository.findById(id);
			if (!pedidoTemp.isPresent()) {
				return false;
			}
			pedidoRepository.deleteById(id);
			return true;
		}
}
