package pe.jakarta.lp1.proyecto_biblioteca.controller;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import pe.jakarta.lp1.proyecto_biblioteca.dao.ClienteDao;
import pe.jakarta.lp1.proyecto_biblioteca.dao.DetallePrestamoDao;
import pe.jakarta.lp1.proyecto_biblioteca.dao.LibroDao;
import pe.jakarta.lp1.proyecto_biblioteca.dao.PrestamoDao;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Cliente;
import pe.jakarta.lp1.proyecto_biblioteca.entity.DetallePrestamo;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Libro;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Prestamo;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PrestamoController implements Serializable {
	@EJB
	private LibroDao libroDao;
	@EJB
	private ClienteDao clienteDao;
	@EJB
	private DetallePrestamoDao detallePrestamoDao;
	@EJB
	private PrestamoDao prestamoDao;
	
	private List<Libro> libros;
	private List<Cliente> clientes;
	private List<DetallePrestamo> detallesPrestamoList = new ArrayList<>();
	private List<Prestamo> filteredPrestamos;
	private DetallePrestamo detallePrestamoTemp = new DetallePrestamo();
	private Cliente cliente;
	
	private List<Prestamo> prestamos;
	private Prestamo prestamo = new Prestamo();
	
	public String listarPrestamos() {
		prestamo = new Prestamo();
		return "/prestamo/listaPrestamo?faces-redirect=true";
	}
	
	public String nuevoPrestamo() {
		prestamo = new Prestamo();
		return "/prestamo/nuevoPrestamo?faces-redirect=true";
	}
	
	public String verDetalle(Prestamo prestamoSeleccionado) {
	    this.prestamo = prestamoSeleccionado;
	    this.detallesPrestamoList = prestamoSeleccionado.getDetalles();

	    return "/prestamo/detallePrestamo?faces-redirect=true"; }

	public void agregarItem() {
		

		if (detallePrestamoTemp.getLibro() == null || detallePrestamoTemp.getLibro().getLibroId() == null) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un cliente antes de proceder", ""));
	        return;
	    }
		
		Integer libriSeleccionadoId = detallePrestamoTemp.getLibro().getLibroId();
		Libro libro = obtenetLibroDeLaLista(libriSeleccionadoId);
		
		for (DetallePrestamo detallePrestamo : detallesPrestamoList) {
			if(detallePrestamo.getLibro().getLibroId().equals(libriSeleccionadoId)) {
				FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El libro seleccionado ya se encuentra agregado", ""));
                    return;
			}
		}
		
		 if (detallesPrestamoList.size() >= 3) {
		        FacesContext.getCurrentInstance().addMessage(null,
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, 
		            "No se pueden agregar más de 3 libros.", null));
		        return;
		    }

		if (libro != null) {
	        detallePrestamoTemp.setLibro(libro);
	        detallesPrestamoList.add(detallePrestamoTemp);
	        
	        FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_INFO, "LibroAgregado " , ""));
		    
	        

	        prestamo.setCantidad(detallesPrestamoList.size());
	        detallePrestamoTemp = new DetallePrestamo();  // Reiniciar para un nuevo libro
	    }
		
		
		

		
	}

	// Metodo registrar
	public void registrarPrestamo() {
		
		
		 if (detallesPrestamoList.isEmpty()) {
		        FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe agregar al menos un libro al préstamo", ""));
		        return;
		    }

		    if (prestamo.getCliente() == null || prestamo.getCliente().getClienteId() == null) {
		        FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un cliente", ""));
		        return;
		    }
		    
		    prestamo.setFechaPrestamo(LocalDate.now());

		
		for (DetallePrestamo detallePrestamo : detallesPrestamoList) {
			detallePrestamo.setPrestamo(prestamo);
		}

		prestamo.setDetalles(detallesPrestamoList);

		Integer clienteSeleccionadoId = prestamo.getCliente().getClienteId();

		Cliente cliente = obtenerClienteDeLaLista(clienteSeleccionadoId);

		prestamo.setCliente(cliente);
		
		prestamoDao.guardarPrestamo(prestamo);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Prestamo registrado de manera satisfactoria", ""));


		listarPrestamos();
		prestamo = new Prestamo();
		

		cancelar();

	}

	private Libro obtenetLibroDeLaLista(Integer libroSeleccionadoId) {
		for (Libro libro : libros) {
			if (libro.getLibroId().equals(libroSeleccionadoId)) {
				return libro;
			}
		}

		return null;
	}

	private Cliente obtenerClienteDeLaLista(Integer cienteSeleccionadoId) {

		for (Cliente cliente : clientes) {
			if (cliente.getClienteId().equals(cienteSeleccionadoId)) {
				return cliente;
			}
		}

	

		return null;

	}
	
	
