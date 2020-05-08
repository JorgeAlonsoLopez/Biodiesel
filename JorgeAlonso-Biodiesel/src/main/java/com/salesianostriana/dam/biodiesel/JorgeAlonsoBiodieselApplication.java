package com.salesianostriana.dam.biodiesel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.salesianostriana.dam.biodiesel.modelo.Administrador;
import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.Compuesto;
import com.salesianostriana.dam.biodiesel.modelo.Pais;
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
		public CommandLineRunner app(CompuestoServicio compuestoServicio, PedidoServicio pedidoServicio, ClienteServicio clienteServicio,
				PaisServicio paisServicio, AdministradorServicio administradorServicio) {
			return new CommandLineRunner() {
				@Override
				public void run(String... args) throws Exception {

					Compuesto comp1 = new Compuesto("Biodiesel", "compra", 2);
					Compuesto comp2 = new Compuesto("Frito", "venta", -10);
					Compuesto comp3 = new Compuesto("Glicerina", "compra", 1);
					compuestoServicio.save(comp1);
					compuestoServicio.save(comp2);
					compuestoServicio.save(comp3);

					Administrador admin = new Administrador("admin", "admin", 1.67, 89.4);
					administradorServicio.save(admin);
					
					Pais pais1 = new Pais("Pais 1", 3, 4);
					Pais pais2 = new Pais("Pais 2", 5, 6);
					paisServicio.save(pais1);
					paisServicio.save(pais2);
					
					
					Cliente c1 = new Cliente("AA", "aa", "Paco", "Perez", "Calle 2", "paco@gmail.com", "987654321", "12341234D", false, false, 
							LocalDate.now(), pais1);
					Cliente c2 = new Cliente("BB", "bb", "Pepe", "ñaaa", "Calle 22", "pepe@gmail.com", "123456789", "09876543E", true, true, 
							LocalDate.now(), pais2);
					clienteServicio.save(c1);
					clienteServicio.save(c2);
					
					
					Map<String, Integer> mapa1 = new HashMap<String, Integer>();
					mapa1.put("Biodiesel", 9);
					
					Map<String, Integer> mapa2 = new HashMap<String, Integer>();
					mapa2.put("Frito", 10);
					
					Map<String, Integer> mapa3 = new HashMap<String, Integer>();
					mapa3.put("Biodiesel", 5);
					mapa3.put("Glicerina", 5);
					
					LocalDate fecha1 = LocalDate.of(2020, 10, 20);
					LocalDate fecha2 = LocalDate.of(2020, 6, 3);
					LocalDate fecha3 = LocalDate.of(2020, 4, 10);
					
					pedidoServicio.hacerPedido(admin, mapa1, fecha1, c1, compuestoServicio);
					pedidoServicio.hacerPedido(admin, mapa2, fecha2, c1, compuestoServicio);
					pedidoServicio.hacerPedido(admin, mapa3, fecha3, c2, compuestoServicio);
					
					Cliente c3 = new Cliente("BB", "bb", "Pepe", "ñaaa", "Calle 22", "pepe@gmail.com", "123456789", "09876543E", true, true, 
							LocalDate.now(), pais2);
					clienteServicio.save(c3);
					pedidoServicio.hacerPedido(admin, mapa3, fecha3, c2, compuestoServicio);
					
					

				}
			};
		
	}
		
		

}
