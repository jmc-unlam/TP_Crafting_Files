package modelo;

import java.util.Map;
import java.util.stream.Collectors;

public class Receta {
	private ObjetoIntermedio objetoProducido;
	private Map<Objeto, Integer> ingredientes;
	private int cantidadProducida;
	private int tiempoBase;

	public Receta(ObjetoIntermedio objetoProducido, Map<Objeto, Integer> ingredientes, int cantidadProducida,
			int tiempoBase) {
		this.objetoProducido = objetoProducido;
		this.ingredientes = ingredientes;
		this.cantidadProducida = cantidadProducida;
		this.tiempoBase = tiempoBase;
	}

	public ObjetoIntermedio getObjetoProducido() {
		return objetoProducido;
	}

	public int getTiempoBase() {
		return tiempoBase;
	}

	public int getCantidadProducida() {
		return cantidadProducida;
	}

	public Map<Objeto, Integer> getIngredientes() {
		return ingredientes;
	}
	
	@Override
    public String toString() {
        String ingredientesStr = ingredientes.entrySet().stream()
            .map(e -> e.getKey() + " x" + e.getValue())
            .collect(Collectors.joining(", "));
        
        return "Receta{" +
            "Produce: " + objetoProducido + " x" + cantidadProducida +
            ", Ingredientes: [" + ingredientesStr + "]" +
            ", Tiempo: " + tiempoBase + "}" ;
    }

}
