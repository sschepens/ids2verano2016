package ar.com.caece.ids2.barapp.facturacion.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
public class Factura {
    Map<Integer, DetallePlato> detallePlatos = new HashMap<>();
    Map<Integer, DetalleBebida> detalleBebida = new HashMap<>();
    Long tip = 0L;
    Long total = 0L;
    MedioDePago medioDePago;

    public Factura(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTip() {
        return tip;
    }

    public void setTip(Long tip) {
        this.tip = tip;
    }

    public void addPlato(DetallePlato detallePlato) {
        DetallePlato d = this.detallePlatos.get(detallePlato.getCode());
        if (d != null) {
            d.setCantidad(d.getCantidad() + detallePlato.getCantidad());
        } else {
            this.detallePlatos.put(detallePlato.getCode(), detallePlato);
        }
    }

    public void addBebida(DetalleBebida detalleBebida) {
        DetalleBebida d = this.detalleBebida.get(detalleBebida.getCode());
        if (d != null) {
            d.setCantidad(d.getCantidad() + detalleBebida.getCantidad());
        } else {
            this.detalleBebida.put(detalleBebida.getCode(), detalleBebida);
        }
    }
}
