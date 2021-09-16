package turismoEnLaTierraMedia;

import java.util.List;

public class PromotionABS extends Promotion {
	private int precioFinal;
	
	public PromotionABS(String nombreDeLaPromo, List<Attraction> atracciones,
						typeOfAttraction tipo, int precioFinal) {
		super(nombreDeLaPromo, atracciones, tipo);
		this.precioFinal = precioFinal;		
	}
	
	@Override
	public int getCosto() {
		return this.precioFinal;
	}
	@Override
	public int getAhorro() {
		int precioReal= 0;
		for(Attraction a:super.atracciones) {
			precioReal += a.getCosto();
		}
		return  precioReal - this.precioFinal;
	}

	@Override
	public String toString() {
		String nombreDeLasAtracciones = ""; 
		for(Attraction a:atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";			
		}
		return "Promocion Abs: " + nombreDeLaPromo + ", Tipo: " + tipo + "\n"
				+ "   Atracciones Incluidas: " + nombreDeLasAtracciones + "\n" 
				+ "   Tiempo Total: " + this.getTiempo()+ " horas " + ", Precio Total: " + 
				this.getCosto() + " monedas" + "\n" + "   Ahorro Comprando La Promo: " + 
				this.getAhorro()+ " monedas" + "\n" ;
	}

	@Override
	public Attraction getAttractionGratis() {
		return null;
	}


}
