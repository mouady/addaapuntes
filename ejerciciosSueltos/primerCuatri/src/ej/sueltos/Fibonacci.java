package ej.sueltos;

import java.util.HashMap;

public class Fibonacci {

	static Integer fibm(Integer n) {
		HashMap<Integer, Integer> m = new HashMap<Integer, Integer>(); // K:fib(n),res
		return fibm(n, m);
	}
	
	private static Integer fibm(Integer n, HashMap<Integer, Integer> m) {
		Integer res;
		if (m.containsKey(n)) return m.get(n);
		else if (n == 0) {
			res = 0;
			m.put(n, res);
		}
		else if (n == 1) {
			res = 1;
			m.put(n, res);
		}
		else {
			res = fibm(n-1,m) + fibm(n-2,m);
			m.put(n, res);
		}
		return res;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(fibm(3));
	}
	
}
