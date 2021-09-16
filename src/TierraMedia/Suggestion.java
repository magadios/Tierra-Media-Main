package turismoEnLaTierraMedia;

import java.util.LinkedList;
import java.util.List;

public interface Suggestion {
	
	List <Suggestion> atraccionComprada = new LinkedList<Suggestion>();

		
	public int getCosto();
	
	public typeOfAttraction getTipo();
	
	public double getTiempo();
	
	public boolean hayCupo();
	
	public boolean esPromocion();

	public boolean esOContiene(Suggestion sugerencia);

	public void restarCupo();
	
	public Attraction getAttractionGratis();
	@Override
	public String toString();



}
