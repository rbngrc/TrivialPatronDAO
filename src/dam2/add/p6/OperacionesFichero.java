package dam2.add.p6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class OperacionesFichero {

	ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
	String path = "ficheros/preguntas.xml";
	SAXBuilder builder = new SAXBuilder();
	File xmlFile = new File(path);

	public ArrayList<Pregunta> lecturaFichero() {

		try {
			Document document = builder.build(xmlFile);

			Element rootNode = document.getRootElement();

			String nombreNodo = rootNode.getName();
			System.out.println("raiz: " + nombreNodo);

			List lista_preguntas = rootNode.getChildren("pregunta");

			for (int i = 0; i < lista_preguntas.size(); i++) {
				Element xmlPregunta = (Element) lista_preguntas.get(i);

				Element elemento_texto = xmlPregunta.getChild("texto");
				String texto = elemento_texto.getText();

				String respuesta1 = xmlPregunta.getChildText("respuesta1");
				String respuesta2 = xmlPregunta.getChildText("respuesta2");
				String respuesta3 = xmlPregunta.getChildText("respuesta3");
				String correcta = xmlPregunta.getChildText("correcta");

				Pregunta preg = new Pregunta(texto, respuesta1, respuesta2, respuesta3, correcta);
				listaPreguntas.add(preg);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaPreguntas;
	}

	public void insertarPreguntas(ArrayList<Pregunta> lista_preguntas) {
		String docNuevoStr = "";
		try {
			Document docNuevo = builder.build(xmlFile);

			for (int i = 0; i < lista_preguntas.size(); i++) {
				Pregunta preg = lista_preguntas.get(i);
				String texto = preg.getPregunta();
				String respuesta1 = preg.getRespuesta1();
				String respuesta2 = preg.getRespuesta2();
				String respuesta3 = preg.getRespuesta3();
				String correcta = preg.getCorrecta();

				Element nodoPregunta = new Element("pregunta");
				docNuevo.getRootElement().addContent(nodoPregunta);

				Element nodoTexto = new Element("texto");
				nodoTexto.setText(texto);
				nodoPregunta.addContent(nodoTexto);

				Element nodoRespuesta1 = new Element("respuesta1");
				nodoRespuesta1.setText(respuesta1);
				nodoPregunta.addContent(nodoRespuesta1);

				Element nodoRespuesta2 = new Element("respuesta2");
				nodoRespuesta2.setText(respuesta2);
				nodoPregunta.addContent(nodoRespuesta2);

				Element nodoRespuesta3 = new Element("respuesta3");
				nodoRespuesta3.setText(respuesta3);
				nodoPregunta.addContent(nodoRespuesta3);

				Element nodoCorrecta = new Element("correcta");
				nodoCorrecta.setText(correcta);
				nodoPregunta.addContent(nodoCorrecta);

				Format format = Format.getPrettyFormat();

				XMLOutputter xmloutputter = new XMLOutputter(format);
				docNuevoStr = xmloutputter.outputString(docNuevo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		FileWriter fichero = null;
		try {
			fichero = new FileWriter(path);
			PrintWriter pw = new PrintWriter(fichero);
			pw.println(docNuevoStr);
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No existe el fichero");
		}
		System.out.println("Reinicia para aplicar cambios");
	}

}
