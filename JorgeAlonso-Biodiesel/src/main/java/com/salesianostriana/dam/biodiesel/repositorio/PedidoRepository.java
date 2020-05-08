package com.salesianostriana.dam.biodiesel.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.biodiesel.modelo.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	
	@Query("select distinct p from Pedido p join fetch p.listaPedidos")
	List<Pedido> findAllJoin();
}
