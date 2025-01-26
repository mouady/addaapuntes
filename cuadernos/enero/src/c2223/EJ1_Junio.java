package c2223;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EJ1_Junio {
	public static Map<String, Long> funcional(Integer a, Integer b, Integer c) { 
		return Stream.iterate(a+b,  e -> e<c, e -> e+a*b) 
		.filter(e-> e%2==0).map(e -> "[" + e%10 + "]") 
		.collect(Collectors.groupingBy(s -> s, Collectors.counting())); 
		}
	
	public static Map<String, Long> iterativo(Integer a, Integer b, Integer c) {
		Integer e = a+b;
		Map<String, Long> acum = new java.util.HashMap<>();
		while (e < c) {
			if (e % 2 == 0) {
				String key = "[" + e % 10 + "]";
				if (!acum.containsKey(key)) acum.put(key, 0L);
				acum.put(key, acum.get(key) + 1L);
			}
			e = e + a * b;
		}
		return acum;
	}
	
	public static Map<String, Long> recFinal(Integer a, Integer b, Integer c) {
		Integer e = a+b;
		Map<String, Long> acum = new java.util.HashMap<>();
		acum = recFinal(a, b, c, acum, e);
		return acum;
	}
	
	private static Map<String, Long> recFinal(Integer a, Integer b, Integer c, Map<String, Long> acum, Integer e) {
		Map<String, Long> res = acum;
		if (e < c) {
			if (e % 2 == 0) {
				String key = "[" + e % 10 + "]";
				if (!acum.containsKey(key)) acum.put(key, 0L);
				acum.put(key, acum.get(key) + 1L);
			}
			res = recFinal(a, b, c, acum, e + a * b);
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(funcional(35, 5, 786));
		System.out.println(iterativo(35, 5, 786));
		System.out.println(recFinal(35, 5, 786));
	}
}
