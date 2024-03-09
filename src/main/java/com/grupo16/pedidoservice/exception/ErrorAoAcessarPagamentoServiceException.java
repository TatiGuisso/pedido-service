package com.grupo16.pedidoservice.exception;

import lombok.Getter;

@Getter
public class ErrorAoAcessarPagamentoServiceException extends SystemBaseException {
	private static final long serialVersionUID = 3172051907551241565L;
	
	private final String code = "pedido.erroAcessarPagamentoService";//NOSONAR
	private final String message = "Erro ao acessar o pagamento service.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
