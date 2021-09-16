package turismoEnLaTierraMedia;

import java.util.List;

public class PromotionPorc extends Promotion {
	
	private double porcentajeDescuento;
	private int precioFinal;
	
	
	public PromotionPorc(String nombreDeLaPromo, List<Attraction> atracciones, typeOfAttraction tipo,
						 double porcentajeDescuento) {
		super(nombreDeLaPromo, atracciones, tipo);
		this.porcentajeDescuento = porcentajeDescuento;
	}


	@Override
	public int getCosto() {
		double precioARedondear = 0;
		precioFinal = 0;
		for (int i = 0; i < super.atracciones.size(); i++) {
			precioFinal += super.atracciones.get(i).getCosto();
			precioARedondear = -((precioFinal*(porcentajeDescuento/100))-precioFinal);			
		}
		return  (int) Math.round(precioARedondear);
	}


	@Override
	public String toString() {
		String nombreDeLasAtracciones = ""; 
		for(Attraction a:atracciones) {
			nombreDeLasAtracciones += a.getNombre() + ", ";
		}
		return "PromocionPorcentual: " + nombreDeLaPromo + ", Tipo: " + tipo + 
				", Porcentaje De Descuento: " + porcentajeDescuento + "%"
				+ "\n" +"   Atracciones Incluidas: " + nombreDeLasAtracciones + "\n"
				+ "   Tiempo Total: " + this.getTiempo() + " horas" + 
				", Precio Final: " + this.getCosto() + " monedas" + "\n" + 
				"   Ahorro Comprando La Promo: " + this.getAhorro() +" monedas" + "\n" ;
				
	}


	@Override
	public Attraction getAttractionGratis() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getAhorro() {
		int precioReal= 0;
		for(Attraction a:super.atracciones) {
		precioReal += a.getCosto();
		} 
		return  (precioReal - this.getCosto());
	}

}
