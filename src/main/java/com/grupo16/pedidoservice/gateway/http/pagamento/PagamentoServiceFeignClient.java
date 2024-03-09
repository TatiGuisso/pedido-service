package com.grupo16.pedidoservice.gateway.http.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo16.pedidoservice.gateway.http.pagamento.json.PagamentoJson;

@FeignClient(value = "PAGAMENTO-SERVICE", path = "pagamentos")
public interface PagamentoServiceFeignClient {
	
	@PostMapping()
	Long criar(@RequestBody PagamentoJson pagamentoJson); 

}
