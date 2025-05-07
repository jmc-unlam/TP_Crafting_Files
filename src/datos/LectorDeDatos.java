package datos;

import java.util.List;

public interface LectorDeDatos<T> {

	List<T> cargar();
	
}