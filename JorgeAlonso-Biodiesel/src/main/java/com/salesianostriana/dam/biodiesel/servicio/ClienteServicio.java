package com.salesianostriana.dam.biodiesel.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.Pedido;
import com.salesianostriana.dam.biodiesel.repositorio.ClienteRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class ClienteServicio extends BaseService<Cliente, Long, ClienteRepository> {

	public ClienteServicio(ClienteRepository repo) {
		super(repo);
	}

	public void cancelarPedido(long id, PedidoServicio servicio) {
		servicio.deleteById(id);
	}

//	public void realizarPedido() {
//		
//	}

	public void cambiarDatos(long id, String nombre, String apellido, String direccion, String telefono, String correo,
			long pais, String contrasenya, PaisServicio paisServ) {

		Cliente clie = this.findById(id);
		clie.setNombre(nombre);
		clie.setApellido(apellido);
		clie.setDireccion(direccion);
		clie.setTelefono(telefono);
		clie.setCorreo(correo);
		clie.setContrasenya(contrasenya);
		clie.setPais(paisServ.findById(pais));

		this.edit(clie);

	}

	public void cambiarEstado(long id) {
		if (this.findById(id).isDadoAlta()) {
			this.findById(id).setDadoAlta(false);
		} else {
			this.findById(id).setDadoAlta(true);
		}

	}

	public List<Pedido> ordernarFechaEntrega(List<Pedido> lista) {
		lista = lista.stream().sorted((f1, f2) -> f1.getFechaLlegada().compareTo(f2.getFechaLlegada()))
				.collect(Collectors.toList());
		return lista;
	}

	public List<Pedido> ordernarFechaSalida(List<Pedido> lista) {
		lista = lista.stream()
				.sorted((f1, f2) -> f1.getFechaSalidaTrasnporte().compareTo(f2.getFechaSalidaTrasnporte()))
				.collect(Collectors.toList());
		return lista;
	}
	
	public Cliente permitirAcceso(Long id) {
		return this.findById(id);
	}

}
