package com.salesianostriana.dam.biodiesel.modelo.formulario;


import java.time.LocalDate;

import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.salesianostriana.dam.biodiesel.modelo.Pais;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor 
public class ClienteFormulario{

	private String nombre, apellido, direccion, correo, telefono, dni, usuario, contrasenya;
	
	private boolean aceptado;
	
	private String fechaNacimiento;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaNacimientoLocal;
	
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

	public ClienteFormulario(String nombre, String apellido, String direccion, String correo, String telefono,
			String dni, String usuario, String contrasenya, boolean aceptado, String fechaNacimiento,
			LocalDate fechaNacimientoLocal, Pais pais) {
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
		this.fechaNacimientoLocal = fechaNacimientoLocal;
		this.pais = pais;
	}
	
	

	
	
	
	
	
}
