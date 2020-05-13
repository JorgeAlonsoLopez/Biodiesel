package com.salesianostriana.dam.biodiesel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.salesianostriana.dam.biodiesel.modelo.Administrador;
import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.modelo.Pais;
import com.salesianostriana.dam.biodiesel.modelo.PedidoFormulario;
import com.salesianostriana.dam.biodiesel.servicio.AdministradorServicio;
import com.salesianostriana.dam.biodiesel.servicio.ClienteServicio;
import com.salesianostriana.dam.biodiesel.servicio.CompuestoServicio;
import com.salesianostriana.dam.biodiesel.servicio.PaisServicio;
import com.salesianostriana.dam.biodiesel.servicio.PedidoServicio;

@SpringBootApplication
public class JorgeAlonsoBiodieselApplication {

	public static void main(String[] args) {
		SpringApplication.run(JorgeAlonsoBiodieselApplication.class, args);
	}

	@Bean
	public CommandLineRunner app(CompuestoServicio compuestoServicio, PedidoServicio pedidoServicio,
			ClienteServicio clienteServicio, PaisServicio paisServicio, AdministradorServicio administradorServicio) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				Random rdn = new Random(System.nanoTime());
				int aleatorio, aleatorio2, rand, num2 = 2, num11=11, num60 = 60, max = 300000, min = 10000;
				String tipo;

				List<Compuesto> listaCompuestos = new ArrayList<Compuesto>();
				listaCompuestos.add(new Compuesto("Biodiesel", "compra", 0.86, true));
				listaCompuestos.add(new Compuesto("Aceite de cogeneración", "compra", 0.34, true));
				listaCompuestos.add(new Compuesto("Glicerina", "compra", 1.37, true));
				listaCompuestos.add(new Compuesto("Aceite de motor", "venta", -0.25, true));
				listaCompuestos.add(new Compuesto("Aceite de fritura", "venta", -0.36, true));
				listaCompuestos.add(new Compuesto("Restos vegetales", "venta", -0.13, true));
				for (Compuesto c : listaCompuestos) {
					compuestoServicio.save(c);
				}

				Administrador admin = new Administrador("admin", "admin", 1200, 800);
				administradorServicio.save(admin);

				// usuario 1234

				for (Pais p : paisServicio.cargarListado()) {
					paisServicio.save(p);
				}

				for (Cliente c : clienteServicio.cargarListado()) {
					aleatorio = rdn.nextInt(paisServicio.findAll().size());
					c.setPais(paisServicio.findAll().get(aleatorio));
					clienteServicio.save(c);
				}
				
				Cliente clienteTest = new Cliente("usuario", "1234", "Juan", "Bosco", "Calle Condes de Bustillo, Nº17, 41010 Sevilla", 
						"juan.bosco@gmail.com", "954522209", "58819681X", true, clienteServicio.createRandomDate(1960, 2000));
				clienteTest.setPais( paisServicio.findAll().get(num11) );
				clienteServicio.save(clienteTest);

				for (int i = 0; i < num60; i++) {
					aleatorio = rdn.nextInt(clienteServicio.findAll().size());
					aleatorio2 = rdn.nextInt(compuestoServicio.findAll().size());

					LocalDate fecha = clienteServicio.createRandomDate(2017, 2021);
					
					PedidoFormulario PedidoF = new PedidoFormulario(
							(compuestoServicio.findAll().get(aleatorio2).getNombre()), rdn.nextInt(max - min) + min,
							fecha);

					pedidoServicio.hacerPedido(admin, PedidoF, clienteServicio.findAll().get(aleatorio), compuestoServicio);
				}
				
				

			}
		};

	}

}