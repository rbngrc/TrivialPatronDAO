package dam2.add.p6;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

public class ConexionBaseDatos {
	private static Logger log = Logger.getLogger(OperacionesBaseDatos.class);
	
	static String url;
	static String username;
	static String password;
	static String driver;

	static String fichero = "./properties/bbdd.properties";
	static Connection conexion;

	public static Connection getConexion() {
		if (conexion == null) {
			crearConexion();
		}
		return conexion;
	}

	public static boolean crearConexion() {
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		try {
			log.addAppender(new FileAppender(new PatternLayout(),"./properties/prueba.log", false));
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(fichero));

			url = properties.getProperty("db.url");
			username = properties.getProperty("db.user");
			password = properties.getProperty("db.password");
			driver = properties.getProperty("db.driver");
			
			log.debug(url + " " + username + " " + password + " " + driver);

			Class.forName(driver);
			conexion = DriverManager.getConnection(url, username, password);
			conexion.setAutoCommit(false);

		} catch (SQLException e) {
			log.error(e.toString());
			return false;
		} catch (Exception e) {
			log.error(e.toString());
			return false;
		}
		return true;
	}

	public static void desconectar() {
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		try {
			conexion.close();
			conexion = null;

		} catch (SQLException e) {
			log.error(e.toString());
			System.out.println("Error al cerrar la conexion");
		}
	}
}
