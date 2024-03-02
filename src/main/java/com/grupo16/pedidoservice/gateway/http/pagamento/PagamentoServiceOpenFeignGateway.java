package com.grupo16.pedidoservice.gateway.http.pagamento;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;

@Component
public class PagamentoServiceOpenFeignGateway implements PagamentoServiceGateway {

	@Override
	public String criarPagamento(Carrinho carrinho) {
		// TODO IMPLEMENTAR
		return "any pagamento externo id";
	}

}
