package com.grupo16.pedidoservice.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.pedidoservice.gateway.controller.json.PedidoJson;
import com.grupo16.pedidoservice.usecase.CancelarPedidoUseCase;
import com.grupo16.pedidoservice.usecase.ConcluirPedidoUseCase;
import com.grupo16.pedidoservice.usecase.CriarPedidoUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidoController {
	
	private CriarPedidoUseCase criarPedidoUseCase;
	
	private ConcluirPedidoUseCase concluirPedidoUseCase;
	
	private CancelarPedidoUseCase cancelarPedidoUseCase;
	
	@PostMapping()
	public Long salvar(
			@RequestHeader(name = "userId") Long userId,
			@RequestBody PedidoJson pedidoJson) {
		log.trace("Start pedidoJson={}", pedidoJson);
		
		Long pedidoId = criarPedidoUseCase.criar(pedidoJson.getCarrinhoId(), userId);
		
		log.trace("End pedidoId={}", pedidoId);
		return pedidoId;
		
	}
	
	@PutMapping("{id}/concluir")
	public void concluir(@PathVariable(value = "id") Long id) {
		log.trace("Start id={}", id);
		
		concluirPedidoUseCase.concluir(id);
		
		log.trace("End");
	}
	
	@PutMapping("{id}/cancelar")
	public void cancelar(@PathVariable(value = "id") Long id) {
		log.trace("Start id={}", id);
		
		cancelarPedidoUseCase.cancelar(id);
		
		log.trace("End");
	}

}
