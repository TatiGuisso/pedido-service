package com.grupo16.pedidoservice.exception;

import lombok.Getter;

@Getter
public class EstoqueInsuficienteException extends SystemBaseException {
	private static final long serialVersionUID = -5080503656342392591L;
	
	private final String code = "pedido.estoqueIndisponivel";//NOSONAR
	private final String message = "Não há estoque para o produto selecionado.";//NOSONAR
	private final Integer httpStatus = 422;//NOSONAR
}
