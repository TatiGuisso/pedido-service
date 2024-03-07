package com.grupo16.pedidoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo16.pedidoservice.gateway.http.estoque.json.EstoqueJson;

@FeignClient(value = "ESTOQUE-SERVICE", path = "estoques")
public interface EstoqueServiceFeignClient {
	
	@GetMapping("{idsProdutos}")
	List<EstoqueJson> obter(@PathVariable(value = "idsProdutos") List<Long> idsProdutos); 
	
	@PutMapping("reserva")
	void reservar(@RequestBody List<EstoqueJson> estoqueJsonList);

}
