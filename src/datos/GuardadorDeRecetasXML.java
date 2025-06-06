package datos;

import modelo.Objeto;
import modelo.Receta;
import modelo.ObjetoBasico; // Necesario para instanceof
import modelo.ObjetoIntermedio; // Necesario para instanceof

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;
import java.util.Map;

public class GuardadorDeRecetasXML {
    private final String rutaArchivo;

    public GuardadorDeRecetasXML(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(List<Receta> recetas) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Elemento raíz: <recetas>
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("recetas");
            doc.appendChild(rootElement);

            for (Receta receta : recetas) {
                // Elemento <receta>
                Element recetaElement = doc.createElement("receta");
                rootElement.appendChild(recetaElement);

                // Elemento <objetoProducido>
                Element objetoProducidoElement = doc.createElement("objetoProducido");
                objetoProducidoElement.setAttribute("nombre", receta.getObjetoProducido().getNombre());
                // No es necesario el atributo 'tipo' aquí porque siempre es intermedio
                recetaElement.appendChild(objetoProducidoElement);

                // Elemento <ingredientes>
                Element ingredientesElement = doc.createElement("ingredientes");
                recetaElement.appendChild(ingredientesElement);

                for (Map.Entry<Objeto, Integer> entry : receta.getIngredientes().entrySet()) {
                    Objeto ingrediente = entry.getKey();
                    int cantidad = entry.getValue();

                    // Elemento <ingrediente>
                    Element ingredienteElement = doc.createElement("ingrediente");
                    ingredienteElement.setAttribute("nombre", ingrediente.getNombre());
                    ingredienteElement.setAttribute("cantidad", String.valueOf(cantidad));
                    
                    // Determinar el tipo para el atributo 'tipo'
                    if (ingrediente.esBasico()) { // Usamos el método abstracto para saber el tipo
                        ingredienteElement.setAttribute("tipo", "basico");
                    } else {
                        ingredienteElement.setAttribute("tipo", "intermedio");
                    }
                    
                    ingredientesElement.appendChild(ingredienteElement);
                }

                // Elemento <cantidadProducida>
                Element cantidadProducidaElement = doc.createElement("cantidadProducida");
                cantidadProducidaElement.appendChild(doc.createTextNode(String.valueOf(receta.getCantidadProducida())));
                recetaElement.appendChild(cantidadProducidaElement);

                // Elemento <tiempoBase>
                Element tiempoBaseElement = doc.createElement("tiempoBase");
                tiempoBaseElement.appendChild(doc.createTextNode(String.valueOf(receta.getTiempoBase())));
                recetaElement.appendChild(tiempoBaseElement);
            }

            // Escribir el contenido en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // Formato bonito
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));
            transformer.transform(source, result);

            System.out.println("Recetas guardadas exitosamente en: " + rutaArchivo);

        } catch (Exception e) {
            System.err.println("Error al guardar las recetas en XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}