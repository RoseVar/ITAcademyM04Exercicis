import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class M4_Fase3 {

	public static void main(String[] args) {
		//Exercici:
		//L’exercici consisteix a mostrar per consola una carta d’un restaurant 
		//on afegirem diferents plats i després escollirem que volem per menjar. 
		//Un cop fet això s’haurà de calcular el preu del menjar el programa ens dirà amb quins bitllets hem de pagar.
		
		//Fase 1 + 2
		//variables
		int bitllet5, bitllet10, bitllet20, bitllet50, bitllet100, bitllet200;
		double preu_total=0.0;
		List <String> menuPlats= new ArrayList<String>();
		List <Double> menuPreus= new ArrayList<Double>();
		
		//Amb un bucle for haurem d’omplir els dos arrays anteriorment creats. 
		//Afegirem el nom del plat i després el preu. Pots fer us de diccionaris de dades(Java HasMap)
		Map<String, Double> menu = new HashMap<String, Double>(); 
		boolean control= true;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Entra un plato para meterlo en el menú (o \"Exit\" para salir)");
			String plato= scan.nextLine();
			if ("Exit".equalsIgnoreCase(plato)) {
				control = false;
			} else if (plato==null) {
				System.out.println("Nombre de plato no correcto. Prueba de nuevo");
			} else {
				boolean preuCorrecte= true;
				do {
					System.out.println("Entra el precio para '"+plato+"' (en números y sin moneda)");
					try{
						Double precio= scan.nextDouble();
						preuCorrecte= false;
						menu.put(plato,  precio);
						scan.nextLine();						
					} catch (InputMismatchException e) {
						System.out.println("El formato del precio no es correcto");
						scan.nextLine();	
					}
				} while (preuCorrecte);
			}
		} while (control);
		
		for (String nombrePlato : menu.keySet()) {
			menuPlats.add(nombrePlato);
		}
		
		for (String nombrePlato :menuPlats) {
			menuPreus.add(menu.get(nombrePlato));
		}
		
		//Un cop plens els dos arrays haurem de mostrar-los i preguntar que es vol per menjar, 
		//guardarem la informació en una List fent servir un bucle while.
		//Haurem de preguntar si es vol seguir demanant menjar. Podeu fer servir el sistema (1:Si / 0:No), 
		//per tant haureu de crear un altre variable int per guardar la informació.
		
		System.out.println("Este es el menú:");
		for (int i= 0; i<menuPlats.size(); i++) {
			System.out.println(i+1 +" - " + menuPlats.get(i));
		}
		
		System.out.println("¿Qué quieres de comer (escribe el número)?");
		boolean finComanda= true;
		List <Integer> comandaEntera = new ArrayList<Integer>();
		int comanda;
		
		do {
			try {
				comanda = scan.nextInt();
				if (comanda==0) {
					finComanda= false;
				} else if (comanda>0 & comanda<menu.size()+1) {
					comandaEntera.add(comanda-1);
					System.out.println("¿Quieres algo más? (si ya estás, elige 0)");
				} else {
					System.out.println("El número no corresponde a ningún plato. Son del 1 al " + menu.size() );
					System.out.println("¿Quieres algo más? (si ya estás, elige 0)");
				}

			} catch (InputMismatchException e) {
				System.out.println("Tu respuesta no tiene un formato correcto: debe ser un número");
				System.out.println("¿Quieres algo más? (si ya estás, elige 0)");
			}
		} while (finComanda);

		//--- FASE 3	
		//Un cop hem acabat de demanar el menjar, haurem de comparar la llista amb l’array que hem fet al principi. 
		//En el cas que la informació que hem introduït a la List coincideixi amb la del array, 
		//haurem de sumar el preu del producte demanat; en el cas contrari haurem de mostrar un 
		//missatge que digui que el producte que hem demanat no existeix.
		System.out.println("Has comido: ");
		List<String> comandaPlats= new ArrayList<String>();
		for (int codigo: comandaEntera) {
			System.out.println(menuPlats.get(codigo));
			comandaPlats.add(menuPlats.get(codigo));
		}

		for (int codigo: comandaEntera) {		
			preu_total= preu_total + menuPreus.get(codigo);
		}
		System.out.println("Y el precio total es: " + preu_total);
	}

}
