package ar.com.caece.ids2.barapp.facturacion.models;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;

import java.util.*;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Mesa {
    private static final List<Mesa> mesas = Collections.synchronizedList(new ArrayList<>());

    private String name;
    private Integer code;
    private List<Pedido> pedidos;
    public enum State {
        OPEN, CLOSED
    }
    private State state;

    private Mesa(String name) {
        this.name = name;
        this.pedidos = Collections.synchronizedList(new ArrayList<Pedido>());
    }

    public static Mesa getMesa(Integer code) {
        return mesas.get(code);
    }

    public static List<Mesa> getMesas() {
        return new ArrayList<>(mesas);
    }

    public static Mesa createMesa(String name) throws DuplicateTableException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        Optional<Mesa> m = mesas.stream().filter(mesa1 -> mesa1.getName() == name).findAny();
        if (m.isPresent()) {
            throw new DuplicateTableException("Table with name already present");
        }
        Mesa mesa = new Mesa(name);
        mesas.add(mesa);
        mesa.code = mesas.indexOf(mesa);
        return mesa;
    }

    public static void destroyMesa(Integer code) {
        mesas.remove(code);
        Optional<Mesa> m = mesas.stream().filter(mesa -> Objects.equals(mesa.getCode(), code)).findFirst();
        if (m.isPresent()) {
            mesas.remove(m.get());
        }
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

    public void open() {
        state = State.OPEN;
    }

    public List<Cuenta> close() {
        state = State.CLOSED;
        return null;
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
