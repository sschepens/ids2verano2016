package ar.com.caece.ids2.barapp.cocina.dao;

import ar.com.caece.ids2.barapp.cocina.model.Pedido;

//TODO: configurar hibernate
public interface PedidoDao {
	
	public void save(Pedido pedido);
	
	public void update(Pedido pedido);
	
	public void delete(int idPedido);
	
	public Pedido get(int id);

}
