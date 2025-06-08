package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Objeto;
import modelo.ObjetoIntermedio;
import modelo.Receta;

public class RecetaJSON {

	private Objeto objetoProducido;
	private List<ObjetoJSON> ingredientes;
	private int cantidadProducida;
	private int tiempoBase;

	public RecetaJSON() {}

	public RecetaJSON(Objeto objetoProducido, List<ObjetoJSON> ingredientes, int cantidadProducida,
			int tiempoBase) {
		this.objetoProducido = objetoProducido;
		this.ingredientes = ingredientes;
		this.cantidadProducida = cantidadProducida;
		this.tiempoBase = tiempoBase;
	}

	public Objeto getObjetoProducido() {
		return objetoProducido;
	}

	public void setObjetoProducido(Objeto objetoProducido) {
		this.objetoProducido = objetoProducido;
	}

	public List<ObjetoJSON> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<ObjetoJSON> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public int getCantidadProducida() {
		return cantidadProducida;
	}

	public void setCantidadProducida(int cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}

	public int getTiempoBase() {
		return tiempoBase;
	}

	public void setTiempoBase(int tiempoBase) {
		this.tiempoBase = tiempoBase;
	}
	
	public Receta toReceta() {
        Map<Objeto, Integer> ingredientesMap = new HashMap<>();
        if (this.ingredientes != null) {
            for (ObjetoJSON ingJson : this.ingredientes) {
                ingredientesMap.put(ingJson.getObjeto(), ingJson.getCantidad());
            }
        }
        // Aquí necesitas un casting seguro si tu clase de dominio Receta espera ObjetoIntermedio
        // Asegúrate de que objetoProducido sea de hecho un ObjetoIntermedio
        if (this.objetoProducido.esBasico()) {
            // Manejar error o lanzar excepción si el tipo no es el esperado
            throw new IllegalStateException("El objeto producido no es un ObjetoIntermedio como se esperaba en Receta.");
        }
        return new Receta((ObjetoIntermedio) this.objetoProducido, ingredientesMap, this.cantidadProducida, this.tiempoBase);
    }

    // Método estático para convertir Receta de dominio a RecetaJSON (para serialización)
    public static RecetaJSON fromReceta(Receta receta) {
        List<ObjetoJSON> ingredientesJsonList = new ArrayList<>();
        for (Map.Entry<Objeto, Integer> entry : receta.getIngredientes().entrySet()) {
            ingredientesJsonList.add(new ObjetoJSON(entry.getKey(), entry.getValue()));
        }
        return new RecetaJSON(
            receta.getObjetoProducido(),
            ingredientesJsonList,
            receta.getCantidadProducida(),
            receta.getTiempoBase()
        );
    }
}
