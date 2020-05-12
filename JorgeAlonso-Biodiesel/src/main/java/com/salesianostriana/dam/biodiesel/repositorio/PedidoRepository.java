package com.salesianostriana.dam.biodiesel.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.biodiesel.modelo.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("select distinct p from Pedido p join fetch p.listaPedidos")
	List<Pedido> findAllJoin();

//	@Query(value = "SELECT DISTINCT P "
//			+ "FROM PEDIDO P JOIN CLIENTE C ON (P.CLIENTE=C) "
//			+ "WHERE C.DNI = ?1", nativeQuery = true)
//	List<Pedido> findListByDni(@Param("dni") String dni);

	
	@Query("SELECT p FROM Pedido p JOIN p.cliente c WHERE c.dni = :dni")
	List<Pedido> findListByDni(@Param("dni") String dni);
	
	
	
}
