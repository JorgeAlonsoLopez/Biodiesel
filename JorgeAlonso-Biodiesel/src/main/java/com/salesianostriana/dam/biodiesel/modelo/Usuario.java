package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @MappedSuperclass
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private long idUsuario;
	
	private String usuario, contrasenya;

	public Usuario(String usuario, String contrasenya) {
		super();
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}
	
}
