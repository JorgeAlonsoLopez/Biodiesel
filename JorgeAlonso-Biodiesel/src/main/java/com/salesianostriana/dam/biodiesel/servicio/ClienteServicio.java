package com.salesianostriana.dam.biodiesel.servicio;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.formulario.ClienteFormulario;
import com.salesianostriana.dam.biodiesel.repositorio.ClienteRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class ClienteServicio extends BaseService<Cliente, Long, ClienteRepository> {

	public ClienteServicio(ClienteRepository repo) {
		super(repo);
	}
	
	public List<Cliente> listarClientesAceptados() {
		List<Cliente> lista = new ArrayList<Cliente>();
		for(Cliente c: this.findAll()) {
			if(c.isValido()) {
				lista.add(c);
			}
		}
		return lista;
	}
	
	public List<Cliente> listarClientesPendientes() {
		List<Cliente> lista = new ArrayList<Cliente>();
		for(Cliente c: this.findAll()) {
			if(!c.isValido()) {
				lista.add(c);
			}
		}
		return lista;
	}
	

	public void cancelarPedido(long id, PedidoServicio servicio) {
		servicio.deleteById(id);
	}

	public Cliente buscarPorDNI(String dni) {
		Cliente c1 = null;
		for (Cliente c : this.findAll()) {
			if (c.getDni().equals(dni)) {
				c1 = c;
			}
		}
		return c1;
	}
	

	public List<Cliente> listarClientesAlta() {
		List<Cliente> lista = new ArrayList<Cliente>();
		for (Cliente c : this.listarClientesAceptados()) {
			if (!c.isAlta()) {
				lista.add(c);
			}
		}
		return lista;
	}

	public List<Cliente> listarClientesBaja() {
		List<Cliente> lista = new ArrayList<Cliente>();
		for (Cliente c : this.listarClientesAceptados()) {
			if (c.isAlta()) {
				lista.add(c);
			}
		}
		return lista;
	}


	public Cliente cambiarDatosAVerdadero(ClienteFormulario formulario) {

		Cliente clie = this.buscarPorDNI(formulario.getDni());
		clie.setNombre(formulario.getNombre());
		clie.setApellido(formulario.getApellido());
		clie.setDireccion(formulario.getDireccion());
		clie.setTelefono(formulario.getTelefono());
		clie.setCorreo(formulario.getCorreo());
		clie.setContrasenya(formulario.getContrasenya());
		clie.setPais(formulario.getPais());

		clie.setFechaNacimiento(formulario.getFechaNacimientoLocal());

		return clie;

	}

	public ClienteFormulario cambiarDatosAFalso(Cliente cliente) {

		ClienteFormulario clie = new ClienteFormulario();
		clie.setNombre(cliente.getNombre());
		clie.setApellido(cliente.getApellido());
		clie.setDireccion(cliente.getDireccion());
		clie.setTelefono(cliente.getTelefono());
		clie.setCorreo(cliente.getCorreo());
		clie.setContrasenya(cliente.getContrasenya());
		clie.setPais(cliente.getPais());
		clie.setUsuario(cliente.getUsuario());
		clie.setDni(cliente.getDni());
		
		clie.setFechaNacimientoLocal(cliente.getFechaNacimiento());

		return clie;

	}

	public Cliente crearCliente(ClienteFormulario formulario) {
		int num0 = 0, num4 = 4, num5 = 5, num7 = 7, num8 = 8, num10 = 10;
		int dia, mes, anyo;

		Cliente clie = new Cliente();
		clie.setNombre(formulario.getNombre());
		clie.setApellido(formulario.getApellido());
		clie.setDni(formulario.getDni());
		clie.setDireccion(formulario.getDireccion());
		clie.setTelefono(formulario.getTelefono());
		clie.setCorreo(formulario.getCorreo());
		clie.setContrasenya(formulario.getContrasenya());
		clie.setPais(formulario.getPais());
		clie.setUsuario(formulario.getUsuario());
		clie.setAlta(true);

		anyo = Integer.parseInt(formulario.getFechaNacimiento().substring(num0, num4));
		mes = Integer.parseInt(formulario.getFechaNacimiento().substring(num5, num7));
		dia = Integer.parseInt(formulario.getFechaNacimiento().substring(num8, num10));

		clie.setFechaNacimiento(LocalDate.of(anyo, mes, dia));

		return clie;

	}

	public List<Cliente> cargarListado() {
		List<Cliente> result = new ArrayList<Cliente>();

		String path = "classpath:clientes.csv";
		try {
			// @formatter:off
			result = Files.lines(Paths.get(ResourceUtils.getFile(path).toURI())).skip(1).map(line -> {
				String[] values = line.split(",");
				if(values[10].equals("false")) {
					return new Cliente(values[2], values[3], values[0], values[1], values[8], values[5], values[6], values[4], false,
							createRandomDate(1960, 2000));
				}else {
					return new Cliente(values[2], values[3], values[0], values[1], values[8], values[5], values[6], values[4], true,
							createRandomDate(1960, 2000));
				}
			}).collect(Collectors.toList());
			// @formatter:on

		} catch (Exception e) {
			System.err.println("Error de lectura del fichero de datos de clientes.");
			System.exit(-1);
		}

		return result;

	}

	public LocalDate createRandomDate(int startYear, int endYear) {
		int day = createRandomIntBetween(1, 28);
		int month = createRandomIntBetween(1, 12);
		int year = createRandomIntBetween(startYear, endYear);
		return LocalDate.of(year, month, day);
	}

	public int createRandomIntBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

}
