package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @Entity
public class Pais {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPais;
	
	private String nombre;
	
	private int numDiasBarco, numDiasTren;

	public Pais(String nombre, int numDiasBarco, int numDiasTren) {
		super();
		this.nombre = nombre;
		this.numDiasBarco = numDiasBarco;
		this.numDiasTren = numDiasTren;
	}
	
}
