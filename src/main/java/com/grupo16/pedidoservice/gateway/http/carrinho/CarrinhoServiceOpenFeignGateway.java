package com.grupo16.pedidoservice.gateway.http.carrinho;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Item;
import com.grupo16.pedidoservice.domain.Produto;
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
			
			CarrinhoJson carrinhoJson = carrinhoServiceFeignClient.obterPorId(carrinhoId);
			
			List<Item> itensDomain = new ArrayList<>();
			carrinhoJson.getItens().forEach(i -> {
				itensDomain.add(Item.builder()
						.produto(Produto.builder().id(i.getIdProduto()).build())
						.quantidade(i.getQuantidade().longValue())
						.build());
			});
			
			return Carrinho.builder()
					.id(carrinhoJson.getIdCarrinho())
					.itens(itensDomain)
					.build();
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
		carrinhoServiceFeignClient.inativar(carrinhoId);		
	}

}
