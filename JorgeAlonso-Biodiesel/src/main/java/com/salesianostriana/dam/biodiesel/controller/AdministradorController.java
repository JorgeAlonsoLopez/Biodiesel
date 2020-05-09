package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministradorController {

	@GetMapping ("/administrador/precios")
	public String precios (Model model) {
		
		return "administrador/Precios";
	}
	
	
	
	@GetMapping ("/administrador/pedidos_cliente")
	public String pedidosCliente (Model model) {
		
		return "administrador/PedidosCliente";
	}
	
	
}
