package turismoEnLaTierraMedia;

import java.util.List;

public class PromotionAxB extends Promotion {
	// la atraccion gratis la pasamos por archivo (la ultima atraccion)

	private int precioFinal;
	
	private Attraction attractionGratis;

	public PromotionAxB(String nombreDeLaPromo, Attraction attractionGratis, List<Attraction> atracciones,
						typeOfAttraction tipo) {
		super(nombreDeLaPromo, atracciones, tipo);
		
		
		this.attractionGratis = attractionGratis;
		
	}

	@Override
	public int getCosto() {
		precioFinal = 0;
		
		for (int i = 0; i < super.atracciones.size(); i++) {
			if (super.atracciones.get(i).getNombre() != attractionGratis.getNombre())
				precioFinal += super.atracciones.get(i).getCosto();
		}
		return this.precioFinal;
	}

	@Override
	public String toString() {
		String nombreDeLasAtracciones = "";
		for (Attraction a : atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionAxB: " + nombreDeLaPromo + ", Tipo : " + tipo + "\n " + 
		"  Atracciones Incluidas: " + this.attractionGratis.getNombre()+ ", " +
		nombreDeLasAtracciones + "\n " + "  Tiempo Total: " + this.getTiempo() + 
		" horas" + ", Precio Total: " + this.getCosto() + " monedas" + "\n" + 
		"   Ahorro Comprando La Promo: " + this.getAhorro() + " monedas" + "\n";
	}
	
	@Override
	public double getTiempo() {
		tiempoTotal = 0;
		for (int i = 0; i < this.atracciones.size(); i++) {
			tiempoTotal += this.atracciones.get(i).getTiempo();
		}
		tiempoTotal += attractionGratis.getTiempo();
		return tiempoTotal;
	}
	
	public Attraction getAttractionGratis() {
		return this.attractionGratis;
	}
	
	@Override
	public boolean esOContiene(Suggestion s) {
		if(this.attractionGratis.equals(s)) {
			return true;
		}
		else for (Attraction a : super.atracciones) {
			if (a.equals(s))
				return true;
		}
		return false;
	}

	@Override
	public int getAhorro() {
		return this.attractionGratis.getCosto();
	}
	
	@Override
	public void restarCupo() {
		super.restarCupo();
		this.attractionGratis.restarCupo();
		
		}
	
	@Override
	public boolean hayCupo() {
		for (Attraction a : this.atracciones) {
			if (!a.hayCupo() || !this.attractionGratis.hayCupo())
				return false;
		}		
		return true;
	}

}
