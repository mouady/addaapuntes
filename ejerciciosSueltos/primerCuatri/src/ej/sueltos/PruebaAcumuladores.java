package ej.sueltos;

import java.util.List;
import java.util.stream.Collectors;

public class PruebaAcumuladores {
	public static void main(String[] args) {
		
		List<String> lista = List.of("Python", "Java", "C", "ASM");
		List<Integer> listaEnteros = List.of(1,2,3,4,5,6,7,8,9,10);		
		
		// Collect
		System.out.println(lista.stream().anyMatch(p -> p.charAt(0) == 'C'));
		System.out.println(lista.stream().allMatch(p -> p.length() > 5));
		System.out.println(lista.stream().count());
		
		// Uso del joining
		System.out.println(lista.stream().collect(Collectors.joining("%","PRE","SUF")));
		
		// Uso de reduce, suponemos que sum() no existe
		System.out.println(listaEnteros.stream().reduce(0, (a,b) -> a+b));
		System.out.println(listaEnteros.stream().reduce(1, (a,b) -> a*b));
		
	}

}
