package modelo;

public class Objeto {

	private String nombre;
	private int cantidad;
	private String calidad;

	public Objeto(String nombre, int cantidad, String calidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.calidad = calidad;
	}

	public Objeto(String nombre, int cantidad) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.calidad = "normal";
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getCalidad() {
		return calidad;
	}

	@Override
	public String toString() {
		return "Objeto [nombre=" + nombre + ", cantidad=" + cantidad + ", calidad=" + calidad + "]";
	}
}
