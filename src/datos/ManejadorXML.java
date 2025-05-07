package datos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

public abstract class ManejadorXML<T> extends DefaultHandler {
	private final String rutaArchivo;
	protected List<T> datos; 

	public ManejadorXML(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
		this.datos = new ArrayList<>();
	}

	public List<T> cargar() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(rutaArchivo), this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDatos();
	}
	
	public List<T> getDatos() {
		return this.datos;
	}
}