package cocina.model;

import java.util.List;

public class Pedido {

	private int id;
	private int idMesa;
	private int estadoMesa;
	private List<DetallePlato> listaPlatos;
	private List<DetalleCerveza> listaCervezas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}
	public int getEstadoMesa() {
		return estadoMesa;
	}
	public void setEstadoMesa(int estadoMesa) {
		this.estadoMesa = estadoMesa;
	}
	public List<DetallePlato> getListaPlatos() {
		return listaPlatos;
	}
	public void setListaPlatos(List<DetallePlato> listaPlatos) {
		this.listaPlatos = listaPlatos;
	}
	public List<DetalleCerveza> getListaCervezas() {
		return listaCervezas;
	}
	public void setListaCervezas(List<DetalleCerveza> listaCervezas) {
		this.listaCervezas = listaCervezas;
	}
	
}
