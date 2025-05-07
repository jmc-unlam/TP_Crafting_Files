package app;

import java.util.List;

import datos.CargadorDeInventarioXML;
import datos.CargadorDeRecetaXML;
import modelo.Objeto;
import modelo.Receta;

public class Main {

	public static void main(String[] args) {

		List<Objeto> inventario = new CargadorDeInventarioXML("res/inventario.xml").cargar();
		List<Receta> recetas = new CargadorDeRecetaXML("res/recetas.xml").cargar();

		System.out.println("=== INVENTARIO ===");
		for (Objeto o : inventario)	System.out.println(o);

		System.out.println("\n=== RECETAS ===");
		for (Receta r : recetas) System.out.println(r);

	}
}
