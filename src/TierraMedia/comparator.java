package turismoEnLaTierraMedia;

import java.util.Comparator;

public class comparator implements Comparator<Suggestion> {
	private User user;

	public comparator(User user) {
		this.user = user;

	}

	@Override
	public int compare(Suggestion s1, Suggestion s2) {
		if (user.getTipoFavorito() == s1.getTipo() // Por tipo
				&& user.getTipoFavorito() != s2.getTipo())
			return -1;
		else if (user.getTipoFavorito() != s1.getTipo() && user.getTipoFavorito() == s2.getTipo())
			return 1;

		else if (s1.esPromocion() && !s2.esPromocion()) // Por tipo de sugerible
			return -1;

		else if (!s1.esPromocion() && s2.esPromocion())
			return 1;

		else if (s1.getCosto() > s2.getCosto()) // Por precio
			return -1;

		else if (s1.getCosto() < s2.getCosto())
			return 1;

		else // Por tiempo
			return Double.compare(s2.getTiempo(), s1.getTiempo());
	}

}
