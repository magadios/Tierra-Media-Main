package turismoEnLaTierraMedia;

import java.util.Objects;

public class Attraction implements Suggestion {
	private int costo;
	protected String nombre;
	private typeOfAttraction tipo;
	private double tiempo;
	private int cupo;

	public Attraction(String nombre, int costo, double tiempo, int cupo, typeOfAttraction tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}
		public Attraction(String nombre) {
		this.nombre = nombre;
		
	}
	@Override
	public boolean hayCupo() {
		return this.cupo > 0;
	}

	@Override
	public void restarCupo() {
		this.cupo--;
	}

	@Override
	public double getTiempo() {
		return this.tiempo;
	}

	@Override
	public int getCosto() {
		return this.costo;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public typeOfAttraction getTipo() {
		return this.tipo;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public String toString() {
		return "Atraccion " + nombre + ", Tiempo: " + tiempo + ", Costo: " +
	            costo + ", Cupo: " + cupo +  ", Tipo: " + tipo + "\n";
	}

	@Override
	public boolean esOContiene(Suggestion sugerencia) {
		return this.equals(sugerencia);
	}

	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tiempo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attraction other = (Attraction) obj;
		return costo == other.costo && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempo) == Double.doubleToLongBits(other.tiempo) && tipo == other.tipo;
	}
	@Override
	public Attraction getAttractionGratis() {
		return null;
	}

}
