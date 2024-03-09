package com.grupo16.pedidoservice.gateway.http.pagamento;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.exception.ErrorAoAcessarPagamentoServiceException;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;
import com.grupo16.pedidoservice.gateway.http.pagamento.json.PagamentoJson;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PagamentoServiceOpenFeignGateway implements PagamentoServiceGateway {

	private PagamentoServiceFeignClient pagamentoServiceFeignClient;
	
	@Override
	public Long criarPagamento(Long pedidoId, Carrinho carrinho) {

		try {
			
			PagamentoJson pagamentoJson = PagamentoJson.builder()
					.carrinhoId(carrinho.getId())
					.pedidoId(pedidoId)
					.build();
			
			return pagamentoServiceFeignClient.criar(pagamentoJson);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorAoAcessarPagamentoServiceException();
			
		}
	}
}
