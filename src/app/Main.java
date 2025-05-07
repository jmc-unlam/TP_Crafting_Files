package app;
import java.util.List;

import datos.CargadorDeInventarioXML;
import datos.CargadorDeRecetaXML;
import modelo.Objeto;
import modelo.Receta;

public class Main {

	public static void main(String[] args) {
		CargadorDeInventarioXML datosInventario = new CargadorDeInventarioXML("res/inventario.xml");
        CargadorDeRecetaXML datosReceta = new CargadorDeRecetaXML("res/recetas.xml");
        
        List<Objeto> inventario = datosInventario.cargar();
        List<Receta> recetas  = datosReceta.cargar();
        
        System.out.println("=== INVENTARIO ===");
        for (Objeto o : inventario) System.out.println(o);        

        System.out.println("\n=== RECETAS ===");
        for (Receta r : recetas) System.out.println(r);

	}
}
