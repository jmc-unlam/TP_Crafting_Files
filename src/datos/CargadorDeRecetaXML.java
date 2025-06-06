package datos;

import java.util.ArrayList;
import java.util.HashMap; // Importar HashMap
import java.util.List;
import java.util.Map;   // Importar Map

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import modelo.Objeto;
import modelo.ObjetoBasico;
import modelo.ObjetoIntermedio;
import modelo.Receta;

public class CargadorDeRecetaXML extends ManejadorXML<Receta> {

    private Receta recetaActual;
    private ObjetoIntermedio objetoProducidoActual; // Para el objeto que produce la receta
    private Map<Objeto, Integer> ingredientesActuales; // Mapa para los ingredientes de la receta actual
    private StringBuilder contenido;

    public CargadorDeRecetaXML(String rutaArchivo) {
        super(rutaArchivo);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        contenido = new StringBuilder(); // Limpiar el contenido al inicio de cada elemento

        if (qName.equalsIgnoreCase("receta")) {
            ingredientesActuales = new HashMap<>(); // Inicializar un nuevo mapa para cada receta
            recetaActual = null; // Resetear la receta actual
            objetoProducidoActual = null; // Resetear el objeto producido
        } else if (qName.equalsIgnoreCase("objetoProducido")) {
            String nombre = attributes.getValue("nombre");
            // El objetoProducido de una receta SIEMPRE es ObjetoIntermedio
            objetoProducidoActual = new ObjetoIntermedio(nombre);
        } else if (qName.equalsIgnoreCase("ingrediente")) {
            String nombre = attributes.getValue("nombre");
            String tipo = attributes.getValue("tipo");
            int cantidad = Integer.parseInt(attributes.getValue("cantidad"));

            Objeto ingrediente;
            if ("basico".equalsIgnoreCase(tipo)) {
                ingrediente = new ObjetoBasico(nombre);
            } else if ("intermedio".equalsIgnoreCase(tipo)) {
                ingrediente = new ObjetoIntermedio(nombre);
            } else {
                throw new SAXException("Tipo de objeto desconocido para ingrediente: " + tipo);
            }
            ingredientesActuales.put(ingrediente, cantidad);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String textoElemento = contenido.toString().trim();

        if (qName.equalsIgnoreCase("cantidadProducida")) {
            int cantidad = Integer.parseInt(textoElemento);
            // No creamos la receta aquí, solo guardamos el valor
            // La receta se crea al final de <receta>
            if(recetaActual == null) { // Si es la primera vez que asignamos
                recetaActual = new Receta(objetoProducidoActual, ingredientesActuales, cantidad, 0); // Tiempo en 0 por ahora
            } else { // Si ya tenemos una instancia parcial, la actualizamos
                recetaActual = new Receta(objetoProducidoActual, ingredientesActuales, cantidad, recetaActual.getTiempoBase());
            }
        } else if (qName.equalsIgnoreCase("tiempoBase")) { // Nombre de la etiqueta actualizado a tiempoBase
            int tiempo = Integer.parseInt(textoElemento);
            if(recetaActual == null) {
                // Esto no debería ocurrir si el XML está bien formado y cantidadProducida viene antes de tiempoBase
                throw new SAXException("Error: tiempoBase encontrado antes que cantidadProducida.");
            } else {
                recetaActual = new Receta(objetoProducidoActual, ingredientesActuales, recetaActual.getCantidadProducida(), tiempo);
            }
        } else if (qName.equalsIgnoreCase("receta")) {
            // Cuando termina la etiqueta <receta>, la receta ya debería estar completa
            if (recetaActual != null) {
                datos.add(recetaActual);
            } else {
                throw new SAXException("Receta incompleta encontrada.");
            }
            // Resetear para la siguiente receta
            recetaActual = null;
            objetoProducidoActual = null;
            ingredientesActuales = null;
        }
        // No necesitamos manejar <nombre>, <tipo>, <ingrediente> con contenido de texto,
        // ya que la información se extrae de los atributos en startElement.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Solo acumular contenido para los elementos que tienen texto interno
        // como <cantidadProducida> y <tiempoBase>
        contenido.append(ch, start, length);
    }
}