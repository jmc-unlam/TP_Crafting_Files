package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.InventarioGSON;
import datos.InventarioXML;
import datos.RecetaGSON;
import datos.RecetaXML;
import modelo.Objeto;
import modelo.Receta;

public class Main {

	public static void main(String[] args) {
		System.out.println("\n=== XML ===");
		
		System.out.println("=== INVENTARIO ===");
		Map<Objeto, Integer> inventario = new InventarioXML("res/inventario.xml").cargar();
		for (Map.Entry<Objeto, Integer> o : inventario.entrySet())	
			System.out.println(o);

		System.out.println("\n=== RECETAS ===");
		List<Receta> recetas = new RecetaXML("res/recetas.xml").cargar();
		for (Receta r : recetas) System.out.println(r);
		
		System.out.println("\n=== GSON ===");
		
		System.out.println("=== INVENTARIO ===");
		Map<Objeto, Integer> objetos = new InventarioGSON("res/inventario.json").cargar();
		for (Map.Entry<Objeto, Integer> o : objetos.entrySet())	
			System.out.println(o);
        new InventarioGSON("res/inventario_salida.json").guardar(objetos);
        
        
        System.out.println("\n=== RECETAS ===");
        List<Receta> recetasJson = new RecetaGSON("res/recetas.json").cargar();
        for (Receta r : recetasJson) System.out.println(r);
        
        new RecetaGSON("res/recetario_salida.json").guardar(recetasJson);
	}
}
