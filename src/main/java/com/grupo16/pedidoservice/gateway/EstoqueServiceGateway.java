package com.grupo16.pedidoservice.gateway;

import java.util.List;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Produto;

public interface EstoqueServiceGateway {

	Estoque obtemQuantidadeDisponivel(List<Produto> produtos);

	void reservaEstoque(Carrinho carrinho);

}
