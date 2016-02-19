package model;

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
@Table(name="EstiloCerveza")
@NamedQueries({
    @NamedQuery(name="EstiloCerveza.listarEstiloCerveza",
                query="SELECT e FROM EstiloCerveza e")
})
public class EstiloCerveza {
	@Id
	@GeneratedValue(generator="sqlite_estilo_cerveza")
	@TableGenerator(name="sqlite_estilo_cerveza", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="EstiloCerveza",
	    initialValue=1, allocationSize=1)
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;
	
	
	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "estiloCerveza")
	private List<Cerveza> cervezas;
	
	@Column(name="estado")
	private boolean estado;

	
	public List<Cerveza> getCervezas() {
		return cervezas;
	}
	public void setCervezas(List<Cerveza> cervezas) {
		this.cervezas = cervezas;
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
