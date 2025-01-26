package c2223;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class EJ1_Noviembre {

	static final BiPredicate<Integer, Integer> P = (a,b) -> a<2 || b<=3;
	
	public static List<Integer> recNoFinal(Integer a, Integer b) {
		//estados: a,b
		List<Integer> acum = PrecNoFinal(a,b);
		return acum;
	}

	private static List<Integer> PrecNoFinal(Integer a, Integer b) {
		if (P.test(a, b)) {
			List<Integer> acum = new ArrayList<Integer>();
			acum.add(a*b);
			return acum;
		} else {
			List<Integer> acum = PrecNoFinal(a-1,b/2);
			acum.add(a+b);
			return acum;
		}
		
	}
	
	public static List<Integer> recFinal(Integer a, Integer b) {
		//estados: a,b
		List<Integer> acum = new ArrayList<Integer>();
		acum = recFinal(a,b,acum);
		return acum; //.reversed si añadimos al principio
	}
	
	private static List<Integer> recFinal(Integer a, Integer b, List<Integer> acum) {
		List<Integer> res = acum;
		if (P.test(a, b)) res.add(a*b);
		else {
			//podemos añadir al principio si luego hacemos un reversed
			res = recFinal(a-1, b/2, acum);
			acum.add(a+b);
		}
		return res;
	}
	
	public static List<Integer> iterativa(Integer a, Integer b) {
		List<Integer> acum = new ArrayList<Integer>();
		while (!P.test(a, b)) {
			acum.add(a + b);
			a--;
			b = b / 2;
		}
		acum.add(a * b);
		return acum.reversed();
	}
	
	public record T(Integer a, Integer b, List<Integer> acum) { 
		public static T of(Integer a, Integer b, List<Integer> acum) {
			return new T(a, b, acum);
		}
		
		public static T first(Integer a, Integer b) {
			return T.of(a, b, new ArrayList<Integer>());
		}
		
		public boolean hasFinished() {
			return P.test(a, b);
		}
		
		public T next() {
			if (hasFinished()) {
				acum.add(a * b);
				return this;
			} else {
				acum.add(a + b);
				return T.of(a - 1, b / 2, acum);
			}
		}
		
	}
	
	public static List<Integer> funcional(Integer a, Integer b) {
		return Stream.iterate(T.first(a, b), T::next)
				.filter(T::hasFinished)
				.findFirst()
				.get()
				.next()
				.acum
				.reversed();
	}	

	public static void main(String[] args) {
		System.out.println(recNoFinal(23, 34));
		System.out.println(recFinal(23, 34));
		System.out.println(iterativa(23, 34));
		System.out.println(funcional(23, 34));
	}
}
