package com.grupo16.pedidoservice.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Item {
	private Produto produto;
	private Long quantidade;
}
