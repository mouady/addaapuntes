package ej.convo;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SegundaConvo2324 {

	/*
	 * S: (first, hasta que se cumpla el caso base, nx) 
	 * A:(0, (b,e) -> b+e, no tiene, no tine)
	 * P: tiene 2
	 */
	
	// TUPLA
	private record Tupla(Integer acum,Integer a, Integer b, String c, String d) {
		
		public static Tupla first(Integer a, Integer b, String c, String d) {
			return new Tupla(0,a,b,c,d);
		}
		
		public static Tupla of(Integer acum, Integer a, Integer b, String c, String d) {
			return new Tupla(acum,a,b,c,d);
		}
		
		public Boolean hasNext() {
			Boolean res = true;
			if (a<2||b<3) res = false;
			return res;
		}
		
		public Tupla nx() {
			Tupla res; 
			if(b>=3) {
				res = Tupla.of(acum + a*b,b, a/2, "("+c+")", d + "2");
			} else {
				res = Tupla.of(acum+a,a, b/2, c, "["+d+"]");
			}
		
			return res;
	}
}
	
	
	// RECURSIVA NO FINAL
	
	public static Integer ejercicio1RecursivaNoFinal(Integer a, Integer b, String c, String d) {
		Integer res = 0;
		if (a<2||b<3) {
			res = a*2 + b*3 + d.length();
		} else if(b>=3) {
			res = a*b + ejercicio1RecursivaNoFinal(b, a/2, "("+c+")", d + "2");
		} else {
			res = a + ejercicio1RecursivaNoFinal(a, b/2, c, "["+d+"]");
		}
		return res;
		
	}
	
	// RECURSIVA FINAL
	
		public static Integer ejercicio1RecursivaFinal(Integer a, Integer b, String c, String d) {
			Integer acum = 0;
			return ejercicio1RecursivaFinal(acum, a, b, c, d);
		}
	
		private static Integer ejercicio1RecursivaFinal(Integer acum, Integer a, Integer b, String c, String d) {
			Integer res;
			if (a<2||b<3) {
				res = acum + a*2 + b*3 + d.length();
			} else if(b>=3) {
				res = ejercicio1RecursivaFinal(acum + a*b,b, a/2, "("+c+")", d + "2");
			} else {
				res = ejercicio1RecursivaFinal(acum+a, a, b/2, c, "["+d+"]");
			}
			return res;
			
		}
		
	// FUNCIONAL
		
		public static Integer ejercicio1Funcional(Integer a, Integer b, String c, String d) {
			Stream<Tupla> s = Stream.iterate(Tupla.first(a, b, c, d), tp -> tp.hasNext(), tp-> tp.nx());
			return s.filter(t ->!t.hasNext()).findFirst().get().acum;
		}
	
	public static void main(String[] args) {
		System.out.println(ejercicio1RecursivaNoFinal(6, 7, "KM", "TR"));
		System.out.println(ejercicio1RecursivaFinal(6, 7, "KM", "TR"));
		System.out.println(ejercicio1Funcional(6, 7, "KM", "TR"));

	}

}
