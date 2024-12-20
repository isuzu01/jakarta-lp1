package pe.jakarta.lp1.proyecto_biblioteca.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRESTAMO")
public class Prestamo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRESTAMO_ID")
	private Integer prestamoId;
	
	@Column(name = "FECHA_PRESTAMO", nullable = false)
	private LocalDate fechaPrestamo;
	
	@Column(name = "FECHA_DEVOLUCION", nullable = false)
	private LocalDate fechaDevolucion;
	
	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID", nullable = true)
	private Cliente cliente = new Cliente();
	

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

	
	@Column(name = "ESTADO")
	private String estado = "Pendiente";
	
	@Column(name = "MULTA_TOTAL")
	private Double multaTotal;
	
	@OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePrestamo> detalles = new ArrayList<DetallePrestamo>();
	

	public Integer getPrestamoId() {
		return prestamoId;
	}

	public void setPrestamoId(Integer prestamoId) {
		this.prestamoId = prestamoId;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetallePrestamo> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePrestamo> detalles) {
		this.detalles = detalles;
	}

	public Double getMultaTotal() {
		return multaTotal;
	}

	public void setMultaTotal(Double multaTotal) {
		this.multaTotal = multaTotal;
	}
	
	
	
}
