package com.grupo16.pedidoservice.gateway.repository.jpa.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo16.pedidoservice.gateway.repository.jpa.entity.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

}
