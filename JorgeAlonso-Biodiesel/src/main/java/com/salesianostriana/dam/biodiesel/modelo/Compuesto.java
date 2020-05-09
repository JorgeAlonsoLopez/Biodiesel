package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Compuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 

	private String nombre; 

	private String tipo; 

	private double precio; 

	private boolean activo;

	public Compuesto(String nombre, String tipo, double precio, boolean activo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
		this.activo = activo;
	}
	
	
}
