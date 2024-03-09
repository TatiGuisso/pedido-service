package com.grupo16.pedidoservice.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Pedido {
	private Long id;
	private Long usuarioId;
	private Status status;
	private Carrinho carrinho;
	
	public BigDecimal getValorTotal() {
		return carrinho.getValorTotal();
	};
	
	public void concluir() {
		status = Status.CONCLUIDO;
	}
	
	public void cancelar() {
		status = Status.CANCELADO;
	}
}
