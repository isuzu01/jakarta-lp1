package pe.jakarta.lp1.proyecto_biblioteca.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Cliente;

@Stateless
public class ClienteDao {

	@PersistenceContext
	private EntityManager em;

	public List<Cliente> obtenerClientes() {

		List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();

		return clientes;
	}
	
	public void crearCliente(Cliente cliente) {
	    try {
	        em.persist(cliente);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al crear el cliente: " + cliente, e);
	    }
	}

	public void actualizarCliente(Cliente cliente) {
		 try {
		        em.merge(cliente);
		    } catch (Exception e) {
		        throw new RuntimeException("Error al actualizar datos del cliente: " + cliente, e);
		    }
	}
	
	public Cliente obtenerClientePorId(int id) {
		Cliente cliente = em.find(Cliente.class, id) ;
		return cliente;
	}
	

	public void eliminarCliente(Cliente cliente) {
	    try {
	    	cliente = em.getReference(Cliente.class, cliente.getClienteId());
	        em.remove(cliente);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al eliminar el cliente: " + cliente, e);
	    }
	}
	

	//-------------------------------
	/*public List<Cliente> buscarClientes(String query) {
	    return em.createQuery("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE :query OR c.dni LIKE :query", Cliente.class)
	             .setParameter("query", "%" + query.toLowerCase() + "%")
	             .getResultList();
	}
	*/
	public List<Cliente> buscarClientes(String dni) {
	    return em.createQuery("SELECT c FROM Cliente c WHERE  c.dni LIKE :dni", Cliente.class)
	             .setParameter("dni", "%" + dni + "%")
	             .getResultList();
	}
	
}
