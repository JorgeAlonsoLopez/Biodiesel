package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.formulario.ClienteFormulario;
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
	public String cliente(Model model, @AuthenticationPrincipal Cliente clienteLog) {
		model.addAttribute("listaPedidos", servicioPedido.findListByDni(clienteLog.getDni()));
		model.addAttribute("cliente",servicio.buscarPorDNI(clienteLog.getDni()));
		return "cliente/Cliente";
	}
	
	
	@GetMapping ("/cliente/ajustes")
	public String ajustes ( Model model, @AuthenticationPrincipal Cliente clienteLog) {
		model.addAttribute("clienteFrom", servicio.cambiarDatosAFalso(servicio.buscarPorDNI(clienteLog.getDni())));
		model.addAttribute("cliente", servicio.buscarPorDNI(clienteLog.getDni()));
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
	
}
