package com.grupo16.pedidoservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pedido {
	private Long id;
	private Status status;
	private Carrinho carrinho;
	private String pagamentoExternoId;
}
