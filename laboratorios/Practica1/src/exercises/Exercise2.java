package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;


/*
 * SECUENCIA
 * s = (Tupla.of(a, s), t -> g_t2.test(t), t -> t.next())
 * ACUMULADOR
 * a = ([], ¿(b,t) -> b.add(t -> t.a().toString() + t.s().toString())?, r_b = ls -> ls.reversed(), No hay)
 * PREDICADOS
 * g_t = t -> !(t.a() <= 3 || t.s().length() <= 2); SI EL ULTIMO LO AÑADO LUEGO
 * g_t2 = t -> !(t.a() <= 2 || t.s().length() <= 1); SI EL ULTIMO LO INCLUYO
 * 
 */

public class Exercise2 {
	
	// RECURSIVA NO FINAL
	public static List<String> ejercicio2RecursivoNoFinal(Integer a, String s){
		List<String> res = new ArrayList<String>();
		if (a <=3 || s.length() <=2) res.add(a.toString()+s);
		else {
			res = ejercicio2RecursivoNoFinal(a/2, s.substring(a%s.length()));
			res.add(a.toString()+s);
		}
			
		return res;
	}
	
	// ITERATIVA IMPERATIVA
	public static List<String> ejercicio2Iterativo(Integer a, String s){
		List<String> b = new ArrayList<String>();
		Tupla t = Tupla.of(a,s);
		
		while(p_t.test(t)) {
			b.add(t.a.toString()+t.s);
			t = t.next();
		}
		
		b.add(t.a.toString()+t.s);
		
		return r_b.apply(b);
	}
	
	// RECURSIVA FINAL
	public static List<String> ejercicio2RecursivoFinal(Integer a, String s){
		List<String> acum = new ArrayList<String>();
		return r_b.apply(ejercicio2RecursivoFinal(acum, a, s));
	}
	
	private static List<String> ejercicio2RecursivoFinal(List<String> acum, Integer a, String s) {
		List<String> res = acum;
		if (!(a <= 3 || s.length() <= 2)) {
			acum.add(a.toString()+s);
			res = ejercicio2RecursivoFinal(
					acum,
					a/2,
					s.substring(a%s.length()));
		} else {
			res.add(a.toString()+s);
		}
		return res;
	}
	
	// ITERATIVA FUNCIONAL
	public static List<String> ejercicio2NotacionFuncional(Integer a, String s){
		Tupla tp = Tupla.of(a, s);
		
		Stream<Tupla> seq = Stream.iterate(tp, t -> t.next());
		List<Tupla> ls = new ArrayList<Tupla>(seq.takeWhile(p_t)
												 .toList());
		add_last.apply(ls);
		
		return ls.stream()
				.map(t -> t.a.toString()+t.s)
				.toList()
				.reversed();
		
		
	}
	
	// TUPLA Y PREDICADOS
	
	// Simulamos una tupla para trabajar mas facil 
	// en las versiones imperativa y funcional:
			
	public record Tupla(Integer a, String s) {
		public static Tupla of(Integer a, String s) {
			return new Tupla(a, s);
		}
				
		public Tupla next() {
			return of(a/2, s.substring(a%s.length()));
		}
		
	}
			
	// Predicados y Funcion de retorno
		static Predicate<Tupla> p_t = t -> !(t.a() <= 3 || t.s().length() <= 2);
		static Function<List<Tupla>, Boolean> add_last = ls -> ls.add(ls.getLast().next());
		//static Function<Stream<Tupla>, Stream<Tupla>> add_last2 = s -> s.concat()
		static Function<List<String>, List<String>> r_b = ls -> ls.reversed();
		
	// Creo que la definicion del enunciado en cuanto substrting es incorrecta
	// tambien creo que deberia devolver [11DA,11DA,22DDA,45ADDA]
			
	
}
