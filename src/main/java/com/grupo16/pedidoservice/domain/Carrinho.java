package com.grupo16.pedidoservice.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Carrinho {
	private Long id;
	private List<Item> itens;
	private BigDecimal valorTotal;
	
	public List<Produto> getProdutos(){
		return itens.stream().map(Item::getProduto).toList();
	}
	
	public Long getQuantidadeItensPorProdutoId(Long produtoId) {
		return itens.stream()
				.filter(i -> i.getProduto().getId().equals(produtoId))
				.mapToLong(Item::getQuantidade).reduce(0L, Long::sum);
	}
			
}
