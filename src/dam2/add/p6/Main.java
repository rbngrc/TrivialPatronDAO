package dam2.add.p6;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		Properties properties = new Properties();
		
		Locale locale;
		ResourceBundle rb;
		String fichero = "./properties/idioma.properties";
		String cargaIdioma;
		
		Menu menu = new Menu();

		boolean salir = false;
		int opcion;
		
		properties.load(new FileInputStream(fichero));
		cargaIdioma = properties.getProperty("idioma");
		locale = new Locale("./properties/idioma");
		rb = ResourceBundle.getBundle(cargaIdioma, locale);

		while (!salir) {
			System.out.println(rb.getString("seleccione"));
			System.out.println("1 - " + rb.getString("espanol"));
			System.out.println("2 - " + rb.getString("ingles"));
			System.out.println("3 - " + rb.getString("salir"));
			try {
				System.out.println(rb.getString("opcion"));
				opcion = sc.nextInt();

				switch (opcion) {
				case 1:
					locale = new Locale("es");
					rb = ResourceBundle.getBundle("es", locale);
					properties.setProperty("idioma", "es");
					properties.store(new FileOutputStream(fichero), null);
					menu.Menu(rb);
					break;
				case 2:
					locale = new Locale("en");
					rb = ResourceBundle.getBundle("en", locale);
					properties.setProperty("idioma", "en");
					properties.store(new FileOutputStream(fichero), null);
					menu.Menu(rb);
					break;
				case 3:
					salir = true;
					break;
				default:
					System.out.println("\nElije un numero entre 1 y 3\n");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("\nDebe insertar un numero\n");
			}

		}
	}

}
