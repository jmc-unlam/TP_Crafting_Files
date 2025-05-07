package datos;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;

import modelo.Objeto;

public abstract class ManejadorXML<T> extends DefaultHandler {
	protected final String rutaArchivo;

	public ManejadorXML(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
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
	
	public abstract List<T> getDatos();

}