package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.biodiesel.modelo.Pedido;
import com.salesianostriana.dam.biodiesel.modelo.PedidoFormulario;
import com.salesianostriana.dam.biodiesel.servicio.AdministradorServicio;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.CompuestoServicio;
import com.salesianostriana.dam.biodiesel.servicio.PedidoServicio;

@Controller
public class PedidoController {

	int num0 = 0;

	@Autowired
	private PedidoServicio servicio;

	@Autowired
	private ClienteServicio servicioCli;

	@Autowired
	private CompuestoServicio servicioCompue;

	@Autowired
	private AdministradorServicio servicioAdm;

	@GetMapping("/cliente")
	public String cliente(Model model) {

		// model.addAttribute("lista", servicio.findAll());
		// model.addAttribute("cliente",servicioCli.findById(7L));
		return "cliente/Cliente";
	}

	@GetMapping("/cliente/borrar/{id}")
	public String borrarPedido(@PathVariable("id") Long id, Model model) {

		Pedido pedido = servicio.findById(id);

		if (pedido != null) {
			servicio.delete(pedido);
		}

		return "redirect:/cliente";

	}

	@GetMapping("/cliente/Productos")
	public String compra(Model model) {
		model.addAttribute("listaProductos", servicioCompue.buscarProductos());
		model.addAttribute("pedido", new PedidoFormulario());
		model.addAttribute("cliente", servicioCli.buscarPorDNI("180-96-0663"));
		model.addAttribute("admin", servicioAdm.findAll().get(num0));
		return "cliente/Productos";
	}

	@PostMapping("/cliente/Productos/submit")
	public String compraForm(@ModelAttribute("pedido") PedidoFormulario pedido) {
		servicio.hacerPedido(servicioAdm.findAll().get(num0), pedido, servicioCli.buscarPorDNI("180-96-0663"), servicioCompue);
		return "redirect:/cliente";
	}

	@GetMapping("/cliente/MateriasPrimas")
	public String venta(Model model) {
		model.addAttribute("listaMaterias", servicioCompue.buscarMateriasPrimas());
		model.addAttribute("pedido", new PedidoFormulario());
		model.addAttribute("cliente", servicioCli.buscarPorDNI("180-96-0663"));
		model.addAttribute("admin", servicioAdm.findAll().get(num0));
		return "cliente/MateriasPrimas";
	}
	
	@PostMapping("/cliente/MateriasPrimas/submit")
	public String ventaForm(@ModelAttribute("pedido") PedidoFormulario pedido) {
		servicio.hacerPedido(servicioAdm.findAll().get(num0), pedido, servicioCli.buscarPorDNI("180-96-0663"), servicioCompue);
		return "redirect:/cliente";
	}

}
