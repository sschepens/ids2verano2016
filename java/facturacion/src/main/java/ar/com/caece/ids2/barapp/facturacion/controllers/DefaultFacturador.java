package ar.com.caece.ids2.barapp.facturacion.controllers;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class DefaultFacturador implements Facturador {
    @Override
    public void procesarPedido(Integer mesa, Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException {
        Mesa m = Mesa.getMesa(mesa);
        if (m.getState() == Mesa.State.CLOSED) {
            throw new TableNotOccupiedException("Table is closed");
        }
        Optional<Pedido> op = m.getPedidos().stream().filter(p -> p.getCode() == pedido.getCode()).findFirst();
        if (op.isPresent()) {
            m.removePedido(pedido.getCode());
        }
        if (pedido.getState() != Pedido.STATE.CANCELADO) {
            m.addPedido(pedido);
        }
    }

    @Override
    public Cuenta cuentaMesa(Integer mesa) throws TableNotFoundException {
        return null;
    }

    @Override
    public List<Factura> facturarMesa(Integer code, long limit, float tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException {
        return null;
    }

    @Override
    public void ocuparMesa(Integer code) throws TableNotFoundException {

    }

    @Override
    public void liberarMesa(Integer code) throws TableNotFoundException {

    }

    @Override
    public Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException {
        return null;
    }

    @Override
    public long obtenerSaldo(Integer code) throws TableNotFoundException {
        return new Cuenta(Mesa.getMesa(code).getPedidos()).getTotal();
    }

    @Override
    public List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException {
        return Mesa.getMesa(code).getPedidos();
    }

    @Override
    public List<Mesa> obtenerMesas() throws TableNotFoundException {
        return Mesa.getMesas();
    }

    @Override
    public Mesa crearMesa(String name) throws DuplicateTableException, IllegalArgumentException {
        return Mesa.createMesa(name);
    }

    @Override
    public Mesa modificarMesa(Integer code, String newName) throws TableNotFoundException, DuplicateTableException {
        return null;
    }

    @Override
    public void quitarMesa(Integer code) throws TableNotFoundException {
        Mesa.destroyMesa(code);
    }
}
