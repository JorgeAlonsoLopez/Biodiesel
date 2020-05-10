package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.ClienteFormulario;
import com.salesianostriana.dam.biodiesel.modelo.Pais;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.PaisServicio;

@Controller
public class ClienteController {

	@Autowired
	private ClienteServicio servicio;
	
	@Autowired
	private PaisServicio servicioPais;
	
	
	@GetMapping ("/nuevo")
	public String registro(Model model) {
		model.addAttribute("clienteFrom",new ClienteFormulario());
		model.addAttribute("listaPais", servicioPais.findAll());
		model.addAttribute("fecha", "");

		return "/cliente/Nuevo";
	}
	
	
	@PostMapping ("/nuevo/submit")
	public String submitRegistro(@ModelAttribute("clienteFrom") ClienteFormulario nuevoClienteFormulario) {
		Cliente clienteFinal = servicio.crearCliente(nuevoClienteFormulario);
		servicio.save(clienteFinal);
		return "redirect:/cliente";
	}
	
	
	
	@GetMapping ("/login")
	public String login(Model model) {
		
		return "/Login";
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
	
	@GetMapping("/administrador/borrar/{dni}")
	public String borrarCliente(@PathVariable("dni") String dni, Model model) {

		Cliente cliente = servicio.buscarPorDNI(dni);

		if (cliente != null) {
			servicio.delete(cliente);
		}

		return "redirect:/administrador";

	}
	
	
}
