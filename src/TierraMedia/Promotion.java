package turismoEnLaTierraMedia;

import java.util.List;

public abstract class Promotion implements Suggestion {
	protected List<Attraction> atracciones;
	protected typeOfAttraction tipo;
	protected double tiempoTotal;
	protected String nombreDeLaPromo;
	
	

	public Promotion(String nombreDeLaPromo, List<Attraction> attraction, typeOfAttraction tipo) {
		this.nombreDeLaPromo = nombreDeLaPromo;
		this.atracciones = attraction;
		this.tipo = tipo;
	}

	
	
	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public boolean hayCupo() {
		for (Attraction a : this.atracciones) {
			if (!a.hayCupo())
				return false;
		}		
		return true;
	}
	
	@Override
	public void restarCupo() {
		for (Attraction a : this.atracciones) {
			a.restarCupo();
		}
	}

	@Override
	public double getTiempo() {
		tiempoTotal = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			tiempoTotal += this.atracciones.get(i).getTiempo();
		}
		return tiempoTotal;
	}

	@Override
	public typeOfAttraction getTipo() {
		return this.tipo;
	}

	@Override
	public boolean esOContiene(Suggestion s) {
		for (Attraction a : this.atracciones) {
			if (a.equals(s))
				return true;
		}
		return false;
	}

	public List<Attraction> getAtracciones() {
		return atracciones;
	}
	
	public abstract int getAhorro();
	
}
