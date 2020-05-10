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
		model.addAttribute("elemento", new Compuesto());
		return "/administrador/GestionarProductos";
	}
	
	@GetMapping("/administrador/gestionar_productos/borrar/{nombre}")
	public String borrarCompuesto(@PathVariable("nombre") String nombre, Model model) {

		Compuesto compuesto = servicio.buscarPorNombre(nombre);

		if (compuesto != null) {
			compuesto.setActivo(false);
			servicio.edit(compuesto);
		}

		return "redirect:/administrador/gestionar_productos";

	}
	
	@PostMapping ("/administrador/gestionar_productos/submit")
	public String submitRegistro(@ModelAttribute("elemento") Compuesto compuest) {
		if(servicio.buscarPorNombre(compuest.getNombre()) != null) {
			servicio.buscarPorNombre(compuest.getNombre()).setPrecio(compuest.getPrecio());
			servicio.buscarPorNombre(compuest.getNombre()).setActivo(true);
			servicio.edit(servicio.buscarPorNombre(compuest.getNombre()));
		}else {
			compuest.setActivo(true);
			servicio.save(compuest);
		}
		return "redirect:/administrador/gestionar_productos";
	}
	

}
