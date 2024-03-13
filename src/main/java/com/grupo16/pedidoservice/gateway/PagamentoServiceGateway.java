package com.grupo16.pedidoservice.gateway;

import com.grupo16.pedidoservice.domain.Carrinho;

public interface PagamentoServiceGateway {

	Long criarPagamento(Long pedidoId, Carrinho carrinho);
	
}
