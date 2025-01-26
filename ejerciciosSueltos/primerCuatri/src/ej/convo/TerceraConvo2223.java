package ej.convo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerceraConvo2223 {
	// FUNCIONAL
	public static Map<Integer,Long> ejercicio1Funcional(Integer a, Integer b, String c) {
		return Stream.iterate(2*b, e -> e<a*c.length(), e -> e+b)
				.filter(e -> e%10 != 0)
				.map(e-> c + "-" + e)
				.collect(Collectors.groupingBy(s -> s.length(),
											   Collectors.counting()));
	}
	
	// ITERATIVA IMPERATIVA
	public static Map<Integer,Long> ejercicio1IterativaImperativa(Integer a, Integer b, String c) {
		Map<Integer,Long> res = new HashMap<Integer, Long>();
		Integer estado = 2*b;
		
		while(estado<a*c.length()) {
			if (estado%10 != 0) {
				String estadoMapeado = c + "-" + estado;
				int k = estadoMapeado.length();
				
				if (!res.containsKey(k)) res.put(k, 0L);
				res.put(k, res.get(k)+1);
				
				
			}
			estado = estado + b;
		}
		return res;
	}
	
	// RECURSIVA FINAL
	
	public static Map<Integer,Long> ejercicio1RecursivaFinal(Integer a, Integer b, String c) {
		Map<Integer,Long> acum = new HashMap<Integer, Long>();
		Integer estadoInicial = 2*b;
		return ejercicio1RecursivaFinal(acum, estadoInicial, a, b, c);
	}
	
	private static Map<Integer,Long> ejercicio1RecursivaFinal(Map<Integer,Long> acum, Integer estado, Integer a, Integer b, String c) {
		//Map<Integer,Long> res = acum; Se puede hacer tambien asi
		if (estado<a*c.length()) {
			if (estado%10 != 0) {
				String estadoMapeado = c + "-" + estado;
				int k = estadoMapeado.length();
				
				if (!acum.containsKey(k)) acum.put(k, 0L);
				acum.put(k, acum.get(k)+1);
				
				
			}
			acum = ejercicio1RecursivaFinal(acum, estado+b, a, b, c);
		}
		return acum;
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(ejercicio1Funcional(135, 5, "AJFIJWEOEIF"));
		System.out.println(ejercicio1IterativaImperativa(135, 5, "AJFIJWEOEIF"));
		System.out.println(ejercicio1RecursivaFinal(135, 5, "AJFIJWEOEIF"));
	}

}
