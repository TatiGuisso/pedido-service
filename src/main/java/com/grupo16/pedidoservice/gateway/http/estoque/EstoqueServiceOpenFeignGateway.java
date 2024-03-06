package com.grupo16.pedidoservice.gateway.http.estoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Item;
import com.grupo16.pedidoservice.domain.Produto;
import com.grupo16.pedidoservice.exception.ErrorAoAcessarEstoqueServiceException;
import com.grupo16.pedidoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pedidoservice.gateway.http.estoque.json.EstoqueJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EstoqueServiceOpenFeignGateway implements EstoqueServiceGateway {

	private EstoqueServiceFeignClient estoqueServiceFeignClient;

	@Override
	public Estoque obtemQuantidadeDisponivel(List<Produto> produtos) {

		try {

			List<Long> idsProdutos = produtos.stream().mapToLong(Produto::getId).boxed().toList();

			List<EstoqueJson> estoqueJsonList = estoqueServiceFeignClient.obter(idsProdutos);

			Map<Long, Long> qtdDisp = new HashMap<>();

			for (EstoqueJson estoqueJson : estoqueJsonList) {
				qtdDisp.put(estoqueJson.getIdProduto(), estoqueJson.getQuantidade());
			}

			return Estoque.builder().quantidadeDisponivel(qtdDisp).build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);

			if(e instanceof FeignException feignException) {

				//TODO: implementar
				String exceptionResponseBody = feignException.contentUTF8();
				log.error(exceptionResponseBody);
			}
			throw new ErrorAoAcessarEstoqueServiceException();
		}

	}

	@Override
	public void reservaEstoque(Carrinho carrinho) {

		try {
			List<EstoqueJson> estoqueJsonList = new ArrayList<>(); 
			List<Item> itens = carrinho.getItens();

			itens.forEach(i -> estoqueJsonList.add(EstoqueJson.builder()
					.idProduto(i.getProduto().getId())
					.quantidade(i.getQuantidade())
					.build()));

			estoqueServiceFeignClient.reservar(estoqueJsonList);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			if(e instanceof FeignException feignException) {

				//TODO: implementar
				String exceptionResponseBody = feignException.contentUTF8();
				log.error(exceptionResponseBody);
			}
			throw new ErrorAoAcessarEstoqueServiceException();
		}
	}

}
