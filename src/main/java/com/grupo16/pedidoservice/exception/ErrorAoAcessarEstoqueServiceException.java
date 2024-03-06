package com.grupo16.pedidoservice.exception;

import lombok.Getter;

@Getter
public class ErrorAoAcessarEstoqueServiceException extends SystemBaseException {
	private static final long serialVersionUID = 3172051907551241565L;
	
	private final String code = "pedido.erroAcessarEstoqueService";//NOSONAR
	private final String message = "Erro ao acessar o estoque service.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
