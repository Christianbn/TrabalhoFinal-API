package com.example.tf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.DTO.Relatorio;
import com.example.tf.domain.ItemPedido;
import com.example.tf.domain.Pedido;
import com.example.tf.repository.ItemPedidoRepository;
import com.example.tf.repository.PedidoRepository;

@Service
public class RelatorioService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    
    
    
    public List<Relatorio> findAll() {
        List<Pedido> pedido = pedidoRepository.findAll();
        List<Relatorio> relatorio = new ArrayList<>();

        for (Pedido pedidoTemp : pedido) {
            List<ItemPedido> itensPedido = itemPedidoRepository.findByPedido(pedidoTemp);
            relatorio.add(new Relatorio(pedidoTemp, itensPedido));
        }

        return relatorio;
    }
    
    public Optional<Relatorio> findById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            List<ItemPedido> itensPedido = itemPedidoRepository.findByPedido(pedido.get());
            Relatorio relatorio = new Relatorio(pedido.get(), itensPedido);
            return Optional.of(relatorio);
        }
        return null;
    }
}
