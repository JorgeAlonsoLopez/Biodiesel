package com.salesianostriana.dam.biodiesel.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	double precioCompuestos, precioTrasporte, precioFinal; 

	private LocalDate fechaLlegada, fechaSalidaTrasnporte;
	
	@ManyToOne
	private Cliente cliente;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude 
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineaPedido> listaPedidos = new ArrayList<>();

	public Pedido(double precioCompuestos, double precioTrasporte, double precioFinal, LocalDate fechaLlegada,
			LocalDate fechaSalidaTrasnporte, Cliente cliente) {
		super();
		this.precioCompuestos = precioCompuestos;
		this.precioTrasporte = precioTrasporte;
		this.precioFinal = precioFinal;
		this.fechaLlegada = fechaLlegada;
		this.fechaSalidaTrasnporte = fechaSalidaTrasnporte;
		this.cliente = cliente;
	}

	public void addLinea(LineaPedido linea) {
		linea.setPedido(this);
		this.listaPedidos.add(linea);
	}

	public void deleteLinea(LineaPedido linea) {
		this.listaPedidos.remove(linea);
		linea.setPedido(null);
	}
	
}
