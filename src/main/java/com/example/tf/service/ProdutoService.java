package com.example.tf.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tf.DTO.ProdutoDTO;
import com.example.tf.domain.Produto;
import com.example.tf.exception.DescricaoException;
import com.example.tf.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	FotoService fotoService;

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
	public Produto PostProduto(ProdutoDTO produtoDTO, MultipartFile file) throws IOException, DescricaoException {
		Optional<Produto> produtoTemp = produtoRepository.findByNomeProduto(produtoDTO.getNomeProduto());
		if (produtoTemp.isPresent()) {
			throw new DescricaoException();
		}
		Produto produto = new Produto(produtoDTO);
		produtoRepository.save(produto);
		fotoService.inserir(produto, file);
		return produto;
	}

	public Optional<Produto> PutProduto(ProdutoDTO produtoDTO, Long id) throws DescricaoException {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (produtoTemp.isPresent()) {
			Produto produto = new Produto(produtoDTO, produtoTemp.get().getIdProduto());
			produto = produtoRepository.save(produto);
			return Optional.of(produto);
		}
		throw new DescricaoException();
	}

	public Boolean Delete(Long id) {
		Optional<Produto> produtoTemp = produtoRepository.findById(id);
		if (produtoTemp.isPresent()) {
			produtoRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public ProdutoDTO adicionarImagemUri(Produto produto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/foto")
                .buildAndExpand(produto.getIdProduto()).toUri();
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNomeProduto(produto.getNomeProduto());
        dto.setDescricaoProduto(produto.getDescricaoProduto());
        dto.setQuantidadeEstoqueProduto(produto.getQuantidadeEstoqueProduto());
        dto.setValorUnitarioProduto(produto.getValorUnitarioProduto());
        dto.setUrl(uri.toString());
        return dto;
    }
    
    public ProdutoDTO buscar(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return adicionarImagemUri(produto.get());
    }


}
