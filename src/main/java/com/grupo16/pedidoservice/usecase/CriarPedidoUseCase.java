package com.grupo16.pedidoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pedidoservice.domain.Pedido;
import com.grupo16.pedidoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pedidoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;
import com.grupo16.pedidoservice.gateway.PedidoRepositoryGateway;

@Service
public class CriarPedidoUseCase {

	private CarrinhoServiceGateway carrinhoServiceGateway;
	
	private EstoqueServiceGateway estoqueServiceGateway;
	
	private PagamentoServiceGateway pagamentoServiceGateway;
	
	private PedidoRepositoryGateway pedidoRepositoryGateway;
	
	public Long criar(Pedido pedido) {
		//TODO: implementar
		
		return null;
	}
	
}
