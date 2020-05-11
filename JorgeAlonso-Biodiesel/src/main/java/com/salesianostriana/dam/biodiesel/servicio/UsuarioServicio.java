package com.salesianostriana.dam.biodiesel.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Usuario;
import com.salesianostriana.dam.biodiesel.repositorio.UsuarioRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class UsuarioServicio extends BaseService<Usuario, Long, UsuarioRepository> {

	public UsuarioServicio(UsuarioRepository repo) {
		super(repo);
	}

}
