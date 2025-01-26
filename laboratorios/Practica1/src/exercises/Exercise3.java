package exercises;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercise3 {
	
	// RECURSIVA MULTIPLE SIN MEMORIA
	public static Set<Integer> ejercicio3RecursivoSinMemoria(Integer a, Integer b, Integer c) {
		Set<Integer> s = new HashSet<Integer>();
		Tupla t = Tupla.of(a,b,c);
		
		if (b_t.test(t)) {
			s.addAll(sb_t.apply(t)); 
		} else {
			Set<Integer> s1 = ejercicio3RecursivoSinMemoria(t.a()-5, t.b()/4, t.c()-2);
			Set<Integer> s2 = ejercicio3RecursivoSinMemoria(t.a()/3, t.b()-3, t.c()/2);
			s.addAll(s1);
			s.addAll(s2);
		}
		
		return s; 
	}
	
	// RECURSIVA MULTIPLE CON MEMORIA
	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		HashMap<Tupla, Set<Integer>> m = new HashMap<Tupla, Set<Integer>>();
		return ejercicio3RecursivaMultipleConMemoria(m, a, b, c);
	}
	
	// No se por que cuando comento los diccionarios sigue funcionando igual
	private static Set<Integer> ejercicio3RecursivaMultipleConMemoria(HashMap<Tupla, Set<Integer>> m, Integer a, Integer b, Integer c) {
		Set<Integer> s = new HashSet<Integer>();
		Tupla t = Tupla.of(a,b,c);
			
		if (m.keySet().contains(t)) {
			s = m.get(t);
		} else if(b_t.test(t)) {
			s = sb_t.apply(t);
			m.put(t, s);
		} else {
			Set<Integer> s1 = ejercicio3RecursivaMultipleConMemoria(m, t.a()-5, t.b()/4, t.c()-2);
			Set<Integer> s2 = ejercicio3RecursivaMultipleConMemoria(m, t.a()/3, t.b()-3, t.c()/2);
				
			s.addAll(s1);
			s.addAll(s2);
			
			m.put(t, s);
		}
			
		return s;
	}
	
	// ITERATIVA IMPERATIVA
	public static Set<Integer> ejercicio3Iterativo(Integer a, Integer b, Integer c) {
		HashMap<Tupla, Set<Integer>> m = new HashMap<Tupla, Set<Integer>>();
			
		for (int i = 0; i<=a; i++) {
				for (int j = 0; j<=b; j++) {
					for (int k = 0; k<=c; k++) {
						
						Tupla tp = Tupla.of(i, j, k);
						
						if (i <= 5 || j <= 3 || k <= 2) {
							Set<Integer> sb = new HashSet<Integer>(List.of(i*2, j+3, k));
							m.put(tp, sb);
						} 
						
						else {
							Set<Integer> s1 = new HashSet<Integer>(m.get(Tupla.of(i-5, j/4, k-2)));
							Set<Integer> s2 = new HashSet<Integer>(m.get(Tupla.of(i/3, j-3, k/2)));
							
							m.put(tp, s1);
							m.get(tp).addAll(s2);
							
							
						}
					}
				}
			}
			return m.get(Tupla.of(a, b, c));
			
		
	}
	
	// TUPLA
	
	public static record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a, Integer b, Integer c) {
			return new Tupla(a, b, c);
		}	
	}
	
	// PREDICADOS
	private static Predicate<Tupla> b_t = t -> t.a() <= 5 || t.b() <= 3 || t.c() <= 2;
	private static Function<Tupla, Set<Integer>> sb_t = t -> new HashSet<Integer>(List.of(t.a()*2, t.b()+3, t.c()));
		

}
