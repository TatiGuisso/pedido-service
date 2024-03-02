package com.grupo16.pedidoservice.exception;

public abstract class SystemBaseException extends RuntimeException {
	private static final long serialVersionUID = -5287590663386103465L;
	
	public abstract String getCode();
	public abstract Integer getHttpStatus();
	@Override
	public abstract String getMessage();
}
