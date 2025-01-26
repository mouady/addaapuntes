package tests;

import java.util.List;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import exercise1.*;

public class TestArboles {
	
	static final List<String> files = List.of("resources/Exercise1Binary.txt");
	
	public static void main(String[] args) {
		test1binary();
	}

	private static void test1binary() {
		printCabecera(1, "Binary");
		List<BinaryTree<Integer>> arboles = lecturaEj1Binario(files.get(0));
		arboles.forEach(arbol -> System.out.println(Exercise1Binary.mayorPalindromo(arbol)));
	}
	
	private static void printCabecera(int numEj, String cad) {
		System.out.println(".".repeat(20));
		System.out.println("EJERCICIO " + numEj + " - " + cad);
		System.out.println(".".repeat(20));
	}
	
	private static List<BinaryTree<Integer>> lecturaEj1Binario(String file) {
		return Files2.streamFromFile(file)
				.map(linea -> BinaryTree.parse(linea, s -> Integer.valueOf(s)))
				.toList();
	}
	
	
}