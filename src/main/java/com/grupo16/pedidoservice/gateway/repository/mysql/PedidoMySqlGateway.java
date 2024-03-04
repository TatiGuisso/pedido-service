package com.grupo16.pedidoservice.gateway.repository.mysql;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Pedido;
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

}
