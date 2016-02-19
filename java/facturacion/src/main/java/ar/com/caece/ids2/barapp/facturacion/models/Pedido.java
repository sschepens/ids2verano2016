package ar.com.caece.ids2.barapp.facturacion.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Pedido {
    private Integer code;
    private Integer mesaCode;
    private List<DetallePlato> detallePlatos;
    private List<DetalleBebida> detalleBebidas;
    public enum STATE {
        PENDIENTE,
        COMPLETO,
        CANCELADO
    }
    private STATE state = STATE.PENDIENTE;

    public Pedido(int mesa) {
        this.mesaCode = mesa;
        this.detallePlatos = new ArrayList<>();
        this.detalleBebidas = new ArrayList<>();
    }

    public Pedido withPlato(DetallePlato detallePlato) {
        this.detallePlatos.add(detallePlato);
        return this;
    }

    public Pedido withPlatos(List<DetallePlato> detallePlatos) {
        this.detallePlatos.addAll(detallePlatos);
        return this;
    }

    public Pedido withBebida(DetalleBebida detalleBebida) {
        this.detalleBebidas.add(detalleBebida);
        return this;
    }

    public Pedido withBebidas(List<DetalleBebida> detalleBebidas) {
        this.detalleBebidas.addAll(detalleBebidas);
        return this;
    }

    public List<DetallePlato> getDetallePlatos() {
        return detallePlatos;
    }

    public List<DetalleBebida> getDetalleBebidas() {
        return detalleBebidas;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getMesaCode() {
        return mesaCode;
    }

    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }
}
