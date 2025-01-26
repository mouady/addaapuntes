package exercises;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import us.lsi.geometria.Cuadrante;
import us.lsi.geometria.Punto2D;

public class Exercise1 {
	
	// ITERATIVA FUNCIONAL
	public static Map<Cuadrante, String> ejercicio1NotacionFuncional(List<Punto2D> ls) {
		return ls.stream()
				 .filter(p -> p.x() % 5 != 0) 
		 		 .map(nx) 
		 		 .collect(Collectors.groupingBy(
		 				 				Punto2D::cuadrante,
		 				 				Collectors.reducing(
		 				 							"",
		 				 							p -> p.x() % 2 == 0 ? p.x() + "¡" : p.x() + "!",
		 				 							(a, b) -> a.isEmpty() ? b : a + "-" + b
		 				 							)));
	}
	
	// ITERATIVA IMPERATIVA
	public static Map<Cuadrante, String> ejercicio1Iterativo(List<Punto2D> ls) {
		Map<Cuadrante, List<String>> b = new HashMap<Cuadrante, List<String>>();
		Integer i = 0;
		
		while(i < ls.size()) {
			Punto2D punto = ls.get(i);
			if (punto.x() % 5 != 0) {
				punto = nx.apply(punto);
				
				Cuadrante k = punto.cuadrante();
				String v = punto.x() % 2 == 0 ? punto.x() + "¡" : punto.x() + "!";
				
				if (!b.containsKey(k)) b.put(k, new ArrayList<String>());
				b.get(k).add(v);
			}
			i++;
		}
		return r(b);
	}
	
	// RECURSIVA FINAL
	public static Map<Cuadrante, String> ejercicio1Recursivo(List<Punto2D> ls) {
		Integer i = 0;
		Map<Cuadrante, List<String>> b = new HashMap<Cuadrante, List<String>>();
		b = ejercicio1RecursivaFinal(ls, b, i);
		return r(b);
	}
	
	private static Map<Cuadrante, List<String>> ejercicio1RecursivaFinal(List<Punto2D> ls,
			Map<Cuadrante, List<String>> b, Integer i) {
		Map<Cuadrante, List<String>> res = b;
		
		if (i < ls.size()) {
			Punto2D punto = ls.get(i);
			
			if (punto.x() % 5 != 0) {
				punto = nx.apply(punto);
				
				Cuadrante k = punto.cuadrante();
				String v = punto.x() % 2 == 0 ? punto.x() + "¡" : punto.x() + "!";
				
				if (!b.containsKey(k)) b.put(k, new ArrayList<String>());
				b.get(k).add(v);
			}
			
			res = ejercicio1RecursivaFinal(ls, b, i+1);
		}
		return res;
	}

	// OPERADOR NEXT Y FUNCION RETORNO
	private static UnaryOperator<Punto2D> nx = punto -> { // Toma un punto y le suma 3 a la cord x
			double nuevaX = punto.x() + 3;
			return Punto2D.of(nuevaX, punto.y());
			};
			
	private static Map<Cuadrante, String> r(Map<Cuadrante, List<String>> b) {
			Map<Cuadrante, String> res = new HashMap<Cuadrante, String>();
			for (Entry<Cuadrante, List<String>> e:b.entrySet()) {
				Cuadrante k2 = e.getKey();
				String v2 = e.getValue().stream().reduce("", (a, z) -> a.isEmpty() ? z : a + "-" + z);
				res.put(k2, v2);
			}
			return res;
			}
}
