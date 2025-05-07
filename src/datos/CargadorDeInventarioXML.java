package datos;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import modelo.Objeto;

public class CargadorDeInventarioXML extends ManejadorXML<Objeto> {

	List<Objeto> inventario = new ArrayList<>();
	private Objeto objetoActual;
	private StringBuilder contenido;


	public CargadorDeInventarioXML(String rutaArchivo) {
		super(rutaArchivo);
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

	@Override
	public List<Objeto> getDatos() {
		return inventario;
	}

}
