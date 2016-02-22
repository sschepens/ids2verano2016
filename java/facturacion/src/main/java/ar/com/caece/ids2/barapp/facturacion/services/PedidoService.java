package ar.com.caece.ids2.barapp.facturacion.services;

import java.util.List;

import ar.com.caece.ids2.barapp.facturacion.models.DetalleBebida;
import ar.com.caece.ids2.barapp.facturacion.models.DetallePlato;
import ar.com.caece.ids2.barapp.facturacion.models.Pedido;

public interface PedidoService {

	public Long getCuenta(List<Pedido> pedidos);

    public Long getTotal();

    public List<DetallePlato> getDetallePlatos();

    public List<DetalleBebida> getDetalleBebidas();
	
}
