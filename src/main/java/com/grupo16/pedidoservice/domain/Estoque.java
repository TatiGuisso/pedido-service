package com.grupo16.pedidoservice.domain;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Estoque {
	
	private Map<Long, Long> quantidadeDisponivel;//ID produto / Quantidade
	
	public boolean temDisponibilidade(Long produtoId, Long quantidadeRequerida) {
		Long quanidadeDisponivel = quantidadeDisponivel.get(produtoId);
		return quanidadeDisponivel.compareTo(quantidadeRequerida) >= 0;
	}

}
