package com.salesianostriana.dam.biodiesel.servicio;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Pais;
import com.salesianostriana.dam.biodiesel.repositorio.PaisRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class PaisServicio extends BaseService<Pais, Long , PaisRepository>{

	public PaisServicio(PaisRepository repo) {
		super(repo);
	}
}
