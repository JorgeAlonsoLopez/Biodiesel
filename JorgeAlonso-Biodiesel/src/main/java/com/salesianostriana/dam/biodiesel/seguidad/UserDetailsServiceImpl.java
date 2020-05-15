package com.salesianostriana.dam.biodiesel.seguidad;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.servicio.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UsuarioServicio usuarioServicio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioServicio.buscarPorUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
	}

}
