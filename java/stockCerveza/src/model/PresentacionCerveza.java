package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="PresentacionCerveza")
@NamedQueries({
    @NamedQuery(name="PresentacionCerveza.listarPresentacionCerveza",
                query="SELECT p FROM PresentacionCerveza p")
})
public class PresentacionCerveza {

	@Id
	@GeneratedValue(generator="sqlite_presentacion")
	@TableGenerator(name="sqlite_presentacion", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Presentacion",
	    initialValue=1, allocationSize=1)
	private int idPresentacion;
	
	private String desripcion;

	public int getIdPresentacion() {
		return idPresentacion;
	}

	public void setIdPresentacion(int idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public String getDesripcion() {
		return desripcion;
	}

	public void setDesripcion(String desripcion) {
		this.desripcion = desripcion;
	}
}
