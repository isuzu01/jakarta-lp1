package pe.jakarta.lp1.proyecto_biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import pe.jakarta.lp1.proyecto_biblioteca.dao.LibroDao;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Libro;

@Named
@SessionScoped
public class LibroController implements Serializable {

	@EJB
	private LibroDao dao;

	private List<Libro> libros;
	private Libro libro;
	//
	//

	public String listarLibros() {
		libros = null;
		return "/libro/lista?faces-redirect=true";
	}

	public String nuevoLibro() {
		libro = new Libro();
		return "/libro/nuevo?faces-redirect=true";
	}
	
	public String prestarLibro() {
		int libroId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("libroId"));

		libro = dao.obtenerLibroPorId(libroId);

		return "/prestamo/nuevoPrestamo";
	}

	public String editarlibro() {

		int libroId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("libroId"));

		libro = dao.obtenerLibroPorId(libroId);

		return "/libro/editar";
	}

	public String verDetalleLibro() {

		Integer libroId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("libroId"));

		libro = dao.obtenerLibroPorId(libroId);
		return "/libro/detalle";
	}

	public String crear() {
		try {
			dao.crearLibro(libro);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Libro guardado de manera satisfactoria"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al guardar el Libro"));
			return null;
		}

		return listarLibros();

	}

	public String actualizar() {
		try {
			dao.actualizarLibro(libro);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Los datos del Libro se actualizaron de manera satisfactoria"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizar el Libro"));
			return null;
		}

		return listarLibros();
	}

	public String eliminar() {
		int libroId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("libroId"));

		Libro libroAEliminar = new Libro(libroId);

		dao.eliminarLibro(libroAEliminar);
		return listarLibros();

	}

	//
	public List<Libro> getLibros() {
		if (libros == null) {
			libros = dao.obtenerLibros();
		}
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}
