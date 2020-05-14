package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Administrador extends Usuario{

	private double precioTren, precioBarco;

	public Administrador(String usuario, String contrasenya, double precioTren, double precioBarco) {
		super(usuario, contrasenya);
		this.precioTren = precioTren;
		this.precioBarco = precioBarco;
	}

	
	
	
	
}
