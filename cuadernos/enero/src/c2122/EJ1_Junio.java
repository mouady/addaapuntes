package c2122;

import java.util.stream.Stream;

import us.lsi.common.TriFunction;
import us.lsi.common.TriPredicate;

public class EJ1_Junio {

	final static TriPredicate<Integer, Integer, Integer> P1 = (a,b,c) -> a<3 || b<3 || c<3;
	final static TriPredicate<Integer, Integer, Integer> P2 = (a,b,c) -> a<5 || (b<a && b<c) || c<5;
	final static TriPredicate<Integer, Integer, Integer> P3 = (a,b,c) -> a%2==0 && b%2==0 && c%2==0;
	final static TriFunction<Integer, Integer, Integer, String> F1 = (a,b,c) -> Integer.valueOf(a+b+c).toString() + "y";
	final static TriFunction<Integer, Integer, Integer, String> F2 = (a,b,c) -> Integer.valueOf(a*b*c).toString() + "z";
	
	public static String solucion(Integer a, Integer b, Integer c) {
	    if (a < 3 || b < 3 || c < 3) {
	        return (a + b + c) + "y";
	    } else if (a < 5 || (b < a && b < c) || c < 5) {
	        return (a * b * c) + "z";
	    } else if (a % 2 == 0 && b % 2 == 0 && c % 2 == 0) {
	        return solucion(a - 1, b / 2, c - 3) + "+";
	    } else {
	        return solucion(a - 4, b / 3, c - 4) + "#";
	    }
	}
	
	public static String recNoFinal(Integer a, Integer b, Integer c) {
		String res = "";
		if(P1.test(a, b, c)) res = F1.apply(a, b, c);
		else if(P2.test(a, b, c)) res = F2.apply(a, b, c);
		else if(P3.test(a, b, c)) res = recNoFinal(a-1, b/2, c-3) + "+";
		else res = recNoFinal(a-4, b/3, c-4) + "#";
		return res;
	}
	
	public static String recFinal(Integer a, Integer b, Integer c) {
		//estados:a,b,c
		String acum = "";
		acum = recFinal(a,b,c,acum);
		return acum;
	}
	
	private static String recFinal(Integer a, Integer b, Integer c, String acum) {
		String res = acum;
		if(P1.test(a, b, c)) res = F1.apply(a, b, c) + acum;
		else if(P2.test(a, b, c)) res = F2.apply(a, b, c) + acum;
		else if(P3.test(a, b, c)) res = recFinal(a-1, b/2, c-3, "+" + acum);
		else res = recFinal(a-4, b/3, c-4, "#" + acum);
		return res;
	}
	
	public static String iterativa(Integer a, Integer b, Integer c) {
		String acum = "";
		while (!P2.test(a, b, c)) {
			if (P3.test(a, b, c)) {
				acum = "+" + acum;
				a = a - 1;
				b = b / 2;
				c = c - 3;
			} else {
				acum = "#" + acum;
				a = a - 4;
				b = b / 3;
				c = c - 4;
			}
		}
		if (P2.test(a, b, c)) acum = F2.apply(a, b, c) + acum;
		else acum = F1.apply(a, b, c) + acum;
		
		return acum;
	}
	
	public record T(Integer a, Integer b, Integer c, String acum) {
		public static T of(Integer a, Integer b, Integer c, String acum) {
			return new T(a, b, c, acum);
		}
		
		public static T first(Integer a, Integer b, Integer c) {
			return new T(a, b, c, "");
		}
		
		public boolean hasFinished() {
			return P1.test(a, b, c) || P2.test(a, b, c);
		}
		
		public T next() {
			if (this.hasFinished()) {
				if (P1.test(a, b, c))
					return T.of(a, b, c, F1.apply(a, b, c) + acum);
				else
					return T.of(a, b, c, F2.apply(a, b, c) + acum);
			}
			else if (P3.test(a, b, c)) return T.of(a-1, b/2, c-3, "+" + acum);
			else return T.of(a-4, b/3, c-4, "#" + acum);
		}
	}
	
	public static String funcional(Integer a, Integer b, Integer c) {
		return Stream.iterate(T.first(a, b, c), T::next)
				.filter(T::hasFinished)
				.findFirst()
				.get()
				.next()
				.acum;
	}

	public static void main(String[] args) {
		System.out.println(solucion(12, 16, 100));
		System.out.println(recNoFinal(12, 16, 100));
		System.out.println(recFinal(12, 16, 100));
		System.out.println(iterativa(12, 16, 100));
		System.out.println(funcional(12, 16, 100));
		
	}
	
	
}
