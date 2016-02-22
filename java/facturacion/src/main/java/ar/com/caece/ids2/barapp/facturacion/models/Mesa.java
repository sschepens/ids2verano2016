package ar.com.caece.ids2.barapp.facturacion.models;

import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Mesa {

    private String name;
    private Integer code;
    private List<Pedido> pedidos;
    public enum State {
        OPEN, CLOSED
    }
    private State state = State.CLOSED;

    public Mesa(String name) {
        this.name = name;
        this.pedidos = Collections.synchronizedList(new ArrayList<Pedido>());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return state == State.OPEN;
    }

    public boolean isClosed() {
        return !isOpen();
    }

    public void open() throws TableAlreadyOccupiedException {
        if (state == State.OPEN) {
            throw new TableAlreadyOccupiedException();
        }
        state = State.OPEN;
    }

    public void close() throws TableNotOccupiedException {
        if (state == State.CLOSED) {
            throw new TableNotOccupiedException("Table is not occupied");
        }
        state = State.CLOSED;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void removePedido(Integer pedido) {
        Optional<Pedido> p = pedidos.stream().filter(ped -> ped.getCode() == pedido).findFirst();
        if (p.isPresent()) {
            pedidos.remove(p.get());
        }
    }

    public void removePedido(Pedido p) {
        pedidos.remove(p);
    }

    public State getState() {
        return state;
    }
}
