package ej.sueltos;

import java.util.List;

public class TransformacionAlgoritmos {

	// Version funcional sumar todos
	public static Integer ejemplo1Java8(List<Integer> ls) {
		return ls.stream().mapToInt(x->x).sum();
		
	}
	
	// Version imperativa sumar todos
	public static Integer ejemplo1Java7For(List<Integer> ls) {
		Integer a = 0;
		for (Integer e:ls) a = a +e;
		return a;
	}
	
	public static Integer ejemplo2Java7While(List<Integer> ls) {
		Integer a = 0;
		Integer	i = 0;
		while (i <ls.size()) {
			Integer e = ls.get(i);
			a = a + e;
			i++;
		}
		return a;
	}
	
	// Dada una lista, comprobar que todos los numeros sean divisibles por 10
	
	public boolean todosDiv10(List<Integer>ls) {
		boolean a = true;
		for (Integer e:ls) {
			a = e%10 == 0;
			if (a == false) break;
		}
		return a;
		
	}
	
	Boolean todosDiv10Fnl(List<Integer> ls) {
		return null;
		
	}
	
}
