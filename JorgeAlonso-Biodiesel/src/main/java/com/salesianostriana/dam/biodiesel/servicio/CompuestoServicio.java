package com.salesianostriana.dam.biodiesel.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.repositorio.CompuestoRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class CompuestoServicio extends BaseService<Compuesto, Long, CompuestoRepository>{

	public CompuestoServicio(CompuestoRepository repo) {
		super(repo);
	}

	public Compuesto buscarPorNombre(String nombre) {
		Compuesto c = this.findAll().stream().filter(e -> e.getNombre().equals(nombre)).findAny().orElse(null);
		return c;
	}
	
	public List<Compuesto> buscarMateriasPrimas(){
		List<Compuesto> lista = new ArrayList<Compuesto>();
		for(Compuesto p : this.findAll()) {
			if(p.getTipo()=="venta") {
				lista.add(p);
			}
		}
		if(lista.isEmpty()) {
			return null;
		}else {
			return lista;
		}
	}
	
	public List<Compuesto> buscarProductos(){
		List<Compuesto> lista = new ArrayList<Compuesto>();
		for(Compuesto p : this.findAll()) {
			if(p.getTipo()=="compra") {
				lista.add(p);
			}
		}
		if(lista.isEmpty()) {
			return null;
		}else {
			return lista;
		}
	}
	
	
	
}