package cocina.dao;

import cocina.model.Pedido;

//TODO: configurar hibernate
public interface PedidoDao {
	
	public void save(Pedido pedido);
	
	public void update(Pedido pedido);
	
	public void delete(int idPedido);
	
	public Pedido get(int id);

}
