package com.grupo16.pedidoservice.gateway.http.pagamento.json;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PagamentoJson {
	private Long carrinhoId;
	private Long pedidoId;
}
