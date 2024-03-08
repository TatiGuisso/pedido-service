package com.grupo16.pedidoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pedidoservice.domain.Pedido;
import com.grupo16.pedidoservice.gateway.PedidoRepositoryGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConcluirPedidoUseCase {

	private PedidoRepositoryGateway pedidoRepositoryGateway;
	
	public void concluir(long pedidoId) {
		
		Pedido pedido = pedidoRepositoryGateway.obterPorId(pedidoId).orElseThrow();
		pedido.concluir();
		
		pedidoRepositoryGateway.alterar(pedido);
	}
}
