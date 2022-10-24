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

import com.example.tf.domain.ItemPedido;
import com.example.tf.service.ItemPedidoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/ItemPedido")
public class ItemPedidoController {

				@Autowired
				ItemPedidoService itemPedidoService;

				@GetMapping
				@ApiOperation(value = "Lista todos os itens de todos os pedidos", notes = "Lista todos os itens de todos os pedidos")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os itens do pedido"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<List<ItemPedido>> findAll() {
					return ResponseEntity.ok(itemPedidoService.findAll()); 
				}

				@GetMapping("/{id}")
				@ApiOperation(value = "Retorna um item de um pedido", notes = "Retorna um item de um pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um item de um pedido"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<Optional<ItemPedido>> findById(@PathVariable Long id) {
					Optional<ItemPedido> itemPedido = itemPedidoService.findById(id);
					if (!itemPedido.isPresent()) {
						return ResponseEntity.notFound().build();
					}
					return ResponseEntity.ok(itemPedido);
				}

				@PostMapping
				@ApiOperation(value = "Insere os dados do item pedido", notes = "Inserir item pedido")
				@ApiResponses(value = { @ApiResponse(code = 201, message = "Item pedido adicionado"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<ItemPedido> PostItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
					ItemPedido itemPedidoTemp = itemPedidoService.PostItemPedido(itemPedido);
					URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemPedido.getIdItemPedido())
							.toUri();
					if (itemPedidoTemp == null) {
						return ResponseEntity.notFound().build();
					}
					return ResponseEntity.created(uri).body(itemPedidoTemp);
				}

				@PutMapping("/{id}")
				@ApiOperation(value = "Atualiza dados de um item pedido", notes = "Atualizar item pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Item pedido atualizado"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<Optional<ItemPedido>> PutItemPedido(@Valid @RequestBody ItemPedido itemPedido, @PathVariable Long id) {

					Optional<ItemPedido> itemPedidoTemp = itemPedidoService.PutItemPedido(itemPedido, id);
					if (!itemPedidoTemp.isPresent()) {
						return ResponseEntity.notFound().build();
					}
					return ResponseEntity.ok(itemPedidoTemp);

				}

				@DeleteMapping("/{id}")
				@ApiOperation(value = "Remove um item pedido", notes = "Remover item pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Item pedido removido"),
						@ApiResponse(code = 204, message = "Sem conteúdo"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<Void> delete(@PathVariable Long id) {
					if (itemPedidoService.Delete(id)) {
						return ResponseEntity.noContent().build();
					}
					return ResponseEntity.notFound().build();
				}
		}
