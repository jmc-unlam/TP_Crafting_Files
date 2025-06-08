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
        StringBuilder sb = new StringBuilder();
        sb.append("Objeto producido: ").append(objetoProducido).append("\n");
        sb.append("Cantidad producida: ").append(cantidadProducida).append("\n");
        sb.append("Tiempo de crafteo: ").append(tiempoBase).append("\n");
        sb.append("Ingredientes:\n");

        for (Map.Entry<Objeto, Integer> entry : ingredientes.entrySet()) {
            Objeto obj = entry.getKey();
            int cantidad = entry.getValue();
            sb.append("    - ").append(obj.getNombre()).append(" x ").append(cantidad).append("\n");
        }

        return sb.toString();
    }

}
