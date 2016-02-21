package ar.com.caece.ids2.barapp.cocina.impl;

import ar.com.caece.ids2.barapp.cocina.CocinaMesaInterface;
import ar.com.caece.ids2.barapp.cocina.dao.PedidoDao;
import ar.com.caece.ids2.barapp.cocina.model.Pedido;
import ar.com.caece.ids2.barapp.cocina.util.CocinaEstados;

public class CocinaMesaImpl implements CocinaMesaInterface {

	private PedidoDao pedidoDao;
	
	@Override
	public boolean recibirPedido(Pedido pedido) {
		
		if (pedido.getEstadoMesa() == CocinaEstados.PENDIENTE 
				&& pedido.getListaPlatos().size() > 0) {
			pedidoDao.save(pedido);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarPedido(Pedido pedido) {
		Pedido pedidoAux = pedidoDao.get(pedido.getId());
		
		if (pedidoAux != null && pedido.getEstadoMesa() >= CocinaEstados.PENDIENTE 
				&& pedido.getEstadoMesa() <= CocinaEstados.TERMINADO) {
			pedidoDao.update(pedido);
			return true;
		}
		return false;
	}

	@Override
	public boolean cancelarPedido(int idPedido) {
		Pedido pedido = pedidoDao.get(idPedido);
		
		if (pedido == null && pedido.getEstadoMesa() == CocinaEstados.TERMINADO) {
			return false;
		}
		pedido.setEstadoMesa(CocinaEstados.CANCELADO);
		pedidoDao.update(pedido);
		return true;
	}
	
	@Override
	public boolean iniciarPedido(int idPedido) {
		Pedido pedido = pedidoDao.get(idPedido);
		
		if (pedido == null && pedido.getEstadoMesa() != CocinaEstados.PENDIENTE) {
			return false;
		}
		pedido.setEstadoMesa(CocinaEstados.EN_PREPARACION);
		pedidoDao.update(pedido);
		return true;
	}
	
	public boolean terminarPedido(int idPedido) {
		Pedido pedido = pedidoDao.get(idPedido);
		
		if (pedido == null && pedido.getEstadoMesa() != CocinaEstados.EN_PREPARACION) {
			return false;
		}
		pedido.setEstadoMesa(CocinaEstados.EN_PREPARACION);
		pedidoDao.update(pedido);
		return true;
	}

}
