package com.grupo16.pedidoservice.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pedido {
	private Long id;
	private Long usuarioId;
	private Status status;
	private Carrinho carrinho;
	private String pagamentoExternoId;
	
	public BigDecimal getValorTotal() {
		return carrinho.getValorTotal();
	};
}
