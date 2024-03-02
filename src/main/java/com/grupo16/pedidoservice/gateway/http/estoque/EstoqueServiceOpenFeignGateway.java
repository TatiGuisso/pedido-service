package com.grupo16.pedidoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Produto;
import com.grupo16.pedidoservice.gateway.EstoqueServiceGateway;

@Component
public class EstoqueServiceOpenFeignGateway implements EstoqueServiceGateway {

	@Override
	public Estoque obtemQuantidadeDisponivel(List<Produto> produtos) {
		// TODO Implementar
		return null;
	}

	@Override
	public void reservaEstoque(Carrinho carrinho) {
		// TODO Implementar

	}

}
