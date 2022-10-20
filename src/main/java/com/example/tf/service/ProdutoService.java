package com.example.tf.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.domain.Produto;
import com.example.tf.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	@Transactional
	public Produto PostProduto(Produto produto) {
		Optional<Produto> produtoTemp = produtoRepository.findByDescricaoProduto(produto.getDescricaoProduto());
		if (produtoTemp.isPresent()) {
			return null;
		}

		produtoRepository.save(produto);
		return produto;
	}

	public Optional<Produto> PutProduto(Produto produto, Long id) {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (produtoTemp.isPresent()) {
			produto.setIdProduto(id);
			produto = produtoRepository.save(produto);
			return Optional.of(produto);
		}
		return null;
	}

	public Boolean Delete(Long id) {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (!produtoTemp.isPresent()) {
			return false;
		}
		produtoRepository.deleteById(id);
		return true;
	}

}