//------------------
	public void confirmarCliente() {
	    if (prestamo.getCliente() == null || prestamo.getCliente().getClienteId() == null) {
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Seleccione un cliente antes de proceder", ""));
	    } else {
	    	Cliente cliente = clienteDao.obtenerClientePorId(prestamo.getCliente().getClienteId());
	        prestamo.setCliente(cliente);
	        FacesContext.getCurrentInstance().addMessage(null, 
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente confirmado: " + prestamo.getCliente().getNombre(), ""));
	    }
	}
	//-------------------
	public void eliminarItem(DetallePrestamo detalle) {
		detallesPrestamoList.remove(detalle);
		prestamo.setCantidad(detallesPrestamoList.size());
		
	}
	//------
	public void marcarComoDevuelto(DetallePrestamo detalle) {
		
	   detalle.setEstado("Devuelto");

	    Libro libro = detalle.getLibro();
	    libro.setCantidadPrestados(libro.getCantidadPrestados() - 1); // 
	    libro.setCantidadActual(libro.getCantidadActual() + 1); 
	    
	    libroDao.actualizarLibro(libro);
	    
	    calcularMulta(detalle.getPrestamo());


        detallePrestamoDao.actualizarDetalle(detalle);
        prestamoDao.actualizarPrestamo(detalle.getPrestamo());

        // Verifica si todos los libros han sido devueltos
        boolean todosDevueltos = true;
        for (DetallePrestamo item : detalle.getPrestamo().getDetalles()) {
            if (!"Devuelto".equals(item.getEstado())) {
                todosDevueltos = false;
                break;
            }
        }
	    
	    if (todosDevueltos) {
	        detalle.getPrestamo().setEstado("Devuelto");
	        FacesContext.getCurrentInstance().addMessage(null,
	                new FacesMessage(FacesMessage.SEVERITY_INFO, "El préstamo ha sido devuelto completamente", ""));
	    }

	    FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro devuelto correctamente", ""));

	}
	
	//-----------------
	
	public void calcularMulta(Prestamo prestamo) {
        LocalDate fechaActual = LocalDate.now();
        if (prestamo.getFechaDevolucion() != null && fechaActual.isAfter(prestamo.getFechaDevolucion())) {
            long diasRetraso = java.time.temporal.ChronoUnit.DAYS.between(prestamo.getFechaDevolucion(), fechaActual);
            long periodosDeRetraso = (diasRetraso + 1) / 2; // +1 para redondear en caso de días impares
            double multa = periodosDeRetraso * prestamo.getDetalles().get(0).getLibro().getMulta(); // Suponiendo que la multa es la misma para todos los libros

            prestamo.setMultaTotal(multa);
        }
    }
	
	//----------

	public void cancelar() {
		prestamo = new Prestamo();
		detallesPrestamoList.clear();
	}

	// Getters y Setters
	public List<Libro> getLibros() {
		if (libros == null) {
			libros = libroDao.obtenerLibros();
		}
		return libros;
	}

	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = clienteDao.obtenerClientes();
		}
		return clientes;
	}
	public List<Prestamo> getPrestamos() {
		if(prestamos == null ) {

			prestamos = prestamoDao.obtenerPrestamos();
		}
        return prestamos;
    }
	public List<DetallePrestamo> getDetallesPrestamoList() {
		return detallesPrestamoList;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public DetallePrestamo getDetallePrestamoTemp() {
		return detallePrestamoTemp;
	}

	public void setDetallePrestamoTemp(DetallePrestamo detallePrestamoTemp) {
		this.detallePrestamoTemp = detallePrestamoTemp;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void setDetallesPrestamoList(List<DetallePrestamo> detallesPrestamoList) {
		this.detallesPrestamoList = detallesPrestamoList;
	}
	
	public List<Prestamo> getFilteredPrestamos() {
        return filteredPrestamos;
    }

    public void setFilteredPrestamos(List<Prestamo> filteredPrestamos) {
        this.filteredPrestamos = filteredPrestamos;
    }

}
