package pe.jakarta.lp1.proyecto_biblioteca.controller;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import pe.jakarta.lp1.proyecto_biblioteca.dao.ClienteDao;
import pe.jakarta.lp1.proyecto_biblioteca.entity.Cliente;

@Named
@SessionScoped
public class ClienteController implements Serializable {
	
	@EJB
	private ClienteDao dao;
	
	private List<Cliente> clientes;
	private Cliente cliente;
	//
	
	public String listarClientes() {
		clientes = null;
		return "/cliente/listaCliente?faces-redirect=true";
	}
	
	public String nuevoCliente() {
		cliente = new Cliente();
		return "/cliente/nuevoCliente?faces-redirect=true";
	}
	
	
	public String verDetalleCliente() {

		Integer clienteId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clienteId"));

		cliente = dao.obtenerClientePorId(clienteId);
		return "/cliente/detalleCliente?faces-redirect=true";
	}
	
	
	public String editarCliente() {
		int clienteId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clienteId"));

		cliente = dao.obtenerClientePorId(clienteId);
		return "/cliente/editarCliente";
	}
	
	public String eliminarCliente() {
		int clienteId = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clienteId"));

		Cliente clienteAElminar = new Cliente(clienteId);

		
		dao.eliminarCliente(clienteAElminar);
		return listarClientes();
	}
	
	public String crear() {
		try {
			dao.crearCliente(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente guardado de manera satisfactoria"));
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al guardar el Cliente"));
			return null;
		}
		
		return listarClientes();
	}
	

	public String actualizar() {
		try {
			dao.actualizarCliente(cliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Los datos del cliente se actualizaron de manera satisfactoria"));
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al actualizar el Cliente"));
			return null;
		}
		
		return listarClientes();
	}

	

	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = dao.obtenerClientes();
		}
		
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	

}
