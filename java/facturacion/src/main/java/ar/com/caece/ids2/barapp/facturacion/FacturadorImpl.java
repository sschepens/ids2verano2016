package ar.com.caece.ids2.barapp.facturacion;

import ar.com.caece.ids2.barapp.facturacion.exceptions.DuplicateTableException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableAlreadyOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotFoundException;
import ar.com.caece.ids2.barapp.facturacion.exceptions.TableNotOccupiedException;
import ar.com.caece.ids2.barapp.facturacion.models.*;
import ar.com.caece.ids2.barapp.facturacion.services.MenuService;
import ar.com.caece.ids2.barapp.facturacion.services.MesaService;
import ar.com.caece.ids2.barapp.facturacion.services.PedidoService;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sebastian Schepens on 15/2/2016.
 */
@RestController
public class FacturadorImpl implements Facturador {
	private MesaService mesaService;
	private PedidoService pedidoService;
    private MenuService menuService;

	public FacturadorImpl(MesaService mesaService, PedidoService pedidoService, MenuService menuService){
		this.mesaService = mesaService;
		this.pedidoService = pedidoService;
        this.menuService = menuService;
	}

    @Override
    @RequestMapping("/procesarPedido")
    public void procesarPedido(@RequestParam(value="mesa") Integer mesa, @RequestBody Pedido pedido) throws TableNotFoundException, TableNotOccupiedException, IllegalArgumentException {
        Mesa m = mesaService.getMesa(mesa);
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
    @RequestMapping("/cuentaMesa")
    public @ResponseBody Cuenta cuentaMesa(@RequestParam(value="mesa") Integer mesa) throws TableNotFoundException, TableNotOccupiedException {
        Mesa m = mesaService.getMesa(mesa);
        if (m.isClosed()) {
            throw new TableNotOccupiedException("Table is not occupied");
        }
        List<DetallePlato> detallePlatos = new ArrayList<>();
        List<DetalleBebida> detalleBebidas = new ArrayList<>();
        Long total = 0L;
        for (Pedido p : m.getPedidos()) {
            for (DetallePlato detallePlato : p.getDetallePlatos()) {
                detallePlatos.add(detallePlato);
                total += (menuService.obtenerValorPlato(detallePlato.getCode()) * detallePlato.getCantidad());
            }
            for (DetalleBebida detalleBebida : p.getDetalleBebidas()) {
                detalleBebidas.add(detalleBebida);
                total += (menuService.obtenerValorBebida(detalleBebida.getCode()) * detalleBebida.getCantidad());
            }
        }
        return new Cuenta(detallePlatos, detalleBebidas, total);
    }

    @Override
    @RequestMapping("/facturarMesa")
    public @ResponseBody List<Factura> facturarMesa(Integer code, Long limit, Long tip, MedioDePago medio) throws TableNotFoundException, IllegalArgumentException {
        Mesa m = mesaService.getMesa(code);
        List<Factura> facturas = new ArrayList<>();
        Factura f = new Factura(medio);
        for (Pedido p : m.getPedidos()) {
            for (DetallePlato detallePlato : p.getDetallePlatos()) {
                Long valor = menuService.obtenerValorPlato(detallePlato.getCode());
                for (int i = 0; i < detallePlato.getCantidad(); i++) {
                    if (f.getTotal() + valor > limit) {
                        facturas.add(f);
                        f = new Factura(medio);
                    }
                    f.addPlato(new DetallePlato(detallePlato.getCode(), 1));
                    f.setTotal(f.getTotal() + valor);
                }
            }
            for (DetalleBebida detalleBebida : p.getDetalleBebidas()) {
                Long valor = menuService.obtenerValorBebida(detalleBebida.getCode());
                for (int i = 0; i < detalleBebida.getCantidad(); i++) {
                    if (f.getTotal() + valor > limit) {
                        facturas.add(f);
                        f = new Factura(medio);
                    }
                    f.addBebida(new DetalleBebida(detalleBebida.getCode(), 1));
                    f.setTotal(f.getTotal() + valor);
                }
            }
        }
        if (f.getTotal() + tip > limit) {
            facturas.add(f);
            f = new Factura(medio);
        }
        f.setTip(tip);
        return facturas;
    }

    @RequestMapping("/ocuparMesa")
    public void ocuparMesa(Integer code) throws TableNotFoundException, TableAlreadyOccupiedException {
        mesaService.getMesa(code).open();
    }

    @RequestMapping("/liberarMesa")
    public void liberarMesa(Integer code) throws TableNotFoundException, TableNotOccupiedException {
        mesaService.getMesa(code).close();
    }

    @Override
    @RequestMapping("/obtenerEstadoMesa")
    public @ResponseBody Integer obtenerEstadoMesa(Integer code) throws TableNotFoundException {
        return Arrays.asList(Mesa.State.values()).indexOf(mesaService.getMesa(code).getState());
    }

    @RequestMapping("/obtenerSaldo")
    public Long obtenerSaldo(Integer code) throws TableNotFoundException, TableNotOccupiedException {
        return cuentaMesa(code).getTotal();
    }

    @RequestMapping("/obtenerPedido")
    public @ResponseBody List<Pedido> obtenerPedidos(Integer code) throws TableNotFoundException {
        return mesaService.getMesa(code).getPedidos();
    }

    @RequestMapping("/obtenerMesas")
    public @ResponseBody List<Mesa> obtenerMesas() throws TableNotFoundException {
        return mesaService.getMesas();
    }

    @RequestMapping("/crearMesa")
    public @ResponseBody Mesa crearMesa(String name) throws DuplicateTableException, IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException();
        }
        return mesaService.createMesa(name);
    }

    @Override
    public void quitarMesa(Integer code) throws TableNotFoundException {
        mesaService.destroyMesa(code);
    }
}
