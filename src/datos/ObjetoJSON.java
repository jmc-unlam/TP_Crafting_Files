package datos;

import com.google.gson.annotations.SerializedName;

import modelo.Objeto;

public class ObjetoJSON {
	@SerializedName("objeto")
    private Objeto objeto;
	private int cantidad;
	
    public ObjetoJSON() {}
    
	public ObjetoJSON(Objeto objeto, int cantidad) {
		this.objeto = objeto;
		this.cantidad = cantidad;
	}
	public Objeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Objeto toObjeto() {
		return objeto;
	}
}
