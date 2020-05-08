package com.salesianostriana.dam.biodiesel.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class LineaPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double precio;

	private long cantidad;

	@ManyToOne
	private Compuesto compuesto;

	@ManyToOne
	private Pedido pedido;

	public LineaPedido(double precio, long cantidad, Compuesto compuesto) {
		super();
		this.precio = precio;
		this.cantidad = cantidad;
		this.compuesto = compuesto;
	}
	
}
