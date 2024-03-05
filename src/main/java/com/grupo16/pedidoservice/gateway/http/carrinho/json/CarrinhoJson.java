package com.grupo16.pedidoservice.gateway.http.carrinho.json;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarrinhoJson {
	private Long idCarrinho;
	private Long idUsuario;
	private List<ItemJson> itens;
	private Double valorTotal;
}
