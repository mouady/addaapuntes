package c2223;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class EJ1_PrimerParcial {

	static final BiPredicate<Integer, Integer> P1 = (a,b) -> a < 3 && b < 3;
	static final BiPredicate<Integer, Integer> P2 = (a,b) -> a < 3 && b <= 3;
	static final BiPredicate<Integer, Integer> P3 = (a,b) -> a >= 3 && b < 3;
	
	public static String noFinal(Integer a, Integer b, Integer c, String d) { 
			 String res; 
			 if (P1.test(a, b)) { 
			  res = c.toString(); 
			 } else if (P2.test(a, b)) { 
			  res = "~" + noFinal(a - 1, b - 1, c + 4, d); 
			 } else if (P3.test(a,b))  { 
			  res = "-" + c + "-" + noFinal(a - 2, b - 1, c, d + "x"); 
			 } else { 
			  res = "/" + d + "/" + noFinal(a - 1, b - 2, c + 1, d); 
			 } 
			 return res; 
			}
	
	public static String Final(Integer a, Integer b, Integer c, String d) { 
		// estados: a,b
		String acum = "";
		acum = Final(a,b,c,d,acum); 
		return acum; 
	}
	
	private static String Final(Integer a, Integer b, Integer c, String d, String acum) { 
		String r = acum;
		if (P1.test(a, b)) r = acum + c.toString();
		else if (P2.test(a, b)) r = Final(a - 1, b - 1, c + 4, d, acum + "~");
		else if (P3.test(a,b))  r = Final(a - 2, b - 1, c, d + "x", acum + "-" + c.toString() + "-");
		else r = Final(a - 1, b - 2, c + 1, d, acum + "/" + d.toString() + "/");
		
		return r; 
	}
	
	public static String iterativa(Integer a, Integer b, Integer c, String d) {
		String acum = "";
		while (!P1.test(a, b)) {
			if (P2.test(a, b)) {
                acum += "~";
                a--;
                b--;
                c += 4;
            }
			
			else if (P3.test(a, b)) {
				acum += "-" + c + "-";
				a -= 2;
				b--;
				d += "x";
			} 
			
			else {
				acum += "/" + d + "/";
				a--;
				b -= 2;
				c++;
			}
		}
		acum += c;
		return acum;
	}
	
	public record T(Integer a, Integer b, Integer c, String d, String acum) {
		public static T of(Integer a, Integer b, Integer c, String d, String acum) {
			return new T(a, b, c, d, acum);
		}

		public static T first(Integer a, Integer b, Integer c, String d) {
			return T.of(a, b, c, d, "");
		}
		
		public boolean hasFinished() {
			return P1.test(a, b);
		}
		
		public T next() {
			
			if (this.hasFinished()) {
				return T.of(a, b, c, d, acum + c.toString());
			}
			
			else if (P2.test(a, b)) {
				return T.of(a - 1, b - 1, c + 4, d, acum + "~");
			}

			else if (P3.test(a, b)) {
				return T.of(a - 2, b - 1, c, d + "x", acum + "-" + c.toString() + "-");
			}

			return T.of(a - 1, b - 2, c + 1, d, acum + "/" + d + "/");
		}
		
	}
	
	public static String funcional(Integer a, Integer b, Integer c, String d) {
		return Stream.iterate(T.first(a, b, c, d), t -> t.next())
				.filter(T::hasFinished)
				.findFirst()
				.get()
				.next()
				.acum;
	}
	
	public static void main(String[] args) {
		System.err.println(noFinal(10, 6, 12, "ab"));
		System.out.println(Final(10, 6, 12, "ab"));
		System.out.println(iterativa(10, 6, 12, "ab"));
		System.out.println(funcional(10, 6, 12, "ab"));
	}
}
