package com.grupo16.pedidoservice.gateway.http.estoque;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ESTOQUE-SERVICE")
public interface EstoqueServiceFeignClient {

}
