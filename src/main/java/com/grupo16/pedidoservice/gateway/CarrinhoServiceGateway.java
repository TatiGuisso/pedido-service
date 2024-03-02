package com.grupo16.pedidoservice.gateway;

import com.grupo16.pedidoservice.domain.Carrinho;

public interface CarrinhoServiceGateway {

	Carrinho obterPorId(long carrinhoId);

}
