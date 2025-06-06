package modelo;

public class ObjetoIntermedio extends Objeto {
  public ObjetoIntermedio(String nombre) {
      super(nombre);
  }
  
  @Override
  public boolean esBasico() {
      return false;
  }
}
