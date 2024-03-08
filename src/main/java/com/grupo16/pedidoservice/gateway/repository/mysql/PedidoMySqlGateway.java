package com.grupo16.pedidoservice.gateway.repository.mysql;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Pedido;
import com.grupo16.pedidoservice.domain.Status;
import com.grupo16.pedidoservice.exception.ErroAoAcessarBancoDeDadosException;
import com.grupo16.pedidoservice.gateway.PedidoRepositoryGateway;
import com.grupo16.pedidoservice.gateway.repository.jpa.entity.PedidoEntity;
import com.grupo16.pedidoservice.gateway.repository.jpa.springdata.PedidoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PedidoMySqlGateway implements PedidoRepositoryGateway {

	private PedidoRepository pedidoRepository;
	
	@Override
	public Long criar(Pedido novoPedido) {
		
		try {
			
			PedidoEntity pedidoEntity = PedidoEntity.builder()
					.carrinhoId(novoPedido.getCarrinho().getId())
					.usuarioId(novoPedido.getUsuarioId())
					.status((long) novoPedido.getStatus().ordinal())
					.precoTotal(novoPedido.getValorTotal())
					.build();
			
			pedidoRepository.save(pedidoEntity);
			
			return pedidoEntity.getId();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}
	}

	@Override
	public void alterar(Pedido pedido) {
		try {
			PedidoEntity pedidoEntity = PedidoEntity.builder()
					.id(pedido.getId())
					.carrinhoId(pedido.getCarrinho().getId())
					.usuarioId(pedido.getUsuarioId())
					.status((long) pedido.getStatus().ordinal())
					.precoTotal(pedido.getValorTotal())
					.build();
			
			pedidoRepository.save(pedidoEntity);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}
	}

	@Override
	public Optional<Pedido> obterPorId(long pedidoId) {
		try {
			
			Optional<PedidoEntity> pedidoEntityOp = pedidoRepository.findById(pedidoId);
			
			if(pedidoEntityOp.isPresent()) {
				
				PedidoEntity pedidoEntity = pedidoEntityOp.get();
				
				Status[] statusValues = Status.values();
				
				Pedido pedido = Pedido.builder()
						.id(pedidoEntity.getId())
						.usuarioId(pedidoEntity.getUsuarioId())
						.status(statusValues[pedidoEntity.getStatus().intValue()])
						.carrinho(Carrinho.builder().id(pedidoEntity.getId()).build())
						.build();
				
				return Optional.of(pedido);
				
			}

			return Optional.empty();
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}
	}
}
