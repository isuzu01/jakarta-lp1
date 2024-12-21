package pe.jakarta.lp1.proyecto_biblioteca.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLE_PRESTAMO")
public class DetallePrestamo implements Serializable{
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "DETALLE_ID")
	    private Integer detallePrestamoId;

	    @ManyToOne
	    @JoinColumn(name = "LIBRO_ID", referencedColumnName = "LIBRO_ID", nullable = false)
	    private Libro libro = new Libro();

	    @ManyToOne
	    @JoinColumn(name = "PRESTAMO_ID", referencedColumnName = "PRESTAMO_ID", nullable = false)
	    private Prestamo prestamo;
	    
	    @Column(name = "ESTADO")
	    private String estado = "Prestado";
	    
	    private Double multaRetraso;


		public Integer getDetallePrestamoId() {
			return detallePrestamoId;
		}

		public void setDetallePrestamoId(Integer detallePrestamoId) {
			this.detallePrestamoId = detallePrestamoId;
		}

		public Libro getLibro() {
			return libro;
		}

		public void setLibro(Libro libro) {
			this.libro = libro;
		}

		public Prestamo getPrestamo() {
			return prestamo;
		}

		public void setPrestamo(Prestamo prestamo) {
			this.prestamo = prestamo;
		}

		 public String getEstado() {
		        return estado;
		    }

		    public void setEstado(String estado) {
		        this.estado = estado;
		    }
		    
		    public Double getMultaRetraso() {
		        return multaRetraso;
		    }

		    public void setMultaRetraso(Double multaRetraso) {
		        this.multaRetraso = multaRetraso;
		    }
		    

}
