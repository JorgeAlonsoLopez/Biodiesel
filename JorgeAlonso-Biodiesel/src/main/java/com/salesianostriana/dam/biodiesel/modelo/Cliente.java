package com.salesianostriana.dam.biodiesel.modelo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
public class Cliente extends Usuario{

	private String nombre, apellido, direccion, correo, telefono;
	
	@Column(unique = true)
	private String dni;
	
	private boolean alta;
	
	private boolean valido;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate fechaNacimiento;
	
	@ManyToOne
	private Pais pais;

	public Cliente(String usuario, String contrasenya, String nombre, String apellido,
			String direccion, String correo, String telefono, String dni, boolean alta,
			LocalDate fechaNacimiento, Pais pais) {
		super(usuario, contrasenya);
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.alta = alta; 
		this.fechaNacimiento = fechaNacimiento;
		this.pais = pais;
	}

	public Cliente(String usuario, String contrasenya, String nombre, String apellido, String direccion, String correo,
			String telefono, String dni, boolean alta, LocalDate fechaNacimiento) {
		super(usuario, contrasenya);
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.correo = correo;
		this.telefono = telefono;
		this.dni = dni;
		this.alta = alta;
		this.fechaNacimiento = fechaNacimiento;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "ROLE_";
		if (valido) {
			role += "USER";
		} else {
			role += "PENDT";
		}
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}
	
	
	
}
