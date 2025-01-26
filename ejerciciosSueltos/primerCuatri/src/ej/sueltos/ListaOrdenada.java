package ej.sueltos;

import java.util.Comparator;
import java.util.List;

public class ListaOrdenada {
	
	// Declarar genericos tal que: <T, E, V...>
	
	static <E> Boolean estaOrdenada(List<E> ls, Comparator<E> cmp) {
		return estaOrdenda(ls,0,cmp);
		
	}
	
	static <E> Boolean estaOrdenda(List<E> ls, int i, Comparator<E> cmp) {
		Boolean r = true;
		Integer n = ls.size();
		if (n-i >= 2) {
			r = menorOIgual(cmp, ls.get(i), ls.get(i+1)) &&
					estaOrdenda(ls,i+1,cmp);
		}
		return r;
	}

	static <E> boolean menorOIgual(Comparator<E> cmp, E a, E b) {
		return cmp.compare(a, b) <= 0;
	}


	public static void main(String[] args) {
		
		// Creamos listas aleatorias
		List<Integer> ls1 = List.of(3,4,5,8,7,0,1);
		List<String> ls2 = List.of("A", "B", "C");
		List<String> ls3 = List.of("Z", "B", "C");
		
		// Creamos comparadores
		Comparator<Integer> intCmp = Integer::compare;
		Comparator<String> stringCmp = String::compareTo; 
		
		//Printeamos los resultados
		System.out.println(estaOrdenada(ls1, intCmp));
		System.out.println(estaOrdenada(ls2, stringCmp));
		System.out.println(estaOrdenada(ls3, stringCmp));
	}
}
