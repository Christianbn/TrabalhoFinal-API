package com.example.tf.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.tf.DTO.ProdutoDTO;
import com.example.tf.domain.Foto;
import com.example.tf.domain.Produto;
import com.example.tf.service.FotoService;
import com.example.tf.service.ProdutoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	@Autowired
	FotoService fotoService;

	@GetMapping
	@ApiOperation(value = "Lista todos os produtos", notes = "Listagem de produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todas os produtos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produto = produtoService.findAll();
		if(produto.isEmpty()) {
		return  ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um produto", notes = "produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um produto"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.findById(id);
		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}

	@PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation(value = "Insere os dados de um produto", notes = "Inserir produto")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Produto adicionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	
	public ResponseEntity<Produto> PostProduto(@Valid @RequestPart ProdutoDTO produtoDTO, @RequestPart MultipartFile file) throws IOException {
		Produto produtoTemp = produtoService.PostProduto(produtoDTO, file);
		if (produtoTemp == null) {
			return ResponseEntity.notFound().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/produto/{id}/foto")
	                .buildAndExpand(produtoTemp.getIdProduto()).toUri();
		return ResponseEntity.created(uri).body(produtoTemp);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um produto", notes = "Atualizar produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	
	public ResponseEntity<Optional<Produto>> PutProduto(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable Long id) {
		Optional<Produto> produtoTemp = produtoService.PutProduto(produtoDTO, id);
		if (!produtoTemp.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(produtoTemp);

	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um produto", notes = "Remover produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto removido"),
			@ApiResponse(code = 204, message = "Sem conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (produtoService.Delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/foto")
	@ApiOperation(value = "Procura uma imagem do produto passado via ID", notes = "Procura imagem de produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Imagem encontrada"),
			@ApiResponse(code = 204, message = "Sem conteúdo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
    public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id) {
        Foto foto = fotoService.buscarPorIdProduto(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", foto.getTipo());
        headers.add("Content-length", String.valueOf(foto.getDados().length));
        return new ResponseEntity<>(foto.getDados(), headers, HttpStatus.OK);
}
}
