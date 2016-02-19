package ar.com.caece.ids2.barapp.facturacion.controllers;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sebastian Schepens on 17/2/2016.
 */
public class DefaultFacturadorTest {
    private static Facturador facturador = new DefaultFacturador();

    @After
    public void clean() {
        for(Mesa m : Mesa.getMesas()) {
            System.out.println(m);
            Mesa.destroyMesa(m.getCode());
        }
    }

    // crearMesa
    @Test
    public void testCrearMesaAssignsId() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        Assert.assertNotNull(paulaner);
        Assert.assertEquals(paulaner.getCode(), new Integer(0));
        Mesa warsteiner = facturador.crearMesa("Warsteiner");
        Assert.assertNotNull(warsteiner);
        Assert.assertEquals(warsteiner.getCode(), new Integer(1));
    }

    @Test
    public void testCrearMesaAssignsName() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        Assert.assertNotNull(paulaner);
        Assert.assertEquals(paulaner.getName(), "Paulaner");
        Assert.assertEquals(paulaner.getCode(), new Integer(0));
    }

    @Test
    public void testCrearMesaInitializesPedidosList() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        Assert.assertNotNull(paulaner);
        Assert.assertNotNull(paulaner.getPedidos());
        Assert.assertEquals(paulaner.getPedidos().size(), 0);
    }

    @Test(expected = DuplicateTableException.class)
    public void testCrearMesaFailsOnDuplicate() throws Exception {
        facturador.crearMesa("Paulaner");
        facturador.crearMesa("Paulaner");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearMesaFailsNullName() throws Exception {
        facturador.crearMesa(null);
    }

    @Test
    public void testCrearMesaSaves() throws Exception {
        Assert.assertEquals(facturador.obtenerMesas().size(), 0);
        Mesa mesa = facturador.crearMesa("Paulaner");
        Assert.assertEquals(facturador.obtenerMesas().size(), 1);
        Assert.assertEquals(facturador.obtenerMesas().get(0), mesa);
    }

    // procesarPedido
    @Test(expected = TableNotOccupiedException.class)
    public void testProcesarPedidoFailsIfTableNotOcuppied() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        Pedido pedido = new Pedido(paulaner.getCode());
        facturador.liberarMesa(paulaner.getCode());
        facturador.procesarPedido(paulaner.getCode(), pedido);
    }

    @Test()
    public void testProcesarPedidoAddsPedidoToMesa() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.ocuparMesa(paulaner.getCode());
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.withPlato(detallePlato);
        pedido.setState(Pedido.STATE.PENDIENTE);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 0);
        facturador.procesarPedido(paulaner.getCode(), pedido);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 1);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).get(0), pedido);
    }

    @Test()
    public void testProcesarPedidoModifiesExistantPedido() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.ocuparMesa(paulaner.getCode());

        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.setCode(0);
        pedido.withPlato(detallePlato);
        pedido.setState(Pedido.STATE.PENDIENTE);

        facturador.procesarPedido(paulaner.getCode(), pedido);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 1);

        Pedido pedidoModificado = new Pedido(paulaner.getCode());
        pedidoModificado.setCode(0);
        DetallePlato detallePlato2 = new DetallePlato(2, 2);
        pedidoModificado.withPlato(detallePlato);
        pedidoModificado.withPlato(detallePlato2);
        pedidoModificado.setState(Pedido.STATE.PENDIENTE);

        facturador.procesarPedido(paulaner.getCode(), pedidoModificado);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 1);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).get(0), pedidoModificado);
    }

    @Test()
    public void testProcesarPedidoRemovesPedidoWhenCanceled() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.ocuparMesa(paulaner.getCode());
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.setCode(0);
        pedido.withPlato(detallePlato);
        pedido.setState(Pedido.STATE.PENDIENTE);
        facturador.procesarPedido(paulaner.getCode(), pedido);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 1);
        Pedido pedidoModificado = new Pedido(paulaner.getCode());
        pedidoModificado.setCode(0);
        pedidoModificado.withPlato(detallePlato);
        pedidoModificado.setState(Pedido.STATE.CANCELADO);
        facturador.procesarPedido(paulaner.getCode(), pedidoModificado);
        Assert.assertEquals(facturador.obtenerPedidos(paulaner.getCode()).size(), 0);
    }

    // cuentaMesa
    @Test()
    public void testCuentaMesa() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        facturador.ocuparMesa(paulaner.getCode());
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertNotNull(cuenta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCuentaFailsOnClosedTable() throws Exception {
        Mesa warsteiner = facturador.crearMesa("Warsteiner");
        facturador.cuentaMesa(warsteiner.getCode());
    }

    @Test(expected = TableNotFoundException.class)
    public void testCuentaFailsOnInexistantTable() throws Exception {
        facturador.cuentaMesa(Integer.MAX_VALUE);
    }

    @Test()
    public void testCuentaReturnsPedidosAndTotal() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.withPlato(detallePlato);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.procesarPedido(paulaner.getCode(), pedido);
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertEquals(cuenta.getDetallePlatos().size(), 1);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), detallePlato);
        Long total = Menu.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);
    }

    @Test()
    public void testCuentaReturnsMultiplePedidosWithMultipleItems() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato1 = new DetallePlato(1, 1);
        DetallePlato detallePlato2 = new DetallePlato(2, 1);
        DetalleBebida detalleBebida1 = new DetalleBebida(3, 1);
        DetalleBebida detalleBebida2 = new DetalleBebida(4, 1);
        Pedido pedido1 = new Pedido(paulaner.getCode());
        pedido1.withPlato(detallePlato1);
        pedido1.withBebida(detalleBebida1);
        Pedido pedido2 = new Pedido(paulaner.getCode());
        pedido2.withPlato(detallePlato2);
        pedido2.withBebida(detalleBebida2);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.procesarPedido(paulaner.getCode(), pedido1);
        facturador.procesarPedido(paulaner.getCode(), pedido2);
        Cuenta cuenta = facturador.cuentaMesa(paulaner.getCode());
        Assert.assertEquals(cuenta.getDetallePlatos().size(), 2);
        Assert.assertEquals(cuenta.getDetalleBebidas().size(), 2);
        Assert.assertEquals(cuenta.getDetallePlatos().get(0), pedido1.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(0), pedido1.getDetalleBebidas().get(0));
        Assert.assertEquals(cuenta.getDetallePlatos().get(1), pedido2.getDetallePlatos().get(0));
        Assert.assertEquals(cuenta.getDetalleBebidas().get(1), pedido2.getDetalleBebidas().get(0));
        Long total = Menu.obtenerValorPlato(detallePlato1.getCode()) * detallePlato1.getCantidad() +
                Menu.obtenerValorPlato(detallePlato2.getCode()) * detallePlato2.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida1.getCode()) * detalleBebida1.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida2.getCode()) * detalleBebida2.getCantidad();
        Assert.assertEquals(cuenta.getTotal(), total);
    }

    // obtenerSaldo
    @Test(expected = TableNotFoundException.class)
    public void testObtenerSaldoFailsOnTableNotFound() throws Exception {
        Long totalMesa = facturador.obtenerSaldo(Integer.MAX_VALUE);
    }

    @Test()
    public void testObtenerSaldoReturnsTotal() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato = new DetallePlato(1, 1);
        Pedido pedido = new Pedido(paulaner.getCode());
        pedido.withPlato(detallePlato);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.procesarPedido(paulaner.getCode(), pedido);
        Long totalMesa = facturador.obtenerSaldo(paulaner.getCode());
        Long total = Menu.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad();
        Assert.assertEquals(totalMesa, total);
    }

    @Test()
    public void testObtenerSaldoReturnsTotalWithMultiplePedidosAndItems() throws Exception {
        Mesa paulaner = facturador.crearMesa("Paulaner");
        DetallePlato detallePlato1 = new DetallePlato(1, 1);
        DetallePlato detallePlato2 = new DetallePlato(2, 1);
        DetalleBebida detalleBebida1 = new DetalleBebida(3, 1);
        DetalleBebida detalleBebida2 = new DetalleBebida(4, 1);
        Pedido pedido1 = new Pedido(paulaner.getCode());
        pedido1.withPlato(detallePlato1);
        pedido1.withBebida(detalleBebida1);
        Pedido pedido2 = new Pedido(paulaner.getCode());
        pedido2.withPlato(detallePlato2);
        pedido2.withBebida(detalleBebida2);
        facturador.ocuparMesa(paulaner.getCode());
        facturador.procesarPedido(paulaner.getCode(), pedido1);
        facturador.procesarPedido(paulaner.getCode(), pedido2);
        Long totalMesa = facturador.obtenerSaldo(paulaner.getCode());
        Long total = Menu.obtenerValorPlato(detallePlato1.getCode()) * detallePlato1.getCantidad() +
                Menu.obtenerValorPlato(detallePlato2.getCode()) * detallePlato2.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida1.getCode()) * detalleBebida1.getCantidad() +
                Menu.obtenerValorBebida(detalleBebida2.getCode()) * detalleBebida2.getCantidad();
        Assert.assertEquals(totalMesa, total);
    }

    @Test(expected = TableNotFoundException.class)
    public void testFacturaFailsOnInexistantTable() throws Exception {
        facturador.cuentaMesa(Integer.MAX_VALUE);
    }
}
