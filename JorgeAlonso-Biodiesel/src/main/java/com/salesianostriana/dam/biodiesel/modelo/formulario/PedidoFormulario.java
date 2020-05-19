package com.salesianostriana.dam.biodiesel.modelo.formulario;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoFormulario {

	private String nombre;

	private double precio;

	private int cantidad;
	
	private String fecha;
	
	private LocalDate fechaLoc;

	public PedidoFormulario(String nombre, int cantidad, LocalDate fechaLoc) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.fechaLoc = fechaLoc;
	}
	
	
}
