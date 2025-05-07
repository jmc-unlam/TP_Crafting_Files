package modelo;

public class Ingrediente extends Objeto {

	public Ingrediente(String nombre, int cantidad) {
		super(nombre,cantidad);
	}

	@Override
	public String toString() {
		return "Ingrediente [" + super.toString() + "]";
	}

}
