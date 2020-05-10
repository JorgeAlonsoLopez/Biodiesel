package com.salesianostriana.dam.biodiesel.modelo;

import java.time.LocalDate;

import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ClienteFormulario extends Usuario{

private String nombre, apellido, direccion, correo, telefono, dni;
	
	private boolean aceptado;
	
	private String fechaNacimiento;
	
	@ManyToOne
	private Pais pais;

	public ClienteFormulario(String usuario, String contrasenya, String nombre, String apellido, String direccion,
			String correo, String telefono, String dni, boolean aceptado, String fechaNacimiento, Pais pais) {
		super(usuario, contrasenya);
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.aceptado = aceptado;
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
	}
	
	
	
	
}
