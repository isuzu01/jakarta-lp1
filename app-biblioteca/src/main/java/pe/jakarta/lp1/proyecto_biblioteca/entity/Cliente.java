package pe.jakarta.lp1.proyecto_biblioteca.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENTE_ID")
	private Integer clienteId;

	@Column(name = "DNI")
	private String dni;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDO_PA")
	private String apellidoPa;

	@Column(name = "APELLIDO_MA")
	private String apellidoMa;

	@Column(name = "DIRECCION")
	private String direccion;

	@Column(name = "TELEFONO")
	private int telefono;

	@Column(name = "EMAIL")
	private String email;
	//
	
	public Cliente() {
		
	}

	public Cliente(Integer clienteId) {
		super();
		this.clienteId = clienteId;
	}
	
	//
	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPa() {
		return apellidoPa;
	}

	public void setApellidoPa(String apellidoPa) {
		this.apellidoPa = apellidoPa;
	}

	public String getApellidoMa() {
		return apellidoMa;
	}

	public void setApellidoMa(String apellidoMa) {
		this.apellidoMa = apellidoMa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
