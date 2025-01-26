package c2223;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class EJ1_Julio {

	static final BiPredicate<Integer, Integer> P1 = (a, b) -> a < 5 && b < 5;
	static final BiPredicate<Integer, Integer> P2 = (a, b) -> a < 5 && b >= 5;
	static final BiPredicate<Integer, Integer> P3 = (a, b) -> a >= 5 && b <= 10;
	
	public static Integer recNoFinal(Integer a, Integer b, String c) { 
		//estados:a,b
		 Integer res; 
		 if (P1.test(a,b)) { 
		  res = c.length() + b; 
		 } else if (P2.test(a,b)) { 
		  res = a*b + recNoFinal(a/2, b - 3, "("+ c + ")");  
		 } else if (P3.test(a,b)) { 
		  res = a+b + recNoFinal(a/2, b - 1 ,  c + "a"); 
		 } else { 
		  res = a+b + recNoFinal(a/2, b - 2, c + "b"); 
		 } 
		 return res;
		 
	}
	
	public static Integer recFinal(Integer a, Integer b, String c) {
		//estados:a,b
		Integer acum = 0;
		acum = recFinal(a, b, c, acum);
		return acum;
	}
	

	private static Integer recFinal(Integer a, Integer b, String c, Integer acum) {
		Integer res = acum;
		if (P1.test(a, b)) {
			res = acum + c.length() + b;
		} else if (P2.test(a, b)) {
			res = recFinal(a / 2, b - 3, "(" + c + ")", acum + a * b);
		} else if (P3.test(a, b)) {
			res = recFinal(a / 2, b - 1, c + "a", acum + a + b);
		} else {
			res = recFinal(a / 2, b - 2, c + "b", acum + a + b);
		}
		return res;
	}

	public static Integer iterativa(Integer a, Integer b, String c) {
		//estados:a,b
        Integer acum = 0;
        while (!P1.test(a, b)) {
			if (P2.test(a, b)) {
				acum +=a * b;
				a /= 2;
				b -= 3;
				c = "(" + c + ")";
			} else if (P3.test(a, b)) {
				acum = acum + a + b;
				a /= 2;
				b--;
				c = c + "a";
			} else {
				acum = acum + a + b;
				a /= 2;
				b -= 2;
				c = c + "b";
			}
        }
        acum += c.length() + b;        
        return acum;
    }
        
	public record T(Integer a, Integer b, String c, Integer acum) {
		public static T of(Integer a, Integer b, String c, Integer acum) {
			return new T(a, b, c, acum);
		}
		
		public static T first(Integer a, Integer b, String c) {
			return new T(a, b, c, 0);
		}
		
		public boolean hasFinished() {
			return P1.test(a, b);
		}
		
		public T next() {
			if(this.hasFinished()) return T.of(a, b, c, acum + c.length() + b);
			else if (P2.test(a, b))
				return T.of(a / 2, b - 3, "(" + c + ")", acum + a * b);
			else if (P3.test(a, b))
				return T.of(a / 2, b - 1, c + "a", acum + a + b);
			else
				return T.of(a / 2, b - 2, c + "b", acum + a + b);
		}
	}
	
	public static Integer funcional(Integer a, Integer b, String c) {
		return Stream.iterate(T.first(a, b, c), T::next)
				.filter(T::hasFinished)
				.findFirst()
				.get()
				.next()
				.acum;
	}
	
	public static void main(String[] args) {
		System.out.println(recNoFinal(7, 3, "hola"));
		System.out.println(recFinal(7, 3, "hola"));
		System.out.println(iterativa(7, 3, "hola"));
		System.out.println(funcional(7, 3, "hola"));
	}
}
