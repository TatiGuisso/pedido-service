package com.grupo16.pedidoservice.gateway.http.pagamento;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.exception.ErrorAoAcessarPagamentoServiceException;
import com.grupo16.pedidoservice.exception.SystemBaseException;
import com.grupo16.pedidoservice.exception.SystemExternalException;
import com.grupo16.pedidoservice.gateway.PagamentoServiceGateway;
import com.grupo16.pedidoservice.gateway.http.pagamento.json.PagamentoJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PagamentoServiceOpenFeignGateway implements PagamentoServiceGateway {

	private PagamentoServiceFeignClient pagamentoServiceFeignClient;
	
	private ObjectMapper objectMapper;
	
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
			if(e instanceof FeignException feignException) {
				try {
					String exceptionResponseBody = feignException.contentUTF8();
					SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
					throw systemBaseException;

				} catch (Exception e2) {
					log.error(e2.getMessage(), e);
					throw new ErrorAoAcessarPagamentoServiceException();
				}
			}
			throw new ErrorAoAcessarPagamentoServiceException();
			
		}
	}

}
