package com.salesianostriana.dam.biodiesel.modelo;


import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
public class ClienteFormulario{

	private String nombre, apellido, direccion, correo, telefono, dni, usuario, contrasenya;
	
	private boolean aceptado;
	
	private String fechaNacimiento;
	
	@ManyToOne
	private Pais pais;

	public ClienteFormulario(String nombre, String apellido, String direccion, String correo, String telefono,
			String dni, String usuario, String contrasenya, boolean aceptado, String fechaNacimiento, Pais pais) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.usuario = usuario;
		this.contrasenya = contrasenya;
		this.aceptado = aceptado;
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
	}

	
	
	
	
	
}
