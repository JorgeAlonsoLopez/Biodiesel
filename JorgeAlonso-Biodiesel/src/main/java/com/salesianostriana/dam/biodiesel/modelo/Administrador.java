package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @Entity
public class Administrador extends Usuario{

	private double precioTren, precioBarco;

	public Administrador(String usuario, String contrasenya, double precioTren, double precioBarco) {
		super(usuario, contrasenya);
		this.precioTren = precioTren;
		this.precioBarco = precioBarco;
	}
	
}
