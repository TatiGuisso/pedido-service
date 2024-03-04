package com.grupo16.pedidoservice.gateway.repository.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Pedido")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long carrinhoId;
	private Long usuarioId;
	private Long status;
	private BigDecimal precoTotal;
}
