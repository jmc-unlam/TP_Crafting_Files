package datos;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import modelo.Receta;

public class RecetaGSON extends ManejadorGSON<List<RecetaJSON>> {

	public RecetaGSON(String rutaArchivo) {
		super(rutaArchivo);
		datos =  new ArrayList<>();
		listType =  new TypeToken<List<RecetaJSON>>() {}.getType();
	}

	public List<Receta> cargar() {
		super.cargarJSON();
		List<Receta> recetas = new ArrayList<>();
        for (RecetaJSON recetaJson : datos) {
        	Receta recetaConvertida = recetaJson.toReceta();
            recetas.add(recetaConvertida);
        }
		return recetas;
	}
	
	public void guardar(List<Receta> recetas) {
		List<RecetaJSON> recetasJSON = new ArrayList<>();
	    for (Receta receta : recetas) {
	    	recetasJSON.add(RecetaJSON.fromReceta(receta));
	    }
	    super.guardarJSON(recetasJSON);
	}

}
