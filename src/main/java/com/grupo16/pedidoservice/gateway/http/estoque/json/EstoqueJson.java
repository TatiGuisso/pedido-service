package com.grupo16.pedidoservice.gateway.http.estoque.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EstoqueJson {
	private Long id;
	private Long idProduto;
	private Long quantidade;

}
