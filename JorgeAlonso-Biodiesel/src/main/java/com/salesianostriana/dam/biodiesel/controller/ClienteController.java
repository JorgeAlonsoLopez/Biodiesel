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
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.PaisServicio;
import com.salesianostriana.dam.biodiesel.servicio.PedidoServicio;

@Controller
public class ClienteController {

	@Autowired
	private ClienteServicio servicio;
	
	@Autowired
	private PaisServicio servicioPais;
	
	@Autowired
	private PedidoServicio servicioPedido;
	
	@GetMapping("/cliente/principal")
	public String cliente(Model model) {
		model.addAttribute("listaPedidos", servicioPedido.findListByDni("58819681X"));
		model.addAttribute("cliente",servicio.buscarPorDNI("58819681X"));
		return "cliente/Cliente";
	}
	
	
	@GetMapping ("/cliente/ajustes")
	public String ajustes ( Model model) {
		model.addAttribute("clienteFrom", servicio.cambiarDatosAFalso(servicio.buscarPorDNI("58819681X")));
		model.addAttribute("cliente", servicio.buscarPorDNI("58819681X"));
		model.addAttribute("listaPais", servicioPais.findAll());
		return "cliente/Ajustes";
	}
	
	@GetMapping ("/cliente/ajustes/altaBaja/{dni}")
	public String darseAltaBaja (@PathVariable("dni") String dni, Model model) {
		Cliente cliente = servicio.buscarPorDNI(dni);
		if (cliente.isAlta()) {
			cliente.setAlta(false);
			servicio.edit(cliente);
		}else {
			cliente.setAlta(true);
			servicio.edit(cliente);
		}
		return "redirect:/cliente/principal";
	}
	
	@PostMapping ("/cliente/ajustes/submit")
	public String submitAjustes(@ModelAttribute("clienteFrom") ClienteFormulario nuevoClienteFormulario) {
		Cliente clienteFinal = servicio.cambiarDatosAVerdadero(nuevoClienteFormulario);
		servicio.edit(clienteFinal);
		return "redirect:/cliente/principal";
	}
	
	
	
	@GetMapping("/administrador/borrar/{id}")
	public String borrarCliente(@PathVariable("id") Long id, Model model) {

		Cliente cliente = servicio.findById(id);
		if (cliente != null) {
			servicio.delete(cliente);
		}
		return "redirect:/administrador/principal";

	}
	
	
}
