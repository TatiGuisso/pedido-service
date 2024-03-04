package com.grupo16.pedidoservice.exception;

import lombok.Getter;

@Getter
public class ErroAoAcessarBancoDeDadosException extends SystemBaseException {
	private static final long serialVersionUID = -106234706906362820L;
	
	private final String code = "pedido.erroAcessarDatabase";//NOSONAR
	private final String message = "Erro ao acessar o banco de dados.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
