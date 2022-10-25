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

import com.example.tf.DTO.PedidoDTO_GET;
import com.example.tf.DTO.PedidoDTO_POST;
import com.example.tf.DTO.Relatorio;
import com.example.tf.exception.EstoqueException;
import com.example.tf.service.PedidoService;
import com.example.tf.service.RelatorioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController{

				@Autowired
				PedidoService pedidoService;
				@Autowired
				RelatorioService relatorioService;
				

				@GetMapping
				@ApiOperation(value = "Lista todos os pedidos", notes = "Listagem de pedidos")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<List<PedidoDTO_GET>> findAll() {
					List<PedidoDTO_GET> pedido = pedidoService.findAll();
					if(pedido.isEmpty()) {
					return  ResponseEntity.notFound().build();
					}
					return ResponseEntity.ok(pedido);
				
				}

				@GetMapping("/{id}")
				@ApiOperation(value = "Retorna um pedido", notes = "Pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um pedido"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<Optional<PedidoDTO_GET>> findById(@PathVariable Long id) {
					Optional<PedidoDTO_GET> pedido = pedidoService.findById(id);
					if (!pedido.isPresent()) {
						return ResponseEntity.notFound().build();
					}
					return ResponseEntity.ok(pedido);
				}

				@PostMapping
				@ApiOperation(value = "Insere os dados de um pedido", notes = "Inserir pedido")
				@ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido adicionado"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<PedidoDTO_GET> PostPedido(@Valid @RequestBody PedidoDTO_POST pedidoDTO) throws EstoqueException {
					PedidoDTO_GET pedidoTemp = pedidoService.PostPedido(pedidoDTO);
					if (pedidoTemp == null) {
						return ResponseEntity.notFound().build();
					}
					URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedidoTemp.getIdPedido())
							.toUri();
					return ResponseEntity.created(uri).body(pedidoTemp);
				}

				@PutMapping("/{id}")
				@ApiOperation(value = "Atualiza dados de um pedido", notes = "Atualizar pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido atualizado"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				
				public ResponseEntity<Optional<PedidoDTO_GET>> PutPedido(@Valid @RequestBody PedidoDTO_POST pedidoDTO, @PathVariable Long id) throws EstoqueException {

					Optional<PedidoDTO_GET> pedidoTemp = pedidoService.PutPedido(pedidoDTO, id);
					if (!pedidoTemp.isPresent()) {
						return ResponseEntity.notFound().build();
					}

					return ResponseEntity.ok(pedidoTemp);

				}

				@DeleteMapping("/{id}")
				@ApiOperation(value = "Remove um pedido", notes = "Remover pedido")
				@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido removido"),
						@ApiResponse(code = 204, message = "Sem conteúdo"),
						@ApiResponse(code = 401, message = "Erro de autenticação"),
						@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
						@ApiResponse(code = 404, message = "Recurso não encontrado"),
						@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
				public ResponseEntity<Void> delete(@PathVariable Long id) {
					if (pedidoService.Delete(id)) {
						return ResponseEntity.noContent().build();
					}
					return ResponseEntity.notFound().build();
				}
				
				
				@GetMapping("/relatorio")
			    public ResponseEntity<List<Relatorio>> listaRelatorio() {
					List<Relatorio> relatorio = relatorioService.findAll();
			        if (relatorio.isEmpty()) {
			            return ResponseEntity.notFound().build();
			        }
			        return ResponseEntity.ok(relatorio);

			    }
				
				@GetMapping("/relatorio/{id}")
			    public ResponseEntity<Optional<Relatorio>> listaRelatorio(@PathVariable Long id) {
					Optional<Relatorio> relatorio = relatorioService.findById(id);
			        if (relatorio.isEmpty()) {
			            return ResponseEntity.notFound().build();
			        }
			        return ResponseEntity.ok(relatorio);

			    }
				
				
				
				
}
