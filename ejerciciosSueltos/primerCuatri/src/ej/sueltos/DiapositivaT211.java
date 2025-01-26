package ej.sueltos;

import java.util.stream.Stream;

public class DiapositivaT211 {

	// Simulamos una tupla para la version funcional
		public static record Tupla(String ac, Integer a, Integer b) {
			// Método de factoría
			 public static Tupla of(String ac, Integer a, Integer b) {
				return new Tupla(ac,a,b);
			}
			
			// Métodos first y next
			public static Tupla first(Integer a, Integer b) {
					return Tupla.of("", a,b);
				}
				
			public Tupla next() {
					return of(ac + String.format("%d", a+b), a/2, b-2);
				}
		}
	
	// Recursiva No Final
	static String recursivaNoFinal(Integer a, Integer b) {
		String r = null;
		if (a<5|b<5) {
			r = String.format("(%d)", a*b);
		} else {
			r = String.format("%d", a+b) + recursivaNoFinal(a/2, b-2);
		}
		return r;
	}
	
	// Funcional
	public static String Funcional(Integer a, Integer b) {
		Tupla t = Stream.iterate(Tupla.first(a, b), e -> e.next())
				  .filter(e -> e.a()<5|e.b()<5)
				  .findFirst().get();
		return t.ac() + String.format("(%d)", t.a() * t.b());
	}
	
	public static void main(String[] args) {
		//Test tupla simulada
		Tupla t = Tupla.of("s",2,3);
		System.out.println(t.ac());
		System.out.println(t.a());
		System.out.println(t.b());
		
		
		// Test de la recursivaNoFinal
		System.out.println(recursivaNoFinal(20, 9));
		
		// Test de la Funcional
		System.out.println(Funcional(20,9));
	}
}
