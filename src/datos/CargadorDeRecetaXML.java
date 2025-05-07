package datos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modelo.Objeto;
import modelo.Receta;

public class CargadorDeRecetaXML extends ManejadorXML<Receta> {

	private List<Receta> recetas = new ArrayList<>();
	private Receta recetaActual;
	private Objeto ingredienteActual;
	private List<Objeto> ingredientesActuales;
	private StringBuilder contenido;


	public CargadorDeRecetaXML(String rutaArchivo) {
		super(rutaArchivo);
	}

	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		contenido = new StringBuilder();

		if (qName.equalsIgnoreCase("receta")) {
			ingredientesActuales = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("ingrediente")) {
			String nombre = attributes.getValue("nombre");
			String cantidadStr = attributes.getValue("cantidad");
			int cantidad = Integer.parseInt(cantidadStr);
			ingredienteActual = new Objeto(nombre, cantidad);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("receta")) {
			recetas.add(recetaActual);
			recetaActual = null;
		} else if (qName.equalsIgnoreCase("nombre")) {
			if (recetaActual == null) {
				String nombre = contenido.toString().trim();
				recetaActual = new Receta(nombre, 0, 0, ingredientesActuales);
			}
		} else if (qName.equalsIgnoreCase("tiempo")) {
			int tiempo = Integer.parseInt(contenido.toString().trim());
			if (recetaActual != null) {
				recetaActual = new Receta(recetaActual.getNombre(), tiempo, recetaActual.getCantidadProducida(),
						ingredientesActuales);
			}
		} else if (qName.equalsIgnoreCase("cantidad") && !qName.contains("ingrediente")) {
			int cantidad = Integer.parseInt(contenido.toString().trim());
			if (recetaActual != null) {
				recetaActual = new Receta(recetaActual.getNombre(), recetaActual.getTiempo(), cantidad,
						ingredientesActuales);
			}
		} else if (qName.equalsIgnoreCase("ingrediente")) {
			if (ingredienteActual != null) {
				ingredientesActuales.add(ingredienteActual);
				ingredienteActual = null;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		contenido.append(ch, start, length);
	}


	@Override
	public List<Receta> getDatos() {
		return recetas;
	}

}
