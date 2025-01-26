package c2122;

import java.util.List;

public class EJ1_Septiembre {
	
	public static boolean iterativo(List<Integer> lista) {
		Integer e = 0;
		Integer first = lista.get(0);
		boolean b = true;
		
		while(e<lista.size()) {
			if (!first.equals(lista.get(e))) {
				b = false;
				break;
			}
			e++;
		}
		return b;
	}
	
	public static boolean recFinal(List<Integer> lista) {
		Integer e = 0;
		boolean b = true;
		b = recFinal(lista, b, e);
		return b;
	}
	
	private static boolean recFinal(List<Integer> lista, boolean b, Integer e) {
		Integer first = lista.get(0);
		boolean res = b;
		
		if (e<lista.size()) {
			if (!first.equals(lista.get(e))) {
				b = false;
			}
			res = recFinal(lista, b, e+1);
		}
		return res;
	}
	
	public static boolean funcional(List<Integer> lista) {
		return lista.stream().allMatch(e -> e.equals(lista.get(0)));
	}
	

	public static void main(String[] args) {
		List<Integer> iguales = List.of(4, 4, 4, 4, 4, 4);
		List<Integer> noIguales = List.of(4, 4, 4, 7, 4, 5);
		
		System.out.println(String.valueOf(iterativo(iguales))+ "[]" + String.valueOf(iterativo(noIguales)));
		System.out.println(String.valueOf(recFinal(iguales))+ "[]" + String.valueOf(recFinal(noIguales)));
		System.out.println(String.valueOf(funcional(iguales))+ "[]" + String.valueOf(funcional(noIguales)));
	}
}
