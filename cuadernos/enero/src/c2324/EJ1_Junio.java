package c2324;

import java.util.stream.Stream;

public class EJ1_Junio {

	public static String f_rec_n_final_aux(Integer a, Integer b, Integer c) { 
		String res = ""; 
		if ((a > 500 || b > 500 || c > 500)) { 
		res = (a * b * c) + " //"; 
		} else if (b % 10 == 0 || (c % 7 == 0 && c<100)) { 
		res = " . " + f_rec_n_final_aux(a + 3, b + 7, c + 1); 
		} else if (a < 100 || b % 2 == 0 ) { 
		res = " ! " + f_rec_n_final_aux(a + 6, b + 30, c + 3); 
		} else { 
		res = " - " + f_rec_n_final_aux(a * 5, 1, c * 7); 
		} 
		return res; 
		} 
	
	public static String f_rec_final(Integer a, Integer b, Integer c) {
		// el estado son los datos de entrada en si
		String acum = "";
		acum = f_rec_final(a, b, c, acum);
		return acum;
	}
	
	private static String f_rec_final(Integer a, Integer b, Integer c, String acum) {
		String res = acum;
		if ((a > 500 || b > 500 || c > 500)) {
            res = acum + (a * b * c) + " //";
        } else if (b % 10 == 0 || (c % 7 == 0 && c < 100)) {
            res = f_rec_final(a + 3, b + 7, c + 1, acum + " . ");
        } else if (a < 100 || b % 2 == 0) {
            res = f_rec_final(a + 6, b + 30, c + 3, acum + " ! ");
        } else {
            res = f_rec_final(a * 5, 1, c * 7, acum + " - ");
        }
		return res;
		}
	
	public static String iterativa(Integer a, Integer b, Integer c) {
			String acum = "";
			while (!(a > 500 || b > 500 || c > 500)) {
				if (b % 10 == 0 || (c % 7 == 0 && c < 100)) {
					acum += " . ";
					a = a + 3;
					b = b + 7;
					c = c + 1;
				} else if (a < 100 || b % 2 == 0) {
					acum += " ! ";					
					a = a + 6;
					b = b + 30;
					c = c + 3;
				} else {
					acum += " - ";
					a = a * 5;
					b = 1;
					c = c * 7;
				}
			}
			acum += (a * b * c) + " //";
			return acum;
		}
	
	record T(Integer a, Integer b, Integer c, String acum) {
		public static T of(Integer a, Integer b, Integer c, String acum) {
			return new T(a, b, c, acum);
		}
		
		public static T first(Integer a, Integer b, Integer c) {
			return T.of(a, b, c, "");
		}
		
		public boolean hasNext() {
			return !(a > 500 || b > 500 || c > 500);
		}
		
		public T next() {
			if (b % 10 == 0 || (c % 7 == 0 && c < 100)) {
				return T.of(a + 3, b + 7, c + 1, acum + " . ");
			} else if (a < 100 || b % 2 == 0) {
				return T.of(a + 6, b + 30, c + 3, acum + " ! ");
			} else {
				return T.of(a * 5, 1, c * 7, acum + " - ");
			}
		}
	}
	
	public static String funcional(Integer a, Integer b, Integer c) {
		T res = Stream.iterate(T.first(a, b, c), t -> t.next())
				.filter(t -> !t.hasNext())
				.findFirst()
				.get();
		
		return res.acum + (res.a * res.b * res.c) + " //";
	}
	
		
	
	public static void main(String[] args) {
		System.out.println(f_rec_n_final_aux(34, 54, 67));
		System.out.println(f_rec_final(34, 54, 67));
		System.out.println(iterativa(34, 54, 67));
		System.out.println(funcional(34, 54, 67));
	}
}
