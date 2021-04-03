package dam2.add.p6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import jxl.Sheet;
import jxl.Workbook;

public class OpsFileDAO implements IJugadorDAO, IPreguntaDAO {

	OperacionesFichero operacionesFichero = new OperacionesFichero();
	Ops comunOps = new Ops();
	String path = "ficheros/preguntas.xml";
	String pathPDF = "ficheros/salida.pdf";
	static int aciertos;
	static int fallos;

	Scanner sc = new Scanner(System.in);

	public void jugar() {
		aciertos = 0;
		fallos = 0;

		ArrayList<Pregunta> listaPreguntas = operacionesFichero.lecturaFichero();

		for (int i = 0; i < listaPreguntas.size(); i++) {
			Pregunta preg = listaPreguntas.get(i);
			String texto = preg.getPregunta();
			String respuesta1 = preg.getRespuesta1();
			String respuesta2 = preg.getRespuesta2();
			String respuesta3 = preg.getRespuesta3();

			System.out.println(texto + "\n1.-" + respuesta1 + "\n2.-" + respuesta2 + "\n3.-" + respuesta3);
			System.out.println("Cual elije respuesta (1, 2 o 3)");
			String resultado = sc.nextLine();

			if (resultado.equals(preg.getCorrecta())) {
				respuestaCorrecta();
			} else {
				respuestaIncorrecta();
			}
		}

		anadirRecord();
		comunOps.generarPDF(aciertos, fallos);

	}

	public void anadirPreguntas() {

		ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();

		System.out.println("Escriba la pregunta");
		String texto = sc.nextLine();
		System.out.println("Escriba la primera respuesta");
		String respuesta1 = sc.nextLine();
		System.out.println("Escriba la segunda respuesta");
		String respuesta2 = sc.nextLine();
		System.out.println("Escriba la tercera respuesta");
		String respuesta3 = sc.nextLine();
		System.out.println("indique el numero de la respuesta correcta (1, 2 o 3)");
		String correcta = sc.nextLine();

		Pregunta pregunta = new Pregunta(texto, respuesta1, respuesta2, respuesta3, correcta);
		listaPreguntas.add(pregunta);

		OperacionesFichero ops = new OperacionesFichero();
		ops.insertarPreguntas(listaPreguntas);
	}

	public ArrayList<Pregunta> importarPreguntas() {

		ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();

		File f = new File("ficheros/preguntasNuevas.xls");
		if (f.exists()) {
			try {
				Workbook w = Workbook.getWorkbook(f);
				Sheet sheet = w.getSheet(0);

				for (int i = 1; i < sheet.getRows(); i++) {
					String pregunta = sheet.getCell(0, i).getContents();
					String respuesta1 = sheet.getCell(1, i).getContents();
					String respuesta2 = sheet.getCell(2, i).getContents();
					String respuesta3 = sheet.getCell(3, i).getContents();
					String correcta = sheet.getCell(4, i).getContents();

					Pregunta preg = new Pregunta(pregunta, respuesta1, respuesta2, respuesta3, correcta);
					listaPreguntas.add(preg);

				}

				OperacionesFichero ops = new OperacionesFichero();
				ops.insertarPreguntas(listaPreguntas);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No existe fichero para importar");
		}

		return listaPreguntas;
	}

	public void verRecords() {
		File f = new File("ficheros/registro.txt");

		if (f.exists()) {

			try {

				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
				String st;
				st = null;

				System.out.println("\n" + "Estos son los records del juego" + "\n");

				while ((st = br.readLine()) != null) {
					System.out.println(st);
				}
				br.close();

			} catch (FileNotFoundException e) {
				System.out.println("Error al abrir el archivo");
			} catch (IOException ex) {
				System.out.println("Error de entrada/salida");
			}

		} else {
			System.out.println("No hay ningun record registrado");
		}

	}

	public int respuestaCorrecta() {
		System.out.println("Respuesta CORRECTA");
		aciertos++;
		if (aciertos == 1) {
			System.out.println("Llevas: " + aciertos + " acierto");
		} else {
			System.out.println("Llevas: " + aciertos + " aciertos");
		}
		return aciertos;

	}

	public int respuestaIncorrecta() {
		System.out.println("Respuesta INCORRECTA");
		fallos++;
		if (fallos == 1) {
			System.out.println("Llevas: " + fallos + " fallo");
		} else {
			System.out.println("Llevas: " + fallos + " fallos");
		}
		return fallos;
	}

	public void anadirRecord() {
		double puntuacion = (aciertos * 5) - (fallos * 0.5);
		Scanner scn = new Scanner(System.in);

		Writer userLog;

		File log = new File("ficheros/registro.txt");

		System.out.println("Introduce tu usuario");
		String usuarioRecord = scn.nextLine();

		if (log.exists()) {

			try {
				userLog = new BufferedWriter(new FileWriter("ficheros/registro.txt", true));
				userLog.append(usuarioRecord + " ha tenido una puntuacion de: " + puntuacion + "\n");
				userLog.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error al abrir el archivo");
			} catch (IOException ex) {
				System.out.println("Error de entrada/salida");
			}

		} else {

			try {
				FileOutputStream out = new FileOutputStream("ficheros/registro.txt");
				userLog = new BufferedWriter(new FileWriter("ficheros/registro.txt", true));
				userLog.append(usuarioRecord + " ha tenido una puntuacion de: " + puntuacion + "\n");
				userLog.close();
			} catch (FileNotFoundException e) {
				System.out.println("Error al abrir el archivo");
			} catch (IOException ex) {
				System.out.println("Error de entrada/salida");
			}

		}
	}

}
