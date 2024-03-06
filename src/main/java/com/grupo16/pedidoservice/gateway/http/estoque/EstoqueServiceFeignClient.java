package com.grupo16.pedidoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo16.pedidoservice.gateway.http.estoque.json.EstoqueJson;

@FeignClient(value = "ESTOQUE-SERVICE")
public interface EstoqueServiceFeignClient {
	
	@GetMapping("{idsProdutos}")
	List<EstoqueJson> obter(@PathVariable(value = "idsProdutos") List<Long> idsProdutos); 

}
