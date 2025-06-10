package datos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

public abstract class ManejadorXML<T> extends DefaultHandler {
	private final String rutaArchivo;
	protected T datos;

	public ManejadorXML(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public T cargar() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(rutaArchivo), this);
			
			System.out.println("Recetas leidas desde: ./" + rutaArchivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.datos;
	}
	
	public void guardar(T datosAGuardar) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            // Crear documento XML
            Document doc = docBuilder.newDocument();
            generarDocumento(doc, datosAGuardar);
            
            // Escribir el contenido en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            // Estas son las propiedades clave para el formato
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));
            transformer.transform(source, result);
            
            System.out.println("Recetas guardadas en: ./" + rutaArchivo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	protected abstract void generarDocumento(Document doc, T datos);
}