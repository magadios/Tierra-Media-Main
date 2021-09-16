package turismoEnLaTierraMedia;

import java.util.LinkedList;
import java.util.List;

public class User {
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private int monedasGastadas;
	private double horasNecesarias;
	private typeOfAttraction atraccionFavorita;
	protected List<Suggestion> sugerenciasCompradas = new LinkedList<Suggestion>();
	protected List<Attraction> atraccionesCompradas = new LinkedList<Attraction>();

	public User(String nombre, int presupuesto, double tiempoDisponible, typeOfAttraction atraccionFavorita) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionFavorita = atraccionFavorita;
	}
	

	public void comprarSugerible(Suggestion s) {
		this.tiempoDisponible -= s.getTiempo();
		this.presupuesto -= s.getCosto();
		this.monedasGastadas += s.getCosto();
		this.horasNecesarias += s.getTiempo();
		s.restarCupo();
		
		sugerenciasCompradas.add(s);
		if (s.esPromocion()) {
			atraccionesCompradas.add(s.getAttractionGratis());
			Promotion promo = (Promotion) s;
			for (Attraction a : promo.getAtracciones()) {
				atraccionesCompradas.add(a);
				
				//System.out.println(a.getNombre() + a.getCupo());
			}
		} else {
			Attraction attraction = (Attraction) s;
			atraccionesCompradas.add(attraction);
			//System.out.println(atraccion.getNombre() + atraccion.getCupo());
			
		}
	}


	public boolean yaCompro(Suggestion sugerencia) {
		for (Suggestion s : atraccionesCompradas) {
			if (sugerencia.esOContiene(s)) 
				return true;
		}
		return false;
	}

	public boolean tieneTiempo(Suggestion atraccion) {
		return (atraccion.getTiempo() <= this.tiempoDisponible);
	}

	public boolean puedeCostear(Suggestion atraccion) {
		return (atraccion.getCosto() <= this.presupuesto);
	}

	public typeOfAttraction getTipoFavorito() {
		return this.atraccionFavorita;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + "\n" + "Tipo de atracci�n preferida: " + atraccionFavorita + "\n"
				+ " Monedas Gastadas: " + monedasGastadas + " monedas de oro" + ",  Tiempo necesario: " + horasNecesarias + " horas" + "\n"
				+ " Presupuesto Disponible: " + presupuesto + " monedas de oro" + ", Tiempo Disponible: " + tiempoDisponible + " horas" + "\n"
				;
	}

	public int getPresupuesto() {
		// TODO Auto-generated method stub
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		// TODO Auto-generated method stub
		return this.tiempoDisponible;
	}

}
