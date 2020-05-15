package com.salesianostriana.dam.biodiesel.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Usuario;
import com.salesianostriana.dam.biodiesel.repositorio.UsuarioRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class UsuarioServicio extends BaseService<Usuario, Long, UsuarioRepository>{

	public UsuarioServicio(UsuarioRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	public Optional<Usuario> buscarPorUsuario(String usuario) {
		return repositorio.findFirstByUsuario(usuario);
	}

}
