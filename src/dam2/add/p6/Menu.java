package dam2.add.p6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

public class Menu {
	private static Logger log = Logger.getLogger(OperacionesBaseDatos.class);
	
	private static Connection conexion;
	private static Statement st;

	public void Menu(ResourceBundle rb) throws SQLException {
		
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		try {
			log.addAppender(new FileAppender(new PatternLayout(),"./properties/prueba.log", false));
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.toString());
		}
		
		Scanner sc = new Scanner(System.in);
		Ops comunOps = new Ops();
		Properties properties = new Properties();
		
		String repositorio = "./properties/persistencia.properties";
		IJugadorDAO daoJug = null;
		IPreguntaDAO daoPreg = null;
		
		try {
			properties.load(new FileInputStream(repositorio));
			
			if (repositorio.equals("mysql")) {
				daoJug = new OpsDataBaseDAO();
				daoPreg = new OpsDataBaseDAO();
			} else if (repositorio.equals("fichero")){
				daoJug = new OpsFileDAO();
				daoPreg = new OpsFileDAO();
			}
			
		} catch (FileNotFoundException e) {
			log.error(e.toString());
		} catch (IOException e) {
			log.error(e.toString());
		}
		
		

		boolean salir = false;
		int opcion;

		conexion = ConexionBaseDatos.getConexion();
		st = conexion.createStatement();

		while (!salir) {

			System.out.println("MENU");
			System.out.println("1 - " + rb.getString("jugar"));
			System.out.println("2 - " + rb.getString("anadir"));
			System.out.println("3 - " + rb.getString("importar"));
			System.out.println("4 - " + rb.getString("records"));
			System.out.println("5 - " + rb.getString("instrucciones"));
			System.out.println("6 - " + rb.getString("volver"));

			try {
				System.out.println(rb.getString("opcion"));
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					daoPreg.jugar();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 2:
					daoPreg.anadirPreguntas();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 3:
					daoPreg.importarPreguntas();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 4:
					daoJug.verRecords();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 5:
					comunOps.instrucciones();
					break;
				case 6:
					salir = true;
					break;
				default:
					System.out.println("\nElije del 1 al 6\n");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nDebe insertar un numero\n");
			}
		}
	}
}
