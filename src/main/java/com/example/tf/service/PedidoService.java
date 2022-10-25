package com.example.tf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.DTO.ItemPedidoDTO_1;
import com.example.tf.DTO.ItemPedidoDTO_GET;
import com.example.tf.DTO.PedidoDTO_GET;
import com.example.tf.DTO.PedidoDTO_POST;
import com.example.tf.domain.ItemPedido;
import com.example.tf.domain.Pedido;
import com.example.tf.domain.Produto;
import com.example.tf.exception.EstoqueException;
import com.example.tf.repository.ItemPedidoRepository;
import com.example.tf.repository.PedidoRepository;
import com.example.tf.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Autowired
	ProdutoRepository produtoRepository;

	public List<PedidoDTO_GET> findAll() {
		List<Pedido> pedido = pedidoRepository.findAll();
		List<PedidoDTO_GET> pedidoDTO_GET = new ArrayList<>();
		List<ItemPedido> itemPedido = itemPedidoRepository.findAll();

		for (Pedido pedidoTemp : pedido) {
			List<ItemPedidoDTO_GET> itemPedidoTemp = new ArrayList<>();
			for (ItemPedido i : itemPedido) {
				if (i.getPedido() == pedidoTemp) 
					itemPedidoTemp.add(new ItemPedidoDTO_GET(i));
			}
			pedidoDTO_GET.add(new PedidoDTO_GET(pedidoTemp, itemPedidoTemp));
		}
		return pedidoDTO_GET;
	}

	public Optional<PedidoDTO_GET> findById(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			List<ItemPedido> itemPedido = itemPedidoRepository.findAll();
			List<ItemPedidoDTO_GET> itemPedidoTemp = new ArrayList<>();
			for (ItemPedido i : itemPedido) {
				if (i.getPedido().getIdPedido() == pedido.get().getIdPedido()) 
					itemPedidoTemp.add(new ItemPedidoDTO_GET(i));
			}
			PedidoDTO_GET pedidoDTO = new PedidoDTO_GET(pedido.get(), itemPedidoTemp);
			return Optional.of(pedidoDTO);
		}
		return null;
	}

	@Transactional
	public PedidoDTO_GET PostPedido(PedidoDTO_POST pedidoDTO) throws EstoqueException {
		Pedido pedido = new Pedido(pedidoDTO);
		pedidoRepository.save(pedido);
		for (ItemPedidoDTO_1 i : pedidoDTO.getItemPedidoDTO_1()) {
			Optional<Produto> produto = produtoRepository.findById(i.getProduto().getIdProduto());
			if (i.getQuantidadeItemPedido() <= i.getProduto().getQuantidadeEstoqueProduto()) {
				ItemPedido iP = new ItemPedido(i, pedido, produto.get());
				itemPedidoRepository.save(iP);
				produto.get().setQuantidadeEstoqueProduto(iP.getQuantidadeItemPedido());
				produtoRepository.save(produto.get());
				} else {
				throw new EstoqueException();
				}
		}
		List<ItemPedido> itemPedido = itemPedidoRepository.findAll();
		List<ItemPedidoDTO_GET> itemPedidoTemp = new ArrayList<>();
		Double valorTotal = 0.0;
		for (ItemPedido i : itemPedido) {
			if (i.getPedido().getIdPedido() == pedido.getIdPedido()) {
				itemPedidoTemp.add(new ItemPedidoDTO_GET(i));
				valorTotal += i.getValorLiquidoItemPedido();
			}
		}
		pedido.setValorTotalPedido(valorTotal);
		PedidoDTO_GET pedidoDTO_GET = new PedidoDTO_GET(pedido, itemPedidoTemp);
		return pedidoDTO_GET;
	}

	public Optional<PedidoDTO_GET> PutPedido(PedidoDTO_POST pedidoDTO, Long id) throws EstoqueException {
		
		Pedido pedido = new Pedido(pedidoDTO, id);
		pedidoRepository.save(pedido);
		List<ItemPedido> itemPedidoList = itemPedidoRepository.findByPedido(pedido);
		List<ItemPedidoDTO_1> pedidoDTOList = pedidoDTO.getItemPedidoDTO_1();

		for (int i = 0; i < pedidoDTOList.size() && i < itemPedidoList.size(); i++) {
			if (itemPedidoList.get(i).getProduto() == pedidoDTOList.get(i).getProduto()) {
				ItemPedido iP = itemPedidoList.get(i);
				iP.setQuantidadeItemPedido(pedidoDTOList.get(i).getQuantidadeItemPedido());
				if (iP.getQuantidadeItemPedido() > itemPedidoList.get(i).getQuantidadeItemPedido()) {
					if (iP.getQuantidadeItemPedido() > iP.getProduto().getQuantidadeEstoqueProduto()
							+ itemPedidoList.get(i).getQuantidadeItemPedido()) {
						throw new EstoqueException();
					}
					iP.getProduto().setQuantidadeEstoqueProduto(iP.getProduto().getQuantidadeEstoqueProduto()
							- (iP.getQuantidadeItemPedido() - itemPedidoList.get(i).getQuantidadeItemPedido()));
				}
				iP.setPercentualDescontoItemPedido(pedidoDTOList.get(i).getPercentualDescontoItemPedido());
				itemPedidoRepository.save(iP);
			} else if (itemPedidoList.get(i).getProduto() != pedidoDTOList.get(i).getProduto()) {
				ItemPedido iP = itemPedidoList.get(i);
				if (iP.getQuantidadeItemPedido() > itemPedidoList.get(i).getQuantidadeItemPedido()) {
					if (iP.getQuantidadeItemPedido() > iP.getProduto().getQuantidadeEstoqueProduto()
						+ itemPedidoList.get(i).getQuantidadeItemPedido()) {
						throw new EstoqueException();
					}
					iP.getProduto().setQuantidadeEstoqueProduto(iP.getProduto().getQuantidadeEstoqueProduto()
							- (iP.getQuantidadeItemPedido() - itemPedidoList.get(i).getQuantidadeItemPedido()));
				}
				iP.setPercentualDescontoItemPedido(pedidoDTOList.get(i).getPercentualDescontoItemPedido());
				iP.setProduto(pedidoDTOList.get(i).getProduto());
				itemPedidoRepository.save(iP);

			}

		}

		List<ItemPedido> itemPedido = itemPedidoRepository.findAll();
		List<ItemPedidoDTO_GET> itemPedidoTemp = new ArrayList<>();

		Double valorTotal = 0.0;
		for (ItemPedido i : itemPedido) {
			if (i.getPedido().getIdPedido() == pedido.getIdPedido()) {

				itemPedidoTemp.add(new ItemPedidoDTO_GET(i));
				valorTotal += i.getValorLiquidoItemPedido();
			}
		}
		
		pedido.setValorTotalPedido(valorTotal);
		PedidoDTO_GET pedidoDTO_GET = new PedidoDTO_GET(pedido, itemPedidoTemp);
		return Optional.of(pedidoDTO_GET);
	}

	public Boolean Delete(Long id) {
		Optional<Pedido> pedidoTemp = pedidoRepository.findById(id);
		if (!pedidoTemp.isPresent()) {
			return false;
		}
		
		List<ItemPedido> itemPedidoList = itemPedidoRepository.findByPedido(pedidoTemp.get());
		for (ItemPedido i : itemPedidoList)
			itemPedidoRepository.delete(i);
		pedidoRepository.deleteById(id);
		return true;
	}
}
