package com.grupo16.pedidoservice.exception;

import lombok.Getter;

@Getter
public class SystemExternalException extends SystemBaseException {
	private static final long serialVersionUID = -1483019469191565764L;
	
	private String code;
	private String message;
	private Integer httpStatus;
}
