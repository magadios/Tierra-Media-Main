package turismoEnLaTierraMedia;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {

	private static List<Attraction> atracciones;
	private static List<User> users;
	private static List<Promotion> promociones;
	private static List<Suggestion> suggestions;
	private static List<Suggestion> sugeriblesUsuario;

	public static void main(String[] args) {

		atracciones = new LinkedList<Attraction>();
		users = new LinkedList<User>();
		promociones = new LinkedList<Promotion>();
		suggestions = new LinkedList<Suggestion>();
		sugeriblesUsuario = new LinkedList<Suggestion>();

		atracciones = FileManager.readAttractions(); // Lee el archivo, crea las atracciones y las guarda en
																	// una lista
		suggestions.addAll(atracciones);
		users = FileManager.readUsers(); // Lee el archivo, crea a los usuarios y los guarda en una
															// lista
		promociones = FileManager.readPromotions(); // Lee el archivo, crea las promociones y las guarda en
																	// una lista
		suggestions.addAll(promociones);

		System.out.println("User\n");
		for (User u : users)
			System.out.println(u); // Recorre cada usuario de la lista y los imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		System.out.println("Atracion\n");
		for (Attraction a : atracciones)
			System.out.println(a); // Recorre cada atraccion de la lista y las imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		System.out.println("Promociones\n");
		for (Promotion p : promociones)
			System.out.println(p); // Recorre cada usuario de la lista y los imprime en consola
		System.out.println("------------------------------------------------------------------\n");

		for (User u : users) {

			System.out.println("********************\n");
			System.out.println("User: " + u.getNombre());

			suggestions.sort(new comparator(u));

			for (Suggestion s1 : suggestions) {

				while (u.tieneTiempo(s1) && u.puedeCostear(s1) && !u.yaCompro(s1) && s1.hayCupo()) {
					sugeriblesUsuario.add(s1);

					System.out.println("=============================================================\n");

					System.out.println("Actividades :");

					System.out.println(s1);

					System.out.println("\n");

					System.out.println("Precione 'S' para comprar o 'N' para continuar");

					String opcion = "";

					Scanner entradaEscanerOpcion = new Scanner(System.in); // Creaci�n de un objeto Scanner;

					opcion = entradaEscanerOpcion.nextLine(); // Invocamos un m�todo sobre un objeto Scanner;

					if (opcion.equals("n") || opcion.equals("N")) {
						System.out.println("\n");
						FileManager.escribirItinerarioDeLosUsuarios(users);
						break;
					}
					if (opcion.equals("s") || opcion.equals("S")) {
						u.comprarSugerible(s1);
						System.out.println("Sugerencia");
						System.out.println("Tiempo: " + u.getTiempoDisponible());
						System.out.println("Presupuesto: " + u.getPresupuesto());
						FileManager.escribirItinerarioDeLosUsuarios(users);
						continue;
					}
					if (!opcion.equals("s") || !opcion.equals("n") || opcion.equals("N") || opcion.equals("S")) {
						System.out.println("incorrecto");
						System.out.println("\n");
						continue;
					}

				}

			}
			System.out.println("\n");

		}
		System.out.println("No hay usuarios");
	}
}