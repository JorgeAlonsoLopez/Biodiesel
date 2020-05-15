package com.salesianostriana.dam.biodiesel.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6767914261998068491L;

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
	
	@Override
	public String getUsername() {
		return usuario;
	}
	
	@Override
	public String getPassword() {
		return contrasenya;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
