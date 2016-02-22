package ar.com.caece.ids2.barapp.facturacion.services;

import java.util.ArrayList;
import java.util.List;

import ar.com.caece.ids2.barapp.facturacion.models.DetalleBebida;
import ar.com.caece.ids2.barapp.facturacion.models.DetallePlato;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;

public class PedidoServiceImpl implements PedidoService {

	private MenuService menuService = new MenuServiceImpl();
	
	private List<DetallePlato> detallePlatos = new ArrayList<>();
	private List<DetalleBebida> detalleBebidas = new ArrayList<>();
	private Long total = 0L;

	@Override
	public Long getCuenta(List<Pedido> pedidos) {
		for (Pedido p : pedidos) {
			for (DetallePlato detallePlato : p.getDetallePlatos()) {
				this.detallePlatos.add(detallePlato);
				this.total += (menuService.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad());
			}
			for (DetalleBebida detalleBebida : p.getDetalleBebidas()) {
				this.detalleBebidas.add(detalleBebida);
				this.total += (menuService.obtenerValorBebida(detalleBebida.getCode()) * detalleBebida.getCantidad());
			}
		}
		return total;
	}

	@Override
	public Long getTotal() {
		return total;
	}

	@Override
	public List<DetallePlato> getDetallePlatos() {
		return detallePlatos;
	}

	@Override
	public List<DetalleBebida> getDetalleBebidas() {
		return detalleBebidas;
	}

}
