package ej.parciales;

import java.util.HashMap;
import java.util.Map;

public class Primero2324 {

	// RECURSIVA NO FINAL
	public static Map<Integer,String> ejercicio1RecursivaNoFinal(Integer a, Integer b) { // a,b>0
		Map<Integer,String> res = new HashMap<Integer, String>();
		if (a<=2 || b>20) {
			res.put(a+b, a.toString()+b.toString());
		} else if(!(a<=2 || b>20) || a%2 ==0) {
			res = (ejercicio1RecursivaNoFinal(a-1, b+3)); // tambien se puede hacer con .putAll
			res.put(a, b.toString());
			
		} else {
			res = (ejercicio1RecursivaNoFinal(a-3, b+5)); // tambien se puede hacer con .putAll
			res.put(b, b.toString()+a.toString());
			
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(ejercicio1RecursivaNoFinal(20, 15));
	}
}
