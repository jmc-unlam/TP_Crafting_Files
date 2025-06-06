package modelo;

import java.util.Map;

public class Receta {
	private ObjetoIntermedio objetoProducido;
  private Map<Objeto, Integer> ingredientes;
  private int cantidadProducida;
  private int tiempoBase;

  public Receta(ObjetoIntermedio objetoProducido, Map<Objeto, Integer> ingredientes, int cantidadProducida, int tiempoBase) {
      this.objetoProducido = objetoProducido;
      this.ingredientes = ingredientes;
      this.cantidadProducida = cantidadProducida;
      this.tiempoBase = tiempoBase;
  }

	public ObjetoIntermedio getObjetoProducido() {
		return nombre;
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
		return "Receta [nombre=" + nombre + ", tiempo=" + tiempo + ", cantidadProducida=" + cantidadProducida
				+ ", ingredientes=" + ingredientes + "]";
	}

}
