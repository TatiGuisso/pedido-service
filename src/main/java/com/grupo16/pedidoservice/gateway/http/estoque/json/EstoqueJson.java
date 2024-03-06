package com.grupo16.pedidoservice.gateway.http.estoque.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EstoqueJson {
	private Long id;
	private Long idProduto;
	private Long quantidade;

}
