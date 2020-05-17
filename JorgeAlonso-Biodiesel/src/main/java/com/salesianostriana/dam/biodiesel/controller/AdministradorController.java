package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.ClienteFormulario;
import com.salesianostriana.dam.biodiesel.modelo.FormularioAdminPedido;
import com.salesianostriana.dam.biodiesel.modelo.Pedido;
import com.salesianostriana.dam.biodiesel.servicio.AdministradorServicio;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.CompuestoServicio;
import com.salesianostriana.dam.biodiesel.servicio.PedidoServicio;

@Controller
public class AdministradorController {

	int num0 = 0;

	@Autowired
	private AdministradorServicio servicio;

	@Autowired
	private CompuestoServicio servicioCompu;

	@Autowired
	private ClienteServicio servicioClien;

	@Autowired
	private PedidoServicio servicioPedido;

	@GetMapping("/administrador/precios")
	public String actualizarMateria(Model model) {
		model.addAttribute("listaMateriasPrimas", servicioCompu.buscarMateriasPrimas());
		model.addAttribute("listaProductos", servicioCompu.buscarProductos());
		model.addAttribute("administrador", servicio.findAll().get(num0));
		return "/administrador/Precios";
	}

	@GetMapping("/administrador/precios/{idMateria}")
	public String actualizarPrecio(@PathVariable("idMateria") String idMateria,
			@RequestParam(name = "precio") Double precio, Model model) {
		servicioCompu.findById(Long.parseLong(idMateria)).setPrecio(precio);
		servicioCompu.edit(servicioCompu.findById(Long.parseLong(idMateria)));
		return "redirect:/administrador/precios";
	}

	@GetMapping("/administrador/precios/admin/{id}")
	public String actualizarPrecioAdmin(@PathVariable("id") String id, @RequestParam(name = "precio") Double precio,
			Model model) {
		if (id.equals("1")) {
			servicio.findAll().get(num0).setPrecioTren(precio);
		} else {
			servicio.findAll().get(num0).setPrecioBarco(precio);
			;
		}
		servicio.edit(servicio.findAll().get(num0));
		return "redirect:/administrador/precios";
	}

	@GetMapping("/administrador/pedidos_cliente/{dni}")
	public String pedidosCliente(@PathVariable("dni") String dni, Model model) {
		model.addAttribute("listaPedidos", servicioPedido.findListByDni(dni));
		model.addAttribute("cliente", servicioClien.buscarPorDNI(dni));
		return "administrador/PedidosCliente";
	}

	@GetMapping("/administrador/principal")
	public String clientesTotales(Model model) {
		model.addAttribute("listaClientes", servicioClien.clientesAceptados());
		model.addAttribute("filtro", new ClienteFormulario());
		return "/administrador/Administrador";
	}

	@GetMapping("/administrador/borrar/{id}")
	public String borrarCliente(@PathVariable("id") Long id, Model model) {

		Cliente cliente = servicioClien.findById(id);
		if (cliente != null) {
			for (Pedido p : servicioPedido.findListByDni(cliente.getDni())) {
				servicioPedido.delete(p);
			}
			servicioClien.delete(cliente);
		}
		return "redirect:/administrador/principal";

	}

	@GetMapping("/administrador/clientes_pendientes")
	public String clientesPendientes(Model model) {
		model.addAttribute("listaClientes", servicioClien.clientesPendientes());
		return "/administrador/ClientesPendientes";
	}

	@GetMapping("/administrador/clientes_pendientes/borrar/{dni}")
	public String borrarClientePendiente(@PathVariable("dni") String dni, Model model) {
		Cliente cliente = servicioClien.buscarPorDNI(dni);
		if (cliente != null) {
			servicioClien.delete(cliente);
		}
		return "redirect:/administrador/clientes_pendientes";
	}

	@GetMapping("/administrador/clientes_pendientes/aceptar/{dni}")
	public String aceptarCliente(@PathVariable("dni") String dni, Model model) {
		Cliente cliente = servicioClien.buscarPorDNI(dni);
		if (cliente != null) {
			cliente.setValido(true);
			servicioClien.edit(cliente);
		}
		return "redirect:/administrador/clientes_pendientes";
	}

}
