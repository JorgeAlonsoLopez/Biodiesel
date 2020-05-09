package com.salesianostriana.dam.biodiesel.servicio;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Administrador;
import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.repositorio.AdministradorRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class AdministradorServicio extends BaseService<Administrador, Long, AdministradorRepository> {

	public AdministradorServicio(AdministradorRepository repo) {
		super(repo);
	}

	public void aceptarCliente(long id, ClienteServicio servicio) {
		servicio.findById(id).setAceptado(true);
		servicio.edit(servicio.findById(id));
	}

	public void eliminarCliente(long id, ClienteServicio servicio) {
		servicio.deleteById(id);
	}

	public void eliminarCompuesto(String compuesto, CompuestoServicio servicio) {
		servicio.buscarPorNombre(compuesto).setActivo(false);
		servicio.edit(servicio.buscarPorNombre(compuesto));
	}

	public void anyadirCompuesto(String nombre, String tipo, double precio, CompuestoServicio servicio) {
		Compuesto comp = new Compuesto(nombre, tipo, precio, true);
		servicio.save(comp);
	}

	public void actualizarPrecios(Map<String, Double> preciosProductos, Map<String, Double> preciosTransporte,
			CompuestoServicio servicioComp, AdministradorServicio servicioAdmin, Administrador admin) {
		
		for (Entry<String, Double> c : preciosProductos.entrySet()) {
			Compuesto comp = servicioComp.buscarPorNombre(c.getKey());
			comp.setPrecio(c.getValue().doubleValue());
			servicioComp.edit(comp);
		}
		
		for (Entry<String, Double> a : preciosTransporte.entrySet()) {
			if(a.getKey()=="barco") {
				admin.setPrecioBarco(a.getValue().doubleValue());
			}else {
				admin.setPrecioTren(a.getValue().doubleValue());
			}			
			servicioAdmin.edit(admin);
		}

	}
}
