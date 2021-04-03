package dam2.add.p6;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Ops {
	private static Logger log = Logger.getLogger(OpsDataBaseDAO.class);
	String pathPDF = "ficheros/salida.pdf";

	Scanner sc = new Scanner(System.in);

	public void instrucciones() {
		
		System.out.println("Bienvenido a trivial \nEl juego es muy sencillo.\n"
				+ "Para jugar tienes que introducir la primera opcion en el menu.");
		System.out.println("Se iran mostrando las preguntas con 3 respuestas. CUIDADO! solo una es la correcta");
		System.out.println("Cada pregunta acertada suma 5 puntos en el marcador y cada pregunta fallada resta 0.5");
		System.out.println("Si al final podras introducir tu puntacion y comprobar quien de entre"
				+ "tu y todos tus amigos ha acertado mas preguntas");
		System.out.println("Si quieres puedes anadir mas preguntas con la opcion de anadir preguntas");
		System.out.println("VENGA A JUGAR");

	}

	public void generarPDF(int aciertos, int fallos) {
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		double puntuacion = (aciertos * 5) - (fallos * 0.5);

		PdfWriter writer = null;
		com.itextpdf.text.Document documento = new com.itextpdf.text.Document(PageSize.A4, 20, 20, 70, 50);

		try {
			writer = PdfWriter.getInstance(documento, new FileOutputStream(pathPDF));

			documento.open();

			Paragraph parrafo = new Paragraph();
			parrafo.add("Su numero de aciertos ha sido " + aciertos);
			parrafo.add("\nSu numero de fallos ha sido " + fallos);
			parrafo.add("\nLa puntuacion total es: " + puntuacion);
			parrafo.add("\nCada acierto suma 5 puntos y los fallos restan 0.5");

			documento.add(parrafo);

			documento.close();
			writer.close();

			try {
				File rutaPDF = new File(pathPDF);
				Desktop.getDesktop().open(rutaPDF);
			} catch (IOException e) {
				log.error(e.toString());
			}

		} catch (Exception e) {
			log.error(e.toString());
		}

	}

}
