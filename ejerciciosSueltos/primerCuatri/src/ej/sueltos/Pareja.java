package ej.sueltos;

import java.util.stream.Stream;

public class Pareja {

	static String pareja(Integer a, Integer b) {
		return pareja("", a,b);
	}
	
	static String pareja(String acum, double e, double d) {
		String res = null;
		if (d>e ||e<4 || d<6) {
			res = acum + Double.valueOf(Math.pow(e, d)).toString();
		} else {
			res = pareja(
					acum + String.format("%d", e*d),
					e-d,
					d/2);
		}
		return res;
	}
	
	static String otraPareja(Integer a, Integer b) {
		return otraPareja("", a,b);
	}
	
	static String otraPareja(String acum, Integer a, double b) {
		String res = null;
		if (a>3 || b>6) {
			res = acum + "División" +  Double.valueOf(a/b).toString();
		} else {
			res = otraPareja(
					acum + "Restar" +  Double.valueOf(a-b).toString(),
					a+1,
					b+0.5);
		}
		return res;
	}
	
	// Funcional Otra Pareja
	
	// Simulamos una tupla para la version funcional
			public static record Tupla(String ac, Integer a, Double b) {
				// Método de factoría
				 public static Tupla of(String ac, Integer a, Double b) {
					return new Tupla(ac,a,b);
				}
				
				// Métodos first y next
				public static Tupla first(Integer a, Double b) {
						return Tupla.of("", a,b);
					}
					
				public Tupla next() {
						return of(ac + "Restar" +  Double.valueOf(a-b).toString(), a+1, b+0.5);
					}
			}

			
	static String otraParejaFuncional(Integer a, Double b) {
		Tupla t = Stream.iterate(Tupla.first(a, b), e-> e.next())
				.filter(e -> e.a()>3 || e.b()>6)
				.findFirst()
				.get();
		return t.ac() + "División" +  Double.valueOf(a/b).toString();
		
	}
	
	public static void main(String[] args) {
		System.out.println(otraPareja(2, 5));
		System.out.println(otraParejaFuncional(2, 5.));
	}
}
