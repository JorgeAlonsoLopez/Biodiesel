package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;

public class ClienteController {

	@Autowired
	private ClienteServicio servicio;
	
	@GetMapping("/administrador/clientes_pendientes/borrar/{id}")
	public String borrarClientePendiente(@PathVariable("id") Long id, Model model) {

		Cliente cliente = servicio.findById(id);

		if (cliente != null) {
			servicio.delete(cliente);
		}

		return "redirect:/administrador/clientes_pendientes";

	}
	
	@GetMapping("/administrador/clientes_pendientes/borrar/{id}")
	public String aceptarCliente(@PathVariable("id") Long id, Model model) {

		Cliente cliente = servicio.findById(id);

		if (cliente != null) {
			cliente.setAceptado(true);
			cliente.setDadoAlta(true);
			servicio.edit(cliente);
		}

		return "redirect:/administrador/clientes_pendientes";

	}
	
	@GetMapping("/administrador/borrar/{id}")
	public String borrarCliente(@PathVariable("id") Long id, Model model) {

		Cliente cliente = servicio.findById(id);

		if (cliente != null) {
			servicio.delete(cliente);
		}

		return "redirect:/administrador";

	}
	
	
}
