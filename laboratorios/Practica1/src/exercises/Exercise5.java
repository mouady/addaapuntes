package exercises;

import java.math.BigInteger;
import java.util.HashMap;

public class Exercise5 {

	// ESQUEMA RECURSIVO NO FINAL
	public static Double ejercicio5Double(Integer n) {
		Double res = null;
		if (n <= 6) res = 1.0;
		else res = 1.0 + ejercicio5Double(n-1) * log2(n-1);
		return res;
	}
	
	public static BigInteger ejercicio5BigInteger(Integer n) {
		BigInteger res = null;
		if (n <= 6) res = BigInteger.ONE;
		else {
			BigInteger lg = BigInteger.valueOf(log2(n-1));
			res = ejercicio5BigInteger(n-1).multiply(lg).add(BigInteger.ONE) ;
		}
		return res;
	}
	
	// ESQUEMA ITERATIVO
		public static Double ejercicio5DoubleIter(Integer n) {
			HashMap<Integer, Double> m = new HashMap<Integer, Double>();
			
			for (int i = 1; i <= n; i++) { // Trabajamos en Z+:1,2,3...
				if (i <= 6) m.put(i, 1.0); // Caso base
				else m.put(i, 1 + m.get(i-1) * log2(i-1));
			}
			
			return m.get(n);
		}
		
		public static BigInteger ejercicio5BigIntegerIter(Integer n) {
			HashMap<Integer, BigInteger> m = new HashMap<Integer, BigInteger>();
			
			for (int i = 1; i <= n; i++) {
				if (i <= 6) m.put(i, BigInteger.ONE); // Caso base
				else  {
					BigInteger lg = BigInteger.valueOf(log2(i-1));
					m.put(i, m.get(i-1).multiply(lg).add(BigInteger.ONE));
				}
			}
			return m.get(n);
		}

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}