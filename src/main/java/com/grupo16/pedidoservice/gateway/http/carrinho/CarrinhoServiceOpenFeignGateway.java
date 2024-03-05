package com.grupo16.pedidoservice.gateway.http.carrinho;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.exception.ErrorAoAcessarCarrinhoServiceException;
import com.grupo16.pedidoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pedidoservice.gateway.http.carrinho.json.CarrinhoJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CarrinhoServiceOpenFeignGateway implements CarrinhoServiceGateway {

	private CarrinhoServiceFeignClient carrinhoServiceFeignClient;
	
	@Override
	public Carrinho obterPorId(long carrinhoId) {
		
		try {
			
			CarrinhoJson obterPorId = carrinhoServiceFeignClient.obterPorId(carrinhoId);
			
			// TODO Implementar
			return Carrinho.builder().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			
			if(e instanceof FeignException feignException) {
				
				//TODO: implementar
				String exceptionResponseBody = feignException.contentUTF8();
				log.error(exceptionResponseBody);
				
			}
			
			throw new ErrorAoAcessarCarrinhoServiceException();
		}
		
	}

	@Override
	public void inativar(long carrinhoId) {
		// TODO Implementar
		
	}

}
