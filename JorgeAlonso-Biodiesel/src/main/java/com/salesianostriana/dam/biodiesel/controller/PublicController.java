package com.salesianostriana.dam.biodiesel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.ClienteFormulario;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.PaisServicio;
import com.salesianostriana.dam.biodiesel.servicio.UsuarioServicio;

@Controller
public class PublicController {

	@Autowired
	private ClienteServicio servicio;

	@Autowired
	private PaisServicio servicioPais;
	
	@Autowired
	private UsuarioServicio servicioUsur;

	@GetMapping("/nuevo")
	public String registro(Model model) {
		model.addAttribute("clienteFrom", new ClienteFormulario());
		model.addAttribute("listaPais", servicioPais.findAll());

		return "/nuevo";
	}

	@PostMapping("/nuevo/submit")
	public String submitRegistro(@ModelAttribute("clienteFrom") ClienteFormulario nuevoClienteFormulario, BCryptPasswordEncoder passwordEncoder) {
		Cliente clienteFinal = servicio.crearCliente(nuevoClienteFormulario);
		if (servicio.buscarPorDNI(clienteFinal.getDni()) == null) {
			if (servicioUsur.buscarPorUsuario(clienteFinal.getUsuario()).orElse(null) == null) {
				clienteFinal.setContrasenya(passwordEncoder.encode(clienteFinal.getContrasenya()));
				servicio.save(clienteFinal);
				return "redirect:/index";
			} else {
				return "redirect:/nuevo";
			}

		} else {
			return "redirect:/nuevo";
		}

	}

	@GetMapping({ "/", "/index" })
	public String index(Model model) {

		return "/index";
	}

}
