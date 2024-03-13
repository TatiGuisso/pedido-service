package com.grupo16.pedidoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Item;
import com.grupo16.pedidoservice.domain.Pedido;
import com.grupo16.pedidoservice.domain.Status;
import com.grupo16.pedidoservice.exception.ErroAoAcessarBancoDeDadosException;
import com.grupo16.pedidoservice.exception.EstoqueInsuficienteException;
import com.grupo16.pedidoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pedidoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;
import com.grupo16.pedidoservice.gateway.PedidoRepositoryGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class CriarPedidoUseCase {

	private CarrinhoServiceGateway carrinhoServiceGateway;
	
	private EstoqueServiceGateway estoqueServiceGateway;
	
	private PagamentoServiceGateway pagamentoServiceGateway;
	
	private PedidoRepositoryGateway pedidoRepositoryGateway;
	
	public Long criar(long carrinhoId, long usuarioId) {
		Carrinho carrinho =  carrinhoServiceGateway.obterPorId(carrinhoId);
		
		verificaEstoque(carrinho);
		
		estoqueServiceGateway.reservaEstoque(carrinho);
		
		Long pedidoId = criaPedido(usuarioId, carrinho);
		
		criarPagamento(carrinho, pedidoId);
		
		//TODO: criar fallback no caso de falha na inativação do carrinho
		carrinhoServiceGateway.inativar(carrinhoId);
		
		return pedidoId;
	}

	private void criarPagamento(Carrinho carrinho, Long pedidoId) {
		try {
			pagamentoServiceGateway.criarPagamento(pedidoId, carrinho);
			
		} catch (Exception e) {
			estoqueServiceGateway.cancelarReserva(carrinho);
			Pedido pedido = pedidoRepositoryGateway.obterPorId(pedidoId).orElseThrow();
			pedido.cancelar();
			pedidoRepositoryGateway.alterar(pedido);
		}
	}

	private Long criaPedido(long usuarioId, Carrinho carrinho) {
		try {
			return pedidoRepositoryGateway.criar(new Pedido(null, usuarioId, Status.CRIADO, carrinho));
			
		} catch (ErroAoAcessarBancoDeDadosException e) {
			estoqueServiceGateway.cancelarReserva(carrinho);
			throw e;
		}
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
