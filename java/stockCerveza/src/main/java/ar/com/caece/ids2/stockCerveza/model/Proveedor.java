package ar.com.caece.ids2.stockCerveza.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="Proveedor")
@NamedQueries({
    @NamedQuery(name="Proveedor.listarProveedores",
                query="SELECT p FROM Proveedor p")
}) 
public class Proveedor {

	@Id
	@GeneratedValue(generator="sqlite_proveedor")
	@TableGenerator(name="sqlite_proveedor", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="Proveedor",
	    initialValue=1, allocationSize=1)
	private int idProveedor;
	
	
	private String razonSocial;
	
	private String cuit;
	private Long nroIngresosBrutos;
	private Long cbu;
	private String direccion;
	private String mailContacto;
	
	
	

	private String mailEmpresa;
	
	private String telefono;
	private String telefonoContacto;
	
	
	@Column(name="estado")
	private boolean estado;

	@OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY,mappedBy = "proveedor")
	private List<MarcaCerveza> marcaCerveza;
	
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public List<MarcaCerveza> getMarcaCerveza() {
		return marcaCerveza;
	}
	public void setMarcaCerveza(List<MarcaCerveza> marcaCerveza) {
		this.marcaCerveza = marcaCerveza;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public Long getNroIngresosBrutos() {
		return nroIngresosBrutos;
	}
	public void setNroIngresosBrutos(Long nroIngresosBrutos) {
		this.nroIngresosBrutos = nroIngresosBrutos;
	}
	public Long getCbu() {
		return cbu;
	}
	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getMailContacto() {
		return mailContacto;
	}
	public void setMailContacto(String mailContacto) {
		this.mailContacto = mailContacto;
	}
	public String getMailEmpresa() {
		return mailEmpresa;
	}
	public void setMailEmpresa(String mailEmpresa) {
		this.mailEmpresa = mailEmpresa;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	
	@Override
	public String toString(){
		return this.getRazonSocial() + this.getTelefono();
	}
}
