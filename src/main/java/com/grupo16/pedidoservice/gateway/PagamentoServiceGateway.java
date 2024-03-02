package com.grupo16.pedidoservice.gateway;

import com.grupo16.pedidoservice.domain.Carrinho;

public interface PagamentoServiceGateway {

	String criarPagamento(Carrinho carrinho);

}
