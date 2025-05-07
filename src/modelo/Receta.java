package modelo;

import java.util.List;

public class Receta {

	private String nombre;
	private int tiempo;
	private int cantidadProducida;
	private List<Objeto> ingredientes;

	public Receta(String nombre, int tiempo, int cantidadProducida, List<Objeto> ingredientes) {
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.cantidadProducida = cantidadProducida;
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTiempo() {
		return tiempo;
	}

	public int getCantidadProducida() {
		return cantidadProducida;
	}

	public List<Objeto> getIngredientes() {
		return ingredientes;
	}

	@Override
	public String toString() {
		return "Receta [nombre=" + nombre + ", tiempo=" + tiempo + ", cantidadProducida=" + cantidadProducida
				+ ", ingredientes=" + ingredientes + "]";
	}

}
