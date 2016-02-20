package ar.com.caece.ids2.stockCerveza.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="MarcaCerveza")
@NamedQueries({
    @NamedQuery(name="MarcaCerveza.listarMarcasCerveza",
                query="SELECT m FROM MarcaCerveza m")
})
public class MarcaCerveza {
	
	@Id
	@GeneratedValue(generator="sqlite_person")
	@TableGenerator(name="sqlite_person", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Person",
	    initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "marcacerveza")
	private List<Cerveza> cervezas;
	
	@ManyToOne
	@JoinColumn(name="proveedorId")
	private Proveedor proveedor;
	
	@Column(name="estado")
	private boolean estado;

	
	public List<Cerveza> getCervezas() {
		return cervezas;
	}
	public void setCervezas(List<Cerveza> cervezas) {
		this.cervezas = cervezas;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
