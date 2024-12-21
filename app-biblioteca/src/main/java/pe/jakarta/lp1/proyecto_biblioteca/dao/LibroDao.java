package pe.jakarta.lp1.proyecto_biblioteca.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Cliente;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Libro;

@Stateless
public class LibroDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Libro> obtenerLibros(){
		
	List<Libro> libros = em.createQuery("select l from Libro l", Libro.class).getResultList();
	
	return libros;
	}
	
	public void crearLibro(Libro libro) {
		try {
			em.persist(libro);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al crear el libro: " + libro, e);
		}
	}
	
	public void actualizarLibro(Libro libro) {
		try {
			em.merge(libro);
		} catch(Exception e) {
			throw new RuntimeException("Error al actualizar el libro: " + libro, e);
		}
	}
	
	public Libro obtenerLibroPorId(int id) {
		Libro libro = em.find(Libro.class, id);
		return libro;
	}
	
	public void eliminarLibro(Libro libro) {
		try {
			libro = em.getReference(Libro.class, libro.getLibroId());
			em.remove(libro);
		}catch (Exception e) {
			throw new RuntimeException("Error al eliminar el libro: " + libro, e);
		}
	}	
	
	public void actualizarStockLibro(Libro libro) {
	    try {
	        em.merge(libro);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al actualizar el stock del libro: " + libro, e);
	    }
	}
	
	public List<Libro> obtenerLibrosDisponibles() {
		List<Libro> libros;
        libros = em.createQuery("select l from Libro l where l.cantidadActual > 0", Libro.class)
                 .getResultList();
        
        return libros;
    }
	
	
}
