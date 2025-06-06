package app;

import java.util.List;
import java.util.Map;

import datos.InventarioXML;
import datos.RecetaXML;
import modelo.Objeto;
import modelo.Receta;

public class Main {

	public static void main(String[] args) {

		Map<Objeto, Integer> inventario = new InventarioXML("res/inventario.xml").cargar();
		List<Receta> recetas = new RecetaXML("res/recetas.xml").cargar();

		System.out.println("=== INVENTARIO ===");
		for (Map.Entry<Objeto, Integer> o : inventario.entrySet())	
			System.out.println(o);

		System.out.println("\n=== RECETAS ===");
		for (Receta r : recetas) System.out.println(r);

	}
}
