package com.salesianostriana.dam.biodiesel.modelo;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @MappedSuperclass
public abstract class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(unique = true)
	private String usuario;
	
	private String contrasenya;

	public Usuario(String usuario, String contrasenya) {
		super();
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}

	
}
