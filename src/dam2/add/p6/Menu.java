package dam2.add.p6;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Menu {

	private static Connection conexion;
	private static Statement st;

	public void Menu(ResourceBundle rb) throws SQLException {
		Scanner sc = new Scanner(System.in);
		OpsDataBaseDAO operacion = new OpsDataBaseDAO();
		Ops comunOps = new Ops();

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
					operacion.jugar();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 2:
					operacion.anadirPreguntas();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 3:
					operacion.importarPreguntas();
					st.close();
					ConexionBaseDatos.desconectar();
					break;
				case 4:
					operacion.verRecords();
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
