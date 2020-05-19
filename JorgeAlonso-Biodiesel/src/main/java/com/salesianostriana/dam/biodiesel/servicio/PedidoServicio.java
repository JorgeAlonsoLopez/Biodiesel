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
import com.salesianostriana.dam.biodiesel.modelo.formulario.PedidoFormulario;
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
	
	public List<Pedido> findListByDni(String dni) {
		return this.repositorio.findListByDni(dni);
	}
	

	public Pedido hacerPedido(Administrador admin, PedidoFormulario pedidoForm, Cliente cliente,
			CompuestoServicio compuestoServicio) {
		double precioProductos = 0;
		int num0 = 0, num4 = 4, num5 = 5, num7 = 7, num8 = 8, num10 = 10;
		int dia, mes, anyo;
		LocalDate fecha, fechaSalidaTrans;

		if (pedidoForm.getFecha()==null) {
			fecha = pedidoForm.getFechaLoc();
		} else {
			anyo = Integer.parseInt(pedidoForm.getFecha().substring(num0, num4));
			mes = Integer.parseInt(pedidoForm.getFecha().substring(num5, num7));
			dia = Integer.parseInt(pedidoForm.getFecha().substring(num8, num10));

			fecha = LocalDate.of(anyo, mes, dia);
		}

		int numDias = cliente.getPais().getNumDiasBarco() + cliente.getPais().getNumDiasTren();
		fechaSalidaTrans = fecha.minusDays(numDias);
		double precioTrasporte = (admin.getPrecioBarco() * cliente.getPais().getNumDiasBarco())
				+ (admin.getPrecioTren() * cliente.getPais().getNumDiasTren());

		LineaPedido linea = new LineaPedido(
				compuestoServicio.buscarPorNombre(pedidoForm.getNombre()).getPrecio() * pedidoForm.getCantidad(),
				pedidoForm.getCantidad(), compuestoServicio.buscarPorNombre(pedidoForm.getNombre()));

		precioProductos = linea.getPrecio();

		if (precioProductos != num0) {
			Pedido pedido = new Pedido(precioProductos, precioTrasporte, (precioProductos + precioTrasporte), fecha,
					fechaSalidaTrans, cliente);
			pedido.addLinea(linea);
			this.save(pedido);
			return pedido;
		} else {
			return null;
		}

	}
}
