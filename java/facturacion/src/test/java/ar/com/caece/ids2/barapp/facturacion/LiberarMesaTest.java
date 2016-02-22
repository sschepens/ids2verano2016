package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Before;
import org.junit.Test;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.models.Mesa;

import static org.mockito.Mockito.*;

public class LiberarMesaTest {
	private MesaService mesaService = mock(MesaServiceImpl.class);
	private PedidoService pedidoService = mock(PedidoServiceImpl.class);
	private MenuService menuService = mock(MenuServiceImpl.class);
	private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

	@Before
	public void clean() {
		reset(mesaService);
		reset(pedidoService);
	}

	@Test
	public void liberarMesaClosesMesa() throws TableNotFoundException, TableNotOccupiedException {
		Mesa m = mock(Mesa.class);
		when(m.getCode()).thenReturn(0);

		when(mesaService.getMesa(m.getCode())).thenReturn(m);

		facturador.liberarMesa(m.getCode());
		verify(mesaService, times(1)).getMesa(m.getCode());
		verify(m, times(1)).close();
	}

	@Test(expected = TableNotFoundException.class)
	public void ocupaMesaNotFound() throws TableNotFoundException, TableNotOccupiedException {
		Mesa m = mock(Mesa.class);
		when(m.getCode()).thenReturn(0);

		when(mesaService.getMesa(m.getCode())).thenThrow(TableNotFoundException.class);

		facturador.liberarMesa(m.getCode());
	}

	@Test(expected = TableNotOccupiedException.class)
	public void ocupaMesaAlreadyOpen() throws TableNotFoundException, TableNotOccupiedException {
		Mesa m = mock(Mesa.class);
		when(m.getCode()).thenReturn(0);
		doThrow(TableNotOccupiedException.class).when(m).close();

		when(mesaService.getMesa(m.getCode())).thenReturn(m);

		facturador.liberarMesa(m.getCode());
	}
}
