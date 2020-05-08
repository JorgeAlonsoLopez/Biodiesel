package com.salesianostriana.dam.biodiesel.servicio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

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
	
	public Cliente buscarPorDNI(String dni) {
		Cliente c1 = null;
		for(Cliente c : this.findAll()) {
			if(c.getDni()==dni) {
				c1 = c;
			}
		}
		return c1;
	}

	public List<Cliente> clientesPendientes(){
		List<Cliente> lista = new ArrayList<Cliente>();
		for(Cliente c : this.findAll()) {
			if(!c.isAceptado()) {
				lista.add(c);
			}
		}
		return lista;
	}
	
	public List<Cliente> clientesAceptados(){
		List<Cliente> lista = new ArrayList<Cliente>();
		for(Cliente c : this.findAll()) {
			if(c.isAceptado()) {
				lista.add(c);
			}
		}
		return lista;
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
	
	public List<Cliente> cargarListado(){
			List<Cliente> result = new ArrayList<Cliente>();

			String path = "classpath:clientes.csv";
			try {
				// @formatter:off
				result = Files
							.lines(Paths.get(ResourceUtils.getFile(path).toURI()))
							.skip(1)
							.map(line -> {
								String[] values = line.split(",");
								return new Cliente(values[2], values[3], values[0], values[1], values[8], values[5], values[6], values[4], false,
										createRandomDate(1960, 2000));
						}).collect(Collectors.toList());
	 			// @formatter:on
				
			}catch (Exception e) {
				System.err.println("Error de lectura del fichero de datos de clientes.");
				System.exit(-1);
			}
			

			return result;

		}
	
	public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
	
	public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
	
	

}
