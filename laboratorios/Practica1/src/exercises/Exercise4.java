package exercises;

import java.math.BigInteger;

public class Exercise4 {
	// USAMOS UN ESQUEMA NO FINAL
	public static Double funcRecDouble(Integer a) {
		Double res = null;
		if (a<10) res = 5.0;
		else res = Math.sqrt(3*a) * funcRecDouble(a-2);
		return res;
	}
	
	public static BigInteger funcRecBig(Integer a) {
		BigInteger res = null;
		if (a<10) res = BigInteger.valueOf(5);
		else res = BigInteger.valueOf(3*a)
							 .sqrt()
							 .multiply(funcRecBig(a-2));
		return res;
	}
	
	// ESQUEMA ITERATIVO
	public static Double funcItDouble(Integer a) {
		Double ac = 1.0;
		while (!(a < 10)) {
			ac *= Math.sqrt(3*a);
			a -= 2;
			
		}
		return ac * 5.0;
	}
	
	public static BigInteger funcItBig(Integer a) {
		BigInteger ac = BigInteger.valueOf(1);
		while (!(a < 10)) {
			ac = ac.multiply(BigInteger.valueOf(3*a).sqrt());
			a -= 2;
			
		}
		return ac.multiply(BigInteger.valueOf(5));
	}

}
