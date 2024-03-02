package com.grupo16.pedidoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Item;
import com.grupo16.pedidoservice.domain.Pedido;
import com.grupo16.pedidoservice.domain.Status;
import com.grupo16.pedidoservice.exception.EstoqueInsuficienteException;
import com.grupo16.pedidoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pedidoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;
import com.grupo16.pedidoservice.gateway.PedidoRepositoryGateway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CriarPedidoUseCase {

	private CarrinhoServiceGateway carrinhoServiceGateway;
	
	private EstoqueServiceGateway estoqueServiceGateway;
	
	private PagamentoServiceGateway pagamentoServiceGateway;
	
	private PedidoRepositoryGateway pedidoRepositoryGateway;
	
	public Long criar(long carrinhoId) {
		Carrinho carrinho =  carrinhoServiceGateway.obterPorId(carrinhoId);
		
		verificaEstoque(carrinho);
		
		estoqueServiceGateway.reservaEstoque(carrinho);
		
		String pagamentoExternoId = pagamentoServiceGateway.criarPagamento(carrinho);
		
		Pedido novoPedido = new Pedido(null, Status.CRIADO, carrinho, pagamentoExternoId);
		
		Long pedidoId = pedidoRepositoryGateway.criar(novoPedido);
		
		carrinhoServiceGateway.inativar(carrinhoId);
		
		return pedidoId;
		
		
	}

	private void verificaEstoque(Carrinho carrinho) {
		Estoque estoqueDisponivel = estoqueServiceGateway.obtemQuantidadeDisponivel(carrinho.getProdutos());
		
		for (Item item : carrinho.getItens()) {
			if(estoqueDisponivel.temDisponibilidade(item.getProduto().getId(), item.getQuantidade())) {
				continue;
			}

			log.warn("Estoque insuficiente para produto: {}", item.getProduto().getId());
			throw new EstoqueInsuficienteException();
		}
	}
}
