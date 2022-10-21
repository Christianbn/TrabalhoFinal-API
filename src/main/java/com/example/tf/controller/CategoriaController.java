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

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	
		@Autowired
		CategoriaService categoriaService;

		@GetMapping
		public ResponseEntity<List<Categoria>> findAll() {
			return ResponseEntity.ok(categoriaService.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Optional<CategoriaDTO>> findById(@PathVariable Long id) {
			Optional<CategoriaDTO> categoriaDTO = categoriaService.findById(id);
			if (!categoriaDTO.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(categoriaDTO);
		}

		@PostMapping
		public ResponseEntity<Categoria> PostCategoria(@Valid @RequestBody Categoria categoria) {
			Categoria categoriaTemp = categoriaService.PostCategoria(categoria);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getIdCategoria())
					.toUri();
			if (categoriaTemp == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.created(uri).body(categoriaTemp);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Optional<Categoria>> PutCategoria(@Valid @RequestBody Categoria categoria, @PathVariable Long id) {

			Optional<Categoria> categoriaTemp = categoriaService.PutCategoria(categoria, id);
			if (!categoriaTemp.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(categoriaTemp);

		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id) {
			if (categoriaService.Delete(id)) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build();
		}
}
