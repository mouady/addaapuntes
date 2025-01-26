package ej.sueltos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import us.lsi.math.Math2;

public class PrimosMenoresQueN {
	
	// Ejercicio de la diapositiva T1-52
	
	public static List<Integer> formaIterativaFuncional(Integer n) {
		return Stream.iterate(1, e -> e<n, e -> Math2.siguientePrimo(e)).toList();
	}
	
	public static List<Long> formaIterativaImperativa(Integer n) {
		List<Long> res = new ArrayList<Long>();
		Long e = 2L;
		while (e<n) {
			if(Math2.esPrimo(e)) res.add(e);
			e++;		
		}
		return res;
	}

}
