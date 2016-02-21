package ar.com.caece.ids2.barapp.cocina;

import ar.com.caece.ids2.barapp.cocina.model.Pedido;

/**
 * Interface presentada al modulo de toma de pedidos
 * con las acciones correspondientes al manejo de pedidos.
 *
 */
public interface CocinaMesaInterface {

	/**
	 * Metodo que recibe el pedido con los detalles de la mesa.
	 * El pedido ingresa con estado pendiente.
	 * 
	 * @param pedido: datos de la mesa
	 */
	public boolean recibirPedido(Pedido pedido);
	
	/**
	 * Metodo que recibe un pedido a modificar.
	 * 
	 * @param pedido: pedido que sera modificado
	 */
	public boolean modificarPedido(Pedido pedido);
	
	/**
	 * Metodo que cancela un pedido a partir del id.
	 * El pedido quedara en estado cancelado.
	 * 
	 * @param idPedido: id que identifica el pedido
	 */
	public boolean cancelarPedido(int idPedido);

	public boolean iniciarPedido(int idPedido);
	
	public boolean terminarPedido(int idPedido);
	
}
