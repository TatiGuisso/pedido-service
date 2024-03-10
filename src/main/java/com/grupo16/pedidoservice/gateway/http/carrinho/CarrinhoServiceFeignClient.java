package com.grupo16.pedidoservice.gateway.http.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo16.pedidoservice.gateway.http.carrinho.json.CarrinhoJson;

@FeignClient(value = "CARRINHO-SERVICE", path = "carrinhos")
public interface CarrinhoServiceFeignClient {

	@GetMapping("{id}")
	CarrinhoJson obterPorId(@PathVariable(value = "id") Long id);
	
	@PatchMapping("{id}")
	void inativar(@PathVariable(value = "id") Long id);
}
