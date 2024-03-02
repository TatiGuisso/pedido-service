package com.grupo16.pedidoservice.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo16.pedidoservice.gateway.controller.json.PedidoJson;
import com.grupo16.pedidoservice.usecase.CriarPedidoUseCase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("pedidos")
@AllArgsConstructor
public class PedidoController {
	
	private CriarPedidoUseCase criarPedidoUseCase;
	
	@PostMapping()
	public Long salvar(
			@RequestHeader(name = "idUsuario") Long idUsuario,
			@RequestBody PedidoJson pedidoJson) {
		log.trace("Start pedidoJson={}", pedidoJson);
		
		Long pedidoId = criarPedidoUseCase.criar(pedidoJson.getCarrinhoId());
		
		log.trace("End pedidoId={}", pedidoId);
		return pedidoId;
		
	}

}
