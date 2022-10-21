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

import com.example.tf.domain.Endereco;
import com.example.tf.service.EnderecoService;

@RestController
@RequestMapping("/api/Endereco")
public class EnderecoController {

			@Autowired
			EnderecoService enderecoService;

			@GetMapping
			public ResponseEntity<List<Endereco>> findAll() {
				return ResponseEntity.ok(enderecoService.findAll());
			}

			@GetMapping("/{id}")
			public ResponseEntity<Optional<Endereco>> findById(@PathVariable Long id) {
				Optional<Endereco> endereco = enderecoService.findById(id);
				if (!endereco.isPresent()) {
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.ok(endereco);
			}

			@PostMapping
			public ResponseEntity<Endereco> PostEndereco(@Valid @RequestBody Endereco endereco) {
				Endereco enderecoTemp = enderecoService.PostEndereco(endereco);
				URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getIdEndereco())
						.toUri();
				if (enderecoTemp == null) {
					return ResponseEntity.notFound().build();
				}
				return ResponseEntity.created(uri).body(enderecoTemp);
			}

			@PutMapping("/{id}")
			public ResponseEntity<Optional<Endereco>> PutEndereco(@Valid @RequestBody Endereco endereco, @PathVariable Long id) {

				Optional<Endereco> enderecoTemp = enderecoService.PutEndereco(endereco, id);
				if (!enderecoTemp.isPresent()) {
					return ResponseEntity.notFound().build();
				}

				return ResponseEntity.ok(enderecoTemp);

			}

			@DeleteMapping("/{id}")
			public ResponseEntity<Void> delete(@PathVariable Long id) {
				if (enderecoService.Delete(id)) {
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.notFound().build();
			}
	}
