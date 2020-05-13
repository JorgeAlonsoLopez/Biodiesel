package com.salesianostriana.dam.biodiesel.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	

	
	
}
