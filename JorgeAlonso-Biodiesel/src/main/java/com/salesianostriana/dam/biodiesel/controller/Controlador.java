package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.biodiesel.repositorio.PedidoRepository;

@Controller
public class Controlador {


	@GetMapping ("/cliente")
	public String cliente (Model model) {
		
		//model.addAttribute("lista", repo.findAllById());
		
		return "cliente/Cliente";
	}
	
	@GetMapping ("/cliente/ajustes")
	public String ajustes (Model model) {
		
		return "cliente/Ajustes";
	}
	
	@GetMapping ("/cliente/Productos")
	public String compra (Model model) {
		
		return "cliente/Productos";
	}
	
	@GetMapping ("/cliente/MateriasPrimas")
	public String venta (Model model) {
		
		return "cliente/MateriasPrimas";
	}
	

	
	@GetMapping ("/administrador/precios")
	public String precios (Model model) {
		
		return "administrador/Precios";
	}
	
	
	
	@GetMapping ("/administrador/pedidos_cliente")
	public String pedidosCliente (Model model) {
		
		return "administrador/PedidosCliente";
	}
}
