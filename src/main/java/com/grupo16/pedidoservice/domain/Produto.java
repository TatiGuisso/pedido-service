package com.grupo16.pedidoservice.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Produto {
	private Long id;
	private BigDecimal preco;
}
