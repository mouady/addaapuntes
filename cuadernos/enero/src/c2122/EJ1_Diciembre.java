package c2122;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EJ1_Diciembre {

	public static Map<Integer, List<String>> funcional(Integer a, Integer b) { 
		return Stream.iterate(a, c -> c <= b, d -> d + a + d%b) 
		.filter(c -> c%10 != b%10).map(d -> "(" + d + ")") 
		.collect(Collectors.groupingBy(c -> c.length()-2)); 
		}
	
		public static Map<Integer, List<String>> recFinal(Integer a, Integer b) {
			Integer e = Integer.valueOf(a);
			Map<Integer, List<String>> acum = new HashMap<Integer, List<String>>();
			acum = recFinal(a, b, acum, e);
			return acum;
		}
	
	private static Map<Integer, List<String>> recFinal(Integer a, Integer b, Map<Integer, List<String>> acum,
				Integer e) {
			Map<Integer, List<String>> res = acum;
			if (e <= b) {
				if (e%10 != b%10) {
					String s = "(" + e.toString() + ")";
					Integer key = s.length() - 2;
					if(!res.containsKey(key)) res.put(key, new ArrayList<String>());
					res.get(key).add(s);
				}
				res = recFinal(a, b, acum, e + a + e%b);
			}
			return res;
		}
	
		public static Map<Integer, List<String>> iterativa(Integer a, Integer b) {
			Integer e = Integer.valueOf(a);
			Map<Integer, List<String>> acum = new HashMap<Integer, List<String>>();
			
			while (e <= b) {
				if (e%10 != b%10) {
					String s = "(" + e.toString() + ")";
					Integer key = s.length() - 2;
					if(!acum.containsKey(key)) acum.put(key, new ArrayList<String>());
					acum.get(key).add(s);
				}
				e = e + a + e%b;
			}
			return acum;
		}

	public static void main(String[] args) {
		System.out.println(funcional(13, 1021));
		System.out.println(recFinal(13, 1021));
		System.out.println(iterativa(13, 1021));
		
	}
}
