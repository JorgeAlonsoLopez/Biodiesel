package com.salesianostriana.dam.biodiesel.modelo;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;

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
public class Administrador extends Usuario{

	private double precioTren, precioBarco;

	public Administrador(String usuario, String contrasenya, double precioTren, double precioBarco) {
		super(usuario, contrasenya);
		this.precioTren = precioTren;
		this.precioBarco = precioBarco;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	
	
	
	
}
