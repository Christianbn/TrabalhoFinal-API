package com.example.tf.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tf.DTO.ProdutoDTO;
import com.example.tf.domain.Produto;
import com.example.tf.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produto = produtoService.findAll();
		if(produto.isEmpty()) {
		return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}

	@PostMapping
	public ResponseEntity<Produto> PostProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
		Produto produtoTemp = produtoService.PostProduto(produtoDTO);
		if (produtoTemp == null) {
			return ResponseEntity.notFound().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoTemp.getIdProduto())
				.toUri();
		return ResponseEntity.created(uri).body(produtoTemp);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Optional<Produto>> PutProduto(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable Long id) {
		Optional<Produto> produtoTemp = produtoService.PutProduto(produtoDTO, id);
		if (!produtoTemp.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtoTemp);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (produtoService.Delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
