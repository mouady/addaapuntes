package c2324;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EJ1_Octubre {

	public static Map<Integer, Long> funcional(Integer a, Integer b, String c){  
		return Stream.iterate(2*b, e -> e<a*c.length(), e -> e+b)   
		.filter(e -> e%10!=0)  
		.map(e -> c + "-" + e)  
		.collect(Collectors.groupingBy(s -> s.length(),   
		Collectors.counting()));  
		} 
	
	public static Map<Integer, Long> imperativa(Integer a, Integer b, String c) {
		Map<Integer, Long> acum = new HashMap<Integer, Long>();
		Integer e = 2*b;
		
		while (e<a*c.length()) {
			if (e%10!=0) {
				String s = c + "-" + e;
				Integer key = s.length();
				if (!acum.containsKey(key)) acum.put(key, 0L);
				acum.put(key, acum.get(key) + 1L);
			}
			e = e+b;
		}
		
		return acum;
	}
	
	public static Map<Integer, Long> recFinal(Integer a, Integer b, String c) {
		Integer e = 2*b;
		Map<Integer, Long> acum = new HashMap<Integer, Long>();
		acum = recFinal(a, b, c, acum, e);
		return acum;
	}
	
	private static Map<Integer, Long> recFinal(Integer a, Integer b, String c, Map<Integer, Long> acum, Integer e) {
		Map<Integer, Long> r = acum;
		if (e<a*c.length()) {
			if (e%10!=0) {
				String s = c + "-" + e;
				Integer key = s.length();
				if (!acum.containsKey(key)) acum.put(key, 0L);
				acum.put(key, acum.get(key) + 1L);
			}
			r = recFinal(a, b, c, acum, e+b);
		}
		return r;
	}
	
	public static void main(String[] args) {
		System.out.println(funcional(15, 3, "sesdf"));
		System.out.println(imperativa(15, 3, "sesdf"));
		System.out.println(recFinal(15, 3, "sesdf"));

		
	}
}
