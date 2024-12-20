package pe.jakarta.lp1.proyecto_biblioteca.controller;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/**
 * Controlador para gestionar el inicio y cierre de sesión.
 * Usa credenciales fijas para el usuario bibliotecario.
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    // Credenciales fijas
    private static final String USERNAME = "Bibliotecario";
    private static final String PASSWORD = "lectura2024";

    // Atributos del controlador
    private String username;
    private String password;

    
    public String login() {
        try {
            if (USERNAME.equals(username) && PASSWORD.equals(password)) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", username);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", "Inicio de sesión exitoso."));
                return "/inicio/menu?faces-redirect=true"; 
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos."));
                return null; 
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Hubo un problema con el inicio de sesión."));
            return null;
        }
    }

    
    public String logout() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesión cerrada", "Ha cerrado sesión exitosamente."));
            return "/index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se pudo cerrar la sesión."));
            return null;
        }
    }

  
    public boolean isAuthenticated() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null;
    }

   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}