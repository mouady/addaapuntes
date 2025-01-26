package ej.sueltos;

import java.util.stream.IntStream;

public class Factorial {
	public static Integer factorialRec(Integer n) {
		Integer res = null;
		if (n==1 | n==0) {
			res = 1;
		}
		res = n * factorialRec(n-1);
		return res;

	}
	
	public static Integer factorialImp(Integer n) {
		Integer res = 1;
		Integer i = 1;
		while(i <=n) {
			res = res * i;
			i++;
			
		}
		return res;
	}

	public static Integer factorialFun(Integer n) {
		return IntStream
				.range(1, n) // No se incluye n
				.reduce(1, (a,b) -> a*b);
	}
}
