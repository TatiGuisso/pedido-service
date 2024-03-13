package com.grupo16.pedidoservice.gateway.http.estoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.pedidoservice.domain.Carrinho;
import com.grupo16.pedidoservice.domain.Estoque;
import com.grupo16.pedidoservice.domain.Item;
import com.grupo16.pedidoservice.domain.Produto;
import com.grupo16.pedidoservice.exception.ErrorAoAcessarEstoqueServiceException;
import com.grupo16.pedidoservice.exception.SystemBaseException;
import com.grupo16.pedidoservice.exception.SystemExternalException;
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

	private ObjectMapper objectMapper;
	
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
			tratarExcecao(e);
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
			tratarExcecao(e);
		}
	}

	@Override
	public void cancelarReserva(Carrinho carrinho) {
		try {
			List<Item> itens = carrinho.getItens();
			List<EstoqueJson> estoquesJson = itens.stream().map(i -> new EstoqueJson(null, i.getProduto().getId(), i.getQuantidade())).toList();
			
			estoqueServiceFeignClient.cancelarReservas(estoquesJson);

		} catch (Exception e) {
			tratarExcecao(e);
		}
		
	}

	private void tratarExcecao(Exception e) {
		log.error(e.getMessage(), e);
		if(e instanceof FeignException feignException) {
			try {
				String exceptionResponseBody = feignException.contentUTF8();
				SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
				throw systemBaseException;

			} catch (Exception e2) {
				log.error(e2.getMessage(), e);
				throw new ErrorAoAcessarEstoqueServiceException();
			}
		}
		throw new ErrorAoAcessarEstoqueServiceException();
	}
	
}
