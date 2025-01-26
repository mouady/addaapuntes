package c2122;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;


public class EJ1_PrimerParcial {
	
	final static BiPredicate<Integer, Integer> P1 = (n,m) -> n<4 && m<2;
	final static BiPredicate<Integer, Integer> P2 = (n,m) -> n<4 || m<2;
	final static BiPredicate<Integer, Integer> P3 = (n,m) -> n%2==0 && n>=4 && m>=2;
	
	
	public static Integer mult(Integer n, Integer m) {
		//estados:n,m
		Integer s = 0;
		
		if (P1.test(n, m)) s = n + m*m;
		else if (P2.test(n, m)) s = n*n + m;
		else if (P3.test(n, m)) s = 3 * mult(n-1, m-1) + 2;
		else s = mult(n-1, m-2) + mult(n-2, m-2);
		
		return s;
	}
	
	public record T(Integer n, Integer m) {}
	
	public static Integer multMem(Integer n, Integer m) {
		// estados:n,m
		Map<T,Integer> mem = new HashMap<>();
		return multMem(n, m, mem);
	}
	
	private static Integer multMem(Integer n, Integer m, Map<T, Integer> mem) {
		T aux = new T(n, m);
		Integer s;
		if (mem.containsKey(aux)) s = mem.get(aux);
		else if (P1.test(n, m)) {
			s = n + m * m;
			mem.put(aux, s);
		} else if (P2.test(n, m)) {
			s = n * n + m;
			mem.put(aux, s);
		} else if (P3.test(n, m)) {
			s = 3 * multMem(n - 1, m - 1, mem) + 2;
			mem.put(aux, s);
		} else {
			s = multMem(n - 1, m - 2, mem) + multMem(n - 2, m - 2, mem);
			mem.put(aux, s);
		}
		return s;
	}
	
	public static Integer iterativo(Integer n, Integer m) {
		// estados:n,m
		Integer[][] mem = new Integer[n+1][m+1];
		Integer v;
		
		for (int i = 0; i<=n; i++) {
			for (int j = 0; j<=m; j++) {
				
				if(P1.test(i, j)) v = i + j*j;
				else if(P2.test(i, j)) v = i*i + j;
				else if(P3.test(i, j)) v = 3 * mem[i-1][j-1] + 2;
				else v = mem[i-1][j-2] + mem[i-2][j-2];
				
				mem[i][j] = v;
			}
		}
		return mem[n][m];
	}
	
	
	public static void main(String[] args) {
		System.out.println(mult(21, 22));
		System.out.println(multMem(21, 22));
		System.out.println(iterativo(21, 22));
		
	}
}
