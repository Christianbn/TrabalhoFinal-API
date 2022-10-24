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

import com.example.tf.DTO.ClienteDTO_POST;
import com.example.tf.domain.Cliente;
import com.example.tf.exception.CpfException;
import com.example.tf.exception.EmailException;
import com.example.tf.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value="Lista todos os clientes", notes="Listagem de clientes")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os clientes"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),})
	
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> cliente = clienteService.findAll();
		if(cliente.isEmpty()) {
			return  ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	} 
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna um cliente", notes="Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),})
	
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ApiOperation(value="Insere os dados de um cliente", notes="Inserir Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Cliente adicionado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),})
	
	public ResponseEntity<Cliente> PostItemPedido(@Valid @RequestBody ClienteDTO_POST clienteDTO) throws EmailException, CpfException {
		Cliente clienteTemp = clienteService.PostCliente(clienteDTO);
		if (clienteTemp == null) {
			return ResponseEntity.notFound().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteTemp.getIdCliente())
				.toUri();
		return ResponseEntity.created(uri).body(clienteTemp);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza dados de um cliente", notes="Atualizar Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),})
	
	public ResponseEntity<Optional<Cliente>> Putcliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		Optional<Cliente> clienteTemp = clienteService.PutCliente(cliente, id);
		if (!clienteTemp.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(clienteTemp);

	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove um cliente", notes="Remover Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Cliente removido"),
	@ApiResponse(code=204, message="Sem conteúdo"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),})
	
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (clienteService.DeleteCliente(id)) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
