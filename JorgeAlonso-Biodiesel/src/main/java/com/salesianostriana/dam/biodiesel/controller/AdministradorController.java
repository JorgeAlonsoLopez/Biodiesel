package com.salesianostriana.dam.biodiesel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.biodiesel.modelo.Administrador;
import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.servicio.AdministradorServicio;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.CompuestoServicio;

@Controller
public class AdministradorController {
	
	int num0=0;
	
	@Autowired
	private AdministradorServicio servicio;
	
	@Autowired
	private CompuestoServicio servicioCompu;
	
	

	@GetMapping("/administrador/precios")
	public String actualizarMateria(Model model) {
		model.addAttribute("listaMateriasPrimas", servicioCompu.buscarMateriasPrimas());
		model.addAttribute("listaProductos", servicioCompu.buscarProductos());
		model.addAttribute("administrador", servicio.findAll().get(num0));
		return "/administrador/Precios";
	}
	
	@GetMapping("/administrador/precios/{idMateria}")
	public String actualizarPrecioMateria(@PathVariable("idMateria") String idMateria, @RequestParam(name = "precio") Double precio, Model model) {
//		model.addAttribute("listaMateriasPrimas", servicioCompu.buscarMateriasPrimas());
//		model.addAttribute("listaProductos", servicioCompu.buscarProductos());
//		model.addAttribute("administrador", servicio.findAll().get(num0));
		System.out.println(idMateria);
		System.out.println(precio);
		return "redirect:/administrador/precios";
	}
	
	@PostMapping ("/administrador/precios/submitM")
	public String cambioPreciosMateriasPrimas(@ModelAttribute("listaMateriasPrimas") List<Compuesto> listaM) {
		for(Compuesto c : listaM) {
			servicioCompu.edit(c);
		}
		return "redirect:/administrador/precios";
	}
	
	@PostMapping ("/administrador/precios/submitP")
	public String cambioPreciosProductos(@ModelAttribute("listaProductos") List<Compuesto> listaP) {
		for(Compuesto c : listaP) {
			servicioCompu.edit(c);
		}
		return "redirect:/administrador/precios";
	}
	
	@PostMapping ("/administrador/precios/submitT")
	public String cambioPreciosTranspote(@ModelAttribute("administrador") Administrador admin) {
			servicio.edit(admin);
		
		return "redirect:/administrador/precios";
	}
	
	
	@GetMapping ("/administrador/pedidos_cliente")
	public String pedidosCliente (Model model) {
		
		return "administrador/PedidosCliente";
	}
	
	
}
