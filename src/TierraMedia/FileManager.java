package turismoEnLaTierraMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
	
	private static List<Attraction> promoAttraction;

	protected static List<Attraction> Attractions = new LinkedList<Attraction>();

	public static List<Attraction> readAttractions() {
		File f = new File("atracciones.txt");
		Scanner sc;
		String[] line;

		try {
			sc = new Scanner(f);

			while (sc.hasNextLine()) {
				line = sc.nextLine().split(",");

				Attractions.add(new Attraction(line[0],Integer.valueOf(line[1]),Double.parseDouble(line[2]),Integer.valueOf(line[3]), typeOfAttraction.valueOf(line[4])));
				line = null;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
		}

		promoAttraction = Attractions;
		return Attractions;
	}

	public static List<User> readUsers() {
		File f = new File("usuarios.txt");
		Scanner sc;
		String[] line;
		List<User> users = new LinkedList<User>();

		try {
			sc = new Scanner(f);

			while (sc.hasNextLine()) {
				line = sc.nextLine().split(",");

				users.add(new User(line[0], // nombre
						Integer.valueOf(line[1]), // costo
						Double.parseDouble(line[2]), // tiempo
						typeOfAttraction.valueOf(line[3]) // tipo
				));
				line = null;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
		}

		return users;
	}

	public static List<Promotion> readPromotions() {
		File f = new File("testPromo.txt");
		Scanner sc;
		String[] line;
		List<Promotion> promociones = new LinkedList<Promotion>();
		List<Attraction> atraccionesPromo = new LinkedList<Attraction>();


		try {
			sc = new Scanner(f);

			while (sc.hasNextLine()) {
				line = sc.nextLine().split(",");
				for (int i = 4; i < line.length; i++) {
					for (Attraction a : Attractions) {
						if (a.getNombre().equals(line[i])) {
							atraccionesPromo.add(a);
							break;
						}
					}
				}

				if (line[0].equals("Porcentual")) {
					promociones.add(new PromotionPorc(line[1], // nombre
							atraccionesPromo, // atracciones
							typeOfAttraction.valueOf(line[2]), // tipo de atraccion
							Integer.valueOf(line[3])// porcentaje de descuento
					));
				}
				if (line[0].equals("Absoluta")) {
					promociones.add(new PromotionABS(line[1], // nombre
							atraccionesPromo, // atracciones
							typeOfAttraction.valueOf(line[2]), // tipo de atraccion
							Integer.valueOf(line[3])// precio final
					));
				}
				if (line[0].equals("AxB")) {
					Attraction attractionGratis = null;
					for (Attraction a : Attractions) {
						if (a.getNombre().equals(line[3])) {
							attractionGratis = a;
							break;
						}

					}
					promociones.add(new PromotionAxB(line[1], attractionGratis,
							atraccionesPromo,
							typeOfAttraction.valueOf(line[2])
					));
				}

				line = null;
				atraccionesPromo = new LinkedList<Attraction>();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println(e.getMessage());
		}

		return promociones;
	}
	public static void escribirItinerarioDeLosUsuarios(List<User> users) {
		for (User a : users) {
			File f = new File(a.getNombre() + "Itinerario.txt");
			PrintWriter pw;

			try {
				pw = new PrintWriter(f);

				pw.write(a.toString() + "\n");

				pw.write("Compro:" + "\n");

				int i = 1;
				for (Suggestion s : a.sugerenciasCompradas) {
					pw.write("\n" + i + ") " + s.toString());
					i++;
				}

				pw.close();
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
