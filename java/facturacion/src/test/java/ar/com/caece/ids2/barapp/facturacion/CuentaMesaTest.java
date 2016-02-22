package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import ar.com.caece.ids2.barapp.facturacion.services.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CuentaMesaTest {
    private MesaService mesaService = mock(MesaServiceImpl.class);
    private PedidoService pedidoService = mock(PedidoServiceImpl.class);
    private MenuService menuService = mock(MenuServiceImpl.class);
    private Facturador facturador = new FacturadorImpl(mesaService, pedidoService, menuService);

    @Before
    public void clean() {
        reset(mesaService);
        reset(pedidoService);
    }

    // cuentaMesa
    @Test()
    public void testCuentaMesa() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCode()).thenReturn(0);
        when(m.getPedidos()).thenReturn(new ArrayList<Pedido>());
        when(m.isClosed()).thenReturn(false);

        when(mesaService.getMesa(m.getCode())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCode());
        Assert.assertNotNull(cuenta);
        verify(mesaService, times(1)).getMesa(m.getCode());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
    }

    @Test(expected = TableNotOccupiedException.class)
    public void testCuentaFailsOnClosedTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCode()).thenReturn(0);
        when(m.isClosed()).thenReturn(true);

        when(mesaService.getMesa(m.getCode())).thenReturn(m);

        facturador.cuentaMesa(m.getCode());
    }

    @Test(expected = TableNotFoundException.class)
    public void testCuentaFailsOnInexistantTable() throws Exception {
        Mesa m = mock(Mesa.class);
        when(m.getCode()).thenReturn(0);

        when(mesaService.getMesa(m.getCode())).thenThrow(TableNotFoundException.class);

        facturador.cuentaMesa(m.getCode());
    }

    @Test()
    public void testCuentaReturnsPedidosAndTotal() throws Exception {
        DetallePlato detallePlato = mock(DetallePlato.class);
        when(detallePlato.getCode()).thenReturn(1);
        when(detallePlato.getCantidad()).thenReturn(1);
        Mesa m = mock(Mesa.class);
        when(m.getCode()).thenReturn(0);
        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido(m.getCode());
        pedido.withPlato(detallePlato);
        pedidos.add(pedido);
        when(m.getPedidos()).thenReturn(pedidos);
        when(m.isClosed()).thenReturn(false);

        when(menuService.obtenerValorPlato(detallePlato.getCode())).thenReturn(100L);
        when(mesaService.getMesa(m.getCode())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCode());
        Assert.assertEquals(cuenta.getDetallePlatos().size(), 1);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), detallePlato);
        Long total = menuService.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);

        verify(mesaService, times(1)).getMesa(m.getCode());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
    }

    @Test()
    public void testCuentaReturnsMultiplePedidosWithMultipleItems() throws Exception {
        DetallePlato detallePlato1 = mock(DetallePlato.class);
        when(detallePlato1.getCode()).thenReturn(1);
        when(detallePlato1.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorPlato(detallePlato1.getCode())).thenReturn(100L);
        DetallePlato detallePlato2 = mock(DetallePlato.class);
        when(detallePlato2.getCode()).thenReturn(2);
        when(detallePlato2.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorPlato(detallePlato2.getCode())).thenReturn(200L);
        DetalleBebida detalleBebida1 = mock(DetalleBebida.class);
        when(detalleBebida1.getCode()).thenReturn(3);
        when(detalleBebida1.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorBebida(detalleBebida1.getCode())).thenReturn(300L);
        DetalleBebida detalleBebida2 = mock(DetalleBebida.class);
        when(detalleBebida2.getCode()).thenReturn(4);
        when(detalleBebida2.getCantidad()).thenReturn(1);
        when(menuService.obtenerValorBebida(detalleBebida2.getCode())).thenReturn(400L);

        Mesa m = mock(Mesa.class);
        when(m.getCode()).thenReturn(0);

        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido1 = mock(Pedido.class);
        List<DetallePlato> detallePlatos1 = new ArrayList<>();
        detallePlatos1.add(detallePlato1);
        when(pedido1.getDetallePlatos()).thenReturn(detallePlatos1);
        List<DetalleBebida> detalleBebidas1 = new ArrayList<>();
        detalleBebidas1.add(detalleBebida1);
        when(pedido1.getDetalleBebidas()).thenReturn(detalleBebidas1);
        pedidos.add(pedido1);

        Pedido pedido2 = mock(Pedido.class);
        List<DetallePlato> detallePlatos2 = new ArrayList<>();
        detallePlatos2.add(detallePlato2);
        when(pedido2.getDetallePlatos()).thenReturn(detallePlatos2);
        List<DetalleBebida> detalleBebidas2 = new ArrayList<>();
        detalleBebidas2.add(detalleBebida2);
        when(pedido2.getDetalleBebidas()).thenReturn(detalleBebidas2);
        pedidos.add(pedido2);

        when(m.getPedidos()).thenReturn(pedidos);
        when(m.isClosed()).thenReturn(false);

        when(mesaService.getMesa(m.getCode())).thenReturn(m);

        Cuenta cuenta = facturador.cuentaMesa(m.getCode());

        Assert.assertEquals(cuenta.getDetallePlatos().size(), 2);
        Assert.assertEquals(cuenta.getDetalleBebidas().size(), 2);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), pedido1.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(0), pedido1.getDetalleBebidas().get(0));
        Assert.assertEquals(cuenta.getDetallePlatos().get(1), pedido2.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(1), pedido2.getDetalleBebidas().get(0));
        Assert.assertEquals(cuenta.getTotal().longValue(), 1000L);

        verify(mesaService, times(1)).getMesa(m.getCode());
        verify(m, times(1)).isClosed();
        verify(m, times(1)).getPedidos();
        verify(menuService, times(1)).obtenerValorBebida(detalleBebida1.getCode());
        verify(menuService, times(1)).obtenerValorBebida(detalleBebida2.getCode());
        verify(menuService, times(1)).obtenerValorPlato(detallePlato1.getCode());
        verify(menuService, times(1)).obtenerValorPlato(detallePlato2.getCode());
    }
}
