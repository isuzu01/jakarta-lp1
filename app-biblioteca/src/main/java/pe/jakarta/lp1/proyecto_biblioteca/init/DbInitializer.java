package pe.jakarta.lp1.proyecto_biblioteca.init;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
@DataSourceDefinition(
		name = "java:app/jdbc/bibliotecaBDDatasource",
	   className = "com.mysql.cj.jdbc.Driver",
	   url = "jdbc:mysql://localhost:3306/bibliotecabd?serverTimezone=UTC",
	   user = "root",
	   password = "mysql"
		)
public class DbInitializer {
	
	private void init(@Observes @Initialized(ApplicationScoped.class) Object object) {
	       // Método vacío que se activa al inicializar la aplicación
	   }

}
