import static org.junit.Assert.*;

import org.junit.Test;

public class CocinaTest {

	@Test
	public void testCancelarPedidoFinalizado() {
		fail("No se puede cancelar el pedido finalizado.");
	}
	
	@Test
	public void testCancelarPedido() {
		fail("No se pudo cancelar el pedido");
	}
	
	@Test
	public void testModificarPedidoConEstadoInexistente() {
		fail("No existe el estado.");
	}
	
	@Test
	public void testModificarPedidoAEstadoEnPreparacion() {
		fail("No se pudo modificar el estado.");
	}
	
	@Test
	public void testFinalizarPedidoPendiente() {
		fail("No se puede finalizar el pedido.");
	}
	
	public void testPedidoPreparacionAPendiente() {
		fail("No se puede pasar a pendiente.");
	}
	
	public void testRecibirPedidoNoPendiente() {
		fail("No se puede recibir distinto de pendiente.");
	}
	
}
