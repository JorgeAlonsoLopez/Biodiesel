package com.salesianostriana.dam.biodiesel.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.biodiesel.modelo.Administrador;
import com.salesianostriana.dam.biodiesel.modelo.Cliente;
import com.salesianostriana.dam.biodiesel.modelo.LineaPedido;
import com.salesianostriana.dam.biodiesel.modelo.Pedido;
import com.salesianostriana.dam.biodiesel.repositorio.PedidoRepository;
import com.salesianostriana.dam.biodiesel.servicio.base.BaseService;

@Service
public class PedidoServicio extends BaseService<Pedido, Long, PedidoRepository> {

	public PedidoServicio(PedidoRepository repo) {
		super(repo);
	}

	@Override
	public List<Pedido> findAll() {
		return this.repositorio.findAllJoin();
	}

	public Pedido hacerPedido(Administrador admin, Map<String, Integer> lineas, LocalDate fechaLlegada, Cliente cliente,
			CompuestoServicio compuestoServicio) {
		double precioProductos = 0;
		int num0 = 0;

		int numDias = cliente.getPais().getNumDiasBarco() + cliente.getPais().getNumDiasTren();
		LocalDate fechaSalidaTrans = fechaLlegada.minusDays(numDias);
		double precioTrasporte = (admin.getPrecioBarco() * cliente.getPais().getNumDiasBarco())
				+ (admin.getPrecioTren() * cliente.getPais().getNumDiasTren());

		List<LineaPedido> listaLineasPedido = lineas.entrySet().stream()
				.map(e -> new LineaPedido(
						compuestoServicio.buscarPorNombre(e.getKey()).getPrecio() * e.getValue().intValue(),
						e.getValue().intValue(), compuestoServicio.buscarPorNombre(e.getKey())))
				.collect(Collectors.toList());
		
		//Enviamos al formulario un objeto de tipo linea, donde vamos a recoger el id del compuesto(campo oculto)
		// y la cantidad

		for (LineaPedido l : listaLineasPedido) {
			precioProductos += l.getPrecio();
		}

		if (precioProductos != num0) {
			Pedido pedido = new Pedido(precioProductos, precioTrasporte, (precioProductos + precioTrasporte),
					fechaLlegada, fechaSalidaTrans, cliente);

			for (LineaPedido l : listaLineasPedido) {
				pedido.addLinea(l);
			}

			this.save(pedido);

			return pedido;
		} else {
			return null;
		}

	}
}
