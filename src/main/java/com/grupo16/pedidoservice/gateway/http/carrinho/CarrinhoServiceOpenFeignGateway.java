package com.grupo16.pedidoservice.gateway.http.carrinho;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.gateway.CarrinhoServiceGateway;

@Component
public class CarrinhoServiceOpenFeignGateway implements CarrinhoServiceGateway {

	@Override
	public Carrinho obterPorId(long carrinhoId) {
		// TODO Implementar
		return Carrinho.builder().build();
	}

	@Override
	public void inativar(long carrinhoId) {
		// TODO Implementar
		
	}

}
