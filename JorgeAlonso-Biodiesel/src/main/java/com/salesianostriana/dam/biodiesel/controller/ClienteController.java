package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;

@Controller
public class ClienteController {

	@Autowired
	private ClienteServicio servicio;
	
	
	@GetMapping ("/nuevo")
	public String registro(Model model) {
		
		return "/cliente/Nuevo";
	}
	
	@GetMapping ("/login")
	public String login(Model model) {
		
		return "/Login";
	}
	
	
	
	@GetMapping ("/administrador/clientes_pendientes")
	public String clientesPendientes (Model model) {
		model.addAttribute("listaClientes", servicio.clientesPendientes());
		return "/administrador/ClientesPendientes";
	}
	
	@GetMapping ("/administrador")
	public String clientesTotales(Model model) {
		model.addAttribute("listaClientes", servicio.clientesAceptados());
		return "/administrador/Administrador";
	}
	
	
	
	@GetMapping("/administrador/clientes_pendientes/borrar/{dni}")
	public String borrarClientePendiente(@PathVariable("dni") String dni, Model model) {

		Cliente cliente = servicio.buscarPorDNI(dni);

		if (cliente != null) {
			servicio.delete(cliente);
		}

		return "redirect:/administrador/clientes_pendientes";

	}
	
	@GetMapping("/administrador/clientes_pendientes/aceptar/{dni}")
	public String aceptarCliente(@PathVariable("dni") String dni, Model model) {

		Cliente cliente = servicio.buscarPorDNI(dni);

		if (cliente != null) {
			cliente.setAceptado(true);
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

		return "redirect:/administrador/Administrador";

	}
	
	
}
