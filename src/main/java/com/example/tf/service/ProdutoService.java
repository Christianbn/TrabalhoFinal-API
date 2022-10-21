package com.example.tf.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tf.DTO.ProdutoDTO;
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
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
		return produto;
		}
		return null; 
	}

	@Transactional
	public Produto PostProduto(ProdutoDTO produtoDTO) {
		Optional<Produto> produtoTemp = produtoRepository.findByNomeProduto(produtoDTO.getNomeProduto());
		if (produtoTemp.isPresent()) {
			return null;
		}
		Produto produto = new Produto(produtoDTO);
		produtoRepository.save(produto);
		return produto;
	}

	public Optional<Produto> PutProduto(ProdutoDTO produtoDTO, Long id) {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (produtoTemp.isPresent()) {
			Produto produto = new Produto(produtoDTO, produtoTemp.get().getIdProduto());
			produto = produtoRepository.save(produto);
			return Optional.of(produto);
		}
		return null;
	}

	public Boolean Delete(Long id) {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (produtoTemp.isPresent()) {
			produtoRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
