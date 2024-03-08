package com.grupo16.pedidoservice.gateway;

import java.util.Optional;

import com.grupo16.pedidoservice.domain.Pedido;

public interface PedidoRepositoryGateway {

	Long criar(Pedido novoPedido);
	void alterar(Pedido pedido);
	Optional<Pedido> obterPorId(long pedidoId);

}
