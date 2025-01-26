package ej.convo;

import java.util.stream.Stream;

public class Junio2324 {

	public static String f_rec_no_final(Integer a, Integer b, Integer c) {
		String res = "";
		if (a > 500 || b > 500 || c>500) {
			res = (a*b*c) + "//";
		} else if (b%10 ==0 ||(c%7  == 0 && c<100)) {
			res = " . " + f_rec_no_final(a+3, b+7, c+1);
		} else if (a<100 || b%2==0) {
			res = " ! " + f_rec_no_final(a+6, b+30, c+3);
		} else {
			res = " - " + f_rec_no_final(a*5, 1, c*7);
		}
		return res;
	}
	
	
	public static String f_rec_final(Integer a, Integer b, Integer c) {
		String res = f_rec_final_aux(a, b, c,"");
		return res;
	}
	
	private static String f_rec_final_aux(Integer a, Integer b, Integer c,String acum) {
		String res = "";
		if (a > 500 || b > 500 || c>500) {
			res = acum + Integer.valueOf(a*b*c).toString() + "//";
		} else if (b%10 ==0 ||(c%7  == 0 && c<100)) {
			res = f_rec_final_aux(a+3, b+7, c+1, acum + " . ");
		} else if (a<100 || b%2==0) {
			res = f_rec_final_aux(a+6, b+30, c+3, acum +" ! " );
		} else {
			res = f_rec_final_aux(a*5, 1, c*7, acum +  " - ");
		}
		return res;
	}
	
	public static String f_iter_imper(Integer a, Integer b, Integer c) {
		// Los parametros abc son los estados a actualizar
		String acum = "";
		while(!(a > 500 || b > 500 || c>500)) {
			if (b%10 ==0 ||(c%7  == 0 && c<100)) {
				acum += " . ";
				a = a+3;
				b = b+7;
				c = c+1;
			}
			if (a<100 || b%2==0) {
				acum += " ! ";
				a = a+6;
				b = b+30;
				c = c+3;
			}
			else {
				acum += " - ";
				a = a*5;
				b = 1;
				c = c*7;
			}
			
		}
		return acum + Integer.valueOf(a*b*c).toString() + "//";
	}
	
	// Simulamos una tupla para la version funcional
			public record Tupla(Integer a, Integer b, Integer c, String res) {
				// Método de factoría
				 public static Tupla of(Integer a, Integer b, Integer c, String res) {
					return new Tupla(a,b,c,res);
				}
				
				// Métodos first y next
				public static Tupla first(Integer a, Integer b, Integer c) {
						return Tupla.of(a,b,c,"");
					}
					
				public Tupla next() {
					if (b%10 ==0 || (c%7  == 0 && c<100)) {
						return new Tupla(a+3, b+7, c+1, res);
					}
					if (a<100 || b%2==0) {
						return new Tupla(a+6, b+30, c+3, res);
						
					}
					else {
						return new Tupla(a*5, 1, c*7, res);
					}
				}
			}
	
	public static String f_imper_fun(Integer a, Integer b, Integer c) {
		Stream<Tupla> s = Stream.iterate(Tupla.first(a, b, c),t -> !(t.a() > 500 || t.b() > 500 || t.c()>500) , t -> t.next());
		return "Prueba";
	}
	
	// Implementar la imperativa funcional en casa
	
	public static void main(String[] args) {
		System.out.println(f_rec_no_final(93, 13, 80));
		System.out.println(f_rec_final(93, 13, 80));
		System.out.println(f_iter_imper(93, 13, 80));
	}
}
