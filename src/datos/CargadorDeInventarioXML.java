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

public class CargadorDeInventarioXML extends DefaultHandler implements LectorDeDatos<Objeto> {

	private List<Objeto> inventario = new ArrayList<>();
	private Objeto objetoActual;
	private StringBuilder contenido;

	private final String rutaArchivo;

	public CargadorDeInventarioXML(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	@Override
	public List<Objeto> cargar() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(rutaArchivo), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inventario;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		contenido = new StringBuilder();
		if (qName.equalsIgnoreCase("objeto")) {
			objetoActual = null;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("objeto")) {
			inventario.add(objetoActual);
		} else if (qName.equalsIgnoreCase("nombre")) {
			String nombre = contenido.toString().trim();
			if (objetoActual == null) {
				objetoActual = new Objeto(nombre, 0);
			}
		} else if (qName.equalsIgnoreCase("cantidad")) {
			int cantidad = Integer.parseInt(contenido.toString().trim());
			if (objetoActual != null) {
				objetoActual = new Objeto(objetoActual.getNombre(), cantidad);
			}
		} else if (qName.equalsIgnoreCase("calidad")) {
			String calidad = contenido.toString().trim();
			if (objetoActual != null) {
				objetoActual = new Objeto(objetoActual.getNombre(), objetoActual.getCantidad(), calidad);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		contenido.append(ch, start, length);
	}

}
