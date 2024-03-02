package com.grupo16.pedidoservice.gateway;

import com.grupo16.pedidoservice.domain.Pedido;

public interface PedidoRepositoryGateway {

	Long criar(Pedido novoPedido);

}
