package pe.jakarta.lp1.proyecto_biblioteca.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIBRO")
public class Libro implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LIBRO_ID")
	private Integer libroId;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "AUTOR")
	private String autor;
	
	@Column(name = "EDITORIAL")
	private String editorial;
	
	@Column(name = "IDIOMA")
	private String idioma;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "MULTA")
	private Double  multa; // POR DIA DE RETRAZO
	
	@Column(name = "STOCK_ACTUAL")
	private Integer cantidadActual;
	
	@Column(name = "CANTIDAD_PRESTADO")
	private Integer cantidadPrestados;
	
	
	
	//
	
	public Libro() {
		
	}
	
	public Libro(Integer libroId) {
		super();
		this.libroId = libroId;
	}

	
	//

	public Integer getLibroId() {
		return libroId;
	}

	public void setLibroId(Integer libroId) {
		this.libroId = libroId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Integer getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Integer cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Integer getCantidadPrestados() {
		return cantidadPrestados;
	}

	public void setCantidadPrestados(Integer cantidadPrestados) {
		this.cantidadPrestados = cantidadPrestados;
	}
	
	
}
