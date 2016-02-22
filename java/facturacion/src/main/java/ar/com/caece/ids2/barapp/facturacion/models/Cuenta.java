package ar.com.caece.ids2.barapp.facturacion.models;

import ar.com.caece.ids2.barapp.facturacion.services.MenuService;
import ar.com.caece.ids2.barapp.facturacion.services.MenuServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Cuenta {
    public MenuService menuService = new MenuServiceImpl();
    List<DetallePlato> detallePlatos = new ArrayList<>();
    List<DetalleBebida> detalleBebidas = new ArrayList<>();
    Long total = 0L;

    public Cuenta(List<DetallePlato> detallePlatos, List<DetalleBebida> detalleBebidas, Long total) {
        this.detallePlatos = detallePlatos;
        this.detalleBebidas = detalleBebidas;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public List<DetallePlato> getDetallePlatos() {
        return detallePlatos;
    }

    public List<DetalleBebida> getDetalleBebidas() {
        return detalleBebidas;
    }

    public void setDetallePlatos(List<DetallePlato> detallePlatos) {
        this.detallePlatos = detallePlatos;
    }

    public void setDetalleBebidas(List<DetalleBebida> detalleBebidas) {
        this.detalleBebidas = detalleBebidas;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
