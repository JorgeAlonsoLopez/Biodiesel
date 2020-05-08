package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.servicio.CompuestoServicio;

@Controller
public class CompuestoController {
	
	@Autowired
	private CompuestoServicio servicio;
	
	
	@GetMapping ("/administrador/gestionar_productos")
	public String gestionarProductos (Model model) {
		model.addAttribute("materiasPrimas", servicio.buscarMateriasPrimas());
		model.addAttribute("productos", servicio.buscarProductos());
		return "/administrador/GestionarProductos";
	}
	
	@GetMapping("/administrador/compuesto/borrar/{id}")
	public String borrarCompuesto(@PathVariable("id") Long id, Model model) {

		Compuesto compuesto = servicio.findById(id);

		if (compuesto != null) {
			servicio.delete(compuesto);
		}

		return "redirect:/administrador/gestionar_productos";

	}
	

}
