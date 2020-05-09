package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.biodiesel.modelo.Pedido;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.PedidoServicio;

@Controller
public class PedidoController {

	@Autowired
	private PedidoServicio servicio;
	
	@Autowired
	private ClienteServicio servicioCli;
	
	@GetMapping ("/cliente")
	public String cliente (Model model) {
		
	//	model.addAttribute("lista", servicio.findAll());
	//	model.addAttribute("cliente",servicioCli.findById(7L));
		return "cliente/Cliente";
	}
	
	
//	@GetMapping("/nuevo")
//	public String nuevaProducto(Model model) {
//		model.addAttribute("producto", new Producto());
//		model.addAttribute("categorias", categoriaService.findAll());
//		return "admin/form-producto";
//	}
//
//	@PostMapping("/nuevo/submit")
//	public String submitNuevoProducto(Model model) {
//
//		productoService.save(producto);
//		return "redirect:/admin/producto/";
//
//	}
	
	@GetMapping("/cliente/borrar/{id}")
	public String borrarPedido(@PathVariable("id") Long id, Model model) {

		Pedido pedido = servicio.findById(id);

		if (pedido != null) {
			servicio.delete(pedido);
		}

		return "redirect:/cliente";

	}
	
	
}
