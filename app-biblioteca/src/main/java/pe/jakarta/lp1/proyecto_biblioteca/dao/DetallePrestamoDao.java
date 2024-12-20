package pe.jakarta.lp1.proyecto_biblioteca.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pe.jakarta.lp1.proyecto_biblioteca.entity.DetallePrestamo;

@Stateless
public class DetallePrestamoDao {
	
	 @PersistenceContext
	    private EntityManager em;

	    public void guardarDetalle(DetallePrestamo detallePrestamo) {
	        em.persist(detallePrestamo);
	    }
	    
	    public void actualizarDetalle(DetallePrestamo detalle) {
	        em.merge(detalle); 
	    }

}
