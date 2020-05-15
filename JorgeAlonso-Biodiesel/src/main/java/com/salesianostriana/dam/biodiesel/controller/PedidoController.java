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

	

	@GetMapping("/cliente/borrar/{id}")
	public String borrarPedido(@PathVariable("id") Long id, Model model) {

		Pedido pedido = servicio.findById(id);

		if (pedido != null) {
			servicio.delete(pedido);
		}

		return "redirect:/cliente/principal";

	}

	@GetMapping("/cliente/productos")
	public String compra(Model model) {
		model.addAttribute("listaProductos", servicioCompue.buscarProductos());
		model.addAttribute("pedido", new PedidoFormulario());
		model.addAttribute("cliente", servicioCli.buscarPorDNI("58819681X"));
		model.addAttribute("admin", servicioAdm.findAll().get(num0));
		return "cliente/Productos";
	}

	@PostMapping("/cliente/productos/submit")
	public String compraForm(@ModelAttribute("pedido") PedidoFormulario pedido) {
		servicio.hacerPedido(servicioAdm.findAll().get(num0), pedido, servicioCli.buscarPorDNI("58819681X"), servicioCompue);
		return "redirect:/cliente/principal";
	}

	@GetMapping("/cliente/materias_primas")
	public String venta(Model model) {
		model.addAttribute("listaMaterias", servicioCompue.buscarMateriasPrimas());
		model.addAttribute("pedido", new PedidoFormulario());
		model.addAttribute("cliente", servicioCli.buscarPorDNI("58819681X"));
		model.addAttribute("admin", servicioAdm.findAll().get(num0));
		return "cliente/MateriasPrimas";
	}
	
	@PostMapping("/cliente/materias_primas/submit")
	public String ventaForm(@ModelAttribute("pedido") PedidoFormulario pedido) {
		servicio.hacerPedido(servicioAdm.findAll().get(num0), pedido, servicioCli.buscarPorDNI("58819681X"), servicioCompue);
		return "redirect:/cliente/principal";
	}

}
