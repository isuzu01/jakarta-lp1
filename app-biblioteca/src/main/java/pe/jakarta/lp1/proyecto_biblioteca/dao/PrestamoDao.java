package pe.jakarta.lp1.proyecto_biblioteca.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Prestamo;

@Stateless
public class PrestamoDao {
	@PersistenceContext
	EntityManager em;
	
	public void guardarPrestamo(Prestamo prestamo) {
		
		try {
			em.persist(prestamo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarPrestamo(Prestamo prestamo) {
        em.merge(prestamo);  
    }
	
	public List<Prestamo> obtenerPrestamos() {
        return em.createQuery("SELECT p FROM Prestamo p", Prestamo.class).getResultList();
    }
	
	public List<Prestamo> obtenerPrestamosPorEstado(Integer clienteId, String estado) {
	    return em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.clienteId = :clienteId AND p.estado = :estado", Prestamo.class)
	             .setParameter("clienteId", clienteId)
	             .setParameter("estado", estado)
	             .getResultList();
	}

}
