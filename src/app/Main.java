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
		Map<Objeto, Integer> inventario = new InventarioXML("res/inventario2.xml").cargar();
		for (Map.Entry<Objeto, Integer> o : inventario.entrySet())	
			System.out.println(o);
		new InventarioXML("res/inventario2_salida.xml").guardar(inventario);

		System.out.println("\n=== RECETAS ===");
		List<Receta> recetas = new RecetaXML("res/recetas2.xml").cargar();
		for (Receta r : recetas) System.out.println(r);
		new RecetaXML("res/recetas2_salida.xml").guardar(recetas);
		
		
		System.out.println("\n=== GSON ===");
		
		System.out.println("=== INVENTARIO ===");
		Map<Objeto, Integer> objetos = new InventarioGSON("res/inventario.json").cargar();
		for (Map.Entry<Objeto, Integer> o : objetos.entrySet())	
			System.out.println(o);
        new InventarioGSON("res/inventario_salida.json").guardar(objetos);
        
        
        System.out.println("\n=== RECETAS ===");
        List<Receta> recetasJson = new RecetaGSON("res/recetas.json").cargar();
        for (Receta r : recetasJson) System.out.println(r);
        
        new RecetaGSON("res/recetas_salida.json").guardar(recetasJson);
	}
}
