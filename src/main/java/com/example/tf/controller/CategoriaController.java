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

import com.example.tf.DTO.CategoriaDTO;
import com.example.tf.domain.Categoria;
import com.example.tf.service.CategoriaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	
		@Autowired
		CategoriaService categoriaService;

		@GetMapping
		@ApiOperation(value = "Lista todas as categorias", notes = "Listagem de categorias")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todas as categorias"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
		public ResponseEntity<List<Categoria>> findAll() {
			List<Categoria> categoria = categoriaService.findAll();
			if(categoria.isEmpty()) {
			return  ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(categoria);
		}

		@GetMapping("/{id}")
		@ApiOperation(value = "Retorna uma categoria", notes = "Categoria")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna uma categoria"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
		public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long id) {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if (categoria.isPresent()) {
				return ResponseEntity.ok(categoria);
			}
			return ResponseEntity.notFound().build();
		}

		@PostMapping
		@ApiOperation(value = "Insere os dados de uma categoria", notes = "Inserir categoria")
		@ApiResponses(value = { @ApiResponse(code = 201, message = "Categoria adicionada"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
		public ResponseEntity<Categoria> PostCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
			Categoria categoriaTemp = categoriaService.PostCategoria(categoriaDTO);
			if (categoriaTemp == null) {
				return ResponseEntity.notFound().build();
			}
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaTemp.getIdCategoria())
					.toUri();
			return ResponseEntity.created(uri).body(categoriaTemp);
		}

		@PutMapping("/{id}")
		@ApiOperation(value = "Atualiza dados de uma categoria", notes = "Atualizar Categoria")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria atualizada"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })

		public ResponseEntity<Optional<Categoria>> PutCategoria(@Valid @RequestBody Categoria categoria, @PathVariable Long id) {
			Optional<Categoria> categoriaTemp = categoriaService.PutCategoria(categoria, id);
			if (!categoriaTemp.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(categoriaTemp);

		}

		@DeleteMapping("/{id}")
		@ApiOperation(value = "Remove uma categoria", notes = "Remover Categoria")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria removida"),
				@ApiResponse(code = 204, message = "Sem conteúdo"),
				@ApiResponse(code = 401, message = "Erro de autenticação"),
				@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
				@ApiResponse(code = 404, message = "Recurso não encontrado"),
				@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			if (categoriaService.Delete(id)) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		}
}
