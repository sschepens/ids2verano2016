package ar.com.caece.ids2.barapp.facturacion.controllers;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;

import java.util.List;

/**
 * Created by Sebastian Schepens on 16/2/2016.
 */
public interface Facturador {
    /**
     * Agrega, modifica o quita un pedido de una mesa de acuerdo al estado del pedido.
     *
     * @param mesa codigo identificador de la mesa.
     * @param pedido el pedido que debe ser procesado para la mesa.
     * Lanza {@link TableNotFoundException} si la mesa no existe.
     * Lanza {@link IllegalArgumentException} si el {@link Pedido} es invalido.
     */
    public void procesarPedido(Integer mesa, Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException;

    /**
     * Returna una {@link Cuenta} para la mesa data.
     *
     * @param mesa codigo identificador de la mesa.
     * @return {@link Cuenta} para la mesa dada, con items y total.
     * Lanza {@link TableNotFoundException} si la mesa no existe.
     */
    public Cuenta cuentaMesa(Integer mesa) throws TableNotFoundException;

    public List<Factura> facturarMesa(Integer code, long limit, float tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException;

    public void ocuparMesa(Integer code) throws TableNotFoundException;

    public void liberarMesa(Integer code) throws TableNotFoundException;

    public Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException;

    public long obtenerSaldo(Integer code) throws TableNotFoundException;

    public List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException;

    public List<Mesa> obtenerMesas() throws TableNotFoundException;

    public Mesa crearMesa(String name) throws DuplicateTableException, IllegalArgumentException;

    public Mesa modificarMesa(Integer code, String newName) throws TableNotFoundException, DuplicateTableException;

    public void quitarMesa(Integer code) throws TableNotFoundException;
}
