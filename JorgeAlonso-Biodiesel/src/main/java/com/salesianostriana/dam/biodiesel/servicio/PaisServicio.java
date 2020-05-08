package com.salesianostriana.dam.biodiesel.servicio;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.salesianostriana.dam.biodiesel.modelo.Pais;
import com.salesianostriana.dam.biodiesel.repositorio.PaisRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class PaisServicio extends BaseService<Pais, Long , PaisRepository>{

	public PaisServicio(PaisRepository repo) {
		super(repo);
	}
	
	
	public List<Pais> cargarListado(){
		List<Pais> result = new ArrayList<Pais>();

		String path = "classpath:paises.csv";
		try {
			// @formatter:off
			result = Files
						.lines(Paths.get(ResourceUtils.getFile(path).toURI()))
						.skip(1)
						.map(line -> {
							String[] values = line.split(",");
							return new Pais(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]) );
					}).collect(Collectors.toList());
 			// @formatter:on
			
		}catch (Exception e) {
			System.err.println("Error de lectura del fichero de datos de paises.");
			System.exit(-1);
		}
		

		return result;

	}
	
}
