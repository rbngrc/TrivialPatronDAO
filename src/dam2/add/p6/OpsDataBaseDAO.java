package dam2.add.p6;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import jxl.Sheet;
import jxl.Workbook;

public class OpsDataBaseDAO implements IJugadorDAO, IPreguntaDAO {
	private static Logger log = Logger.getLogger(OpsDataBaseDAO.class);
	OperacionesBaseDatos ops = new OperacionesBaseDatos();
	Ops comunOps = new Ops();
	String pathPDF = "ficheros/salida.pdf";
	static int aciertos;
	static int fallos;

	Scanner sc = new Scanner(System.in);

	public void jugar() {
		PropertyConfigurator.configure("./properties/log4j.properties");
		
		aciertos = 0;
		fallos = 0;

		ArrayList<Pregunta> listaPreguntas = ops.leerPreguntas();

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

		listaPreguntas.clear();
	}

	public void anadirPreguntas() {
		PropertyConfigurator.configure("./properties/log4j.properties");

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

		log.debug(pregunta);
		
		ops.insertarPreguntas(listaPreguntas);
	}

	public ArrayList<Pregunta> importarPreguntas() {
		PropertyConfigurator.configure("./properties/log4j.properties");

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
					
					log.debug(preg);
				}

				ops.insertarPreguntas(listaPreguntas);
				System.out.println("Preguntas importadas con exito");
			} catch (Exception e) {
				log.error(e.toString());
			}
		} else {
			System.out.println("No existe fichero para importar");
		}

		return listaPreguntas;
	}

	public void verRecords() {
		PropertyConfigurator.configure("./properties/log4j.properties");

		ArrayList<Jugador> recordJugador = ops.leerRecord();

		for (int i = 0; i < recordJugador.size(); i++) {
			Jugador jug = recordJugador.get(i);
			String nombre = jug.getNombre();
			String aciertos = jug.getAciertos();
			String fallos = jug.getFallos();
			String puntuacion = jug.getPuntuacion();

			System.out.println(nombre + " ha tenido " + aciertos + " aciertos y " + fallos
					+ " fallos. Su puntacion ha sido de " + puntuacion + " puntos");

		}
		recordJugador.clear();
	}

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

	public int respuestaCorrecta() {
		PropertyConfigurator.configure("./properties/log4j.properties");
		
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
		PropertyConfigurator.configure("./properties/log4j.properties");
		
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
		PropertyConfigurator.configure("./properties/log4j.properties");

		ArrayList<Jugador> recordJugador = new ArrayList<Jugador>();

		double puntuacion = (aciertos * 5) - (fallos * 0.5);
		Scanner scn = new Scanner(System.in);

		String act = String.valueOf(aciertos);
		String fll = String.valueOf(fallos);
		String pnt = String.valueOf(puntuacion);

		System.out.println("Introduce tu nombre");
		String nombre = scn.nextLine();

		Jugador jugador = new Jugador(nombre, act, fll, pnt);
		recordJugador.add(jugador);

		boolean existe = ops.comprobarRecord(nombre);
		boolean mayor = ops.comprobarPuntuacion(nombre, puntuacion);
		
		if (existe && mayor) {
			ops.actualizarRecord(recordJugador);
			System.out.println("Puntuacion actualizada!!");
		} else if (existe && !mayor) {
			System.out.println("Has sacado menos puntuacion, no actualizamos");
		} else {
			ops.insertarRecord(recordJugador);
			System.out.println("Tenemos una nueva puntuacion!!");
		}
	}

}
