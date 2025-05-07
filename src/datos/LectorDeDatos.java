package datos;

import java.util.List;

public interface LectorDeDatos<T> {

	public abstract List<T> cargar();

}