package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.clase.palindromo.Palindromo;

// CUIDADO: Si usamos listas hay que crear
// nuevos objetos para no tener problemas. Con Strings no hay problemas

public class Exercise1Binary {
	
	public static int mayorPalindromo(BinaryTree<Integer> tree)  {
    	List<String> b0 = new ArrayList<String>();
    	Integer e0 = 0; // nd 
    	return r(mayorPalindromo(tree, b0, e0));
    }

	private static List<String> mayorPalindromo(BinaryTree<Integer> tree, List<String> b, Integer eNivel) {
		List<String> res = b;
		
		if (tree.isEmpty()) {
			return List.of();
		}
		else if (tree instanceof BLeaf) {
			
		}
		else {
			
		}
		return res;
		
		
	}

	private static int r(List<String> res) {
		res.stream().mapToInt(s -> s.length()).max().orElse(-1);
		return 0;
	}

}


//List<String> res = b;
//final Integer nivel = eNivel;
//String cadCandidata = "";
////int tamanoLista = b.size();
//String etiquetaMiNivel = tree.byLevel()
//		 .filter(l -> l.level() == nivel)
//		 .findFirst().get()
//		 .tree().optionalLabel().get().toString();
//
//if (tree.isEmpty()) res = new ArrayList<String>();
//else if (tree instanceof BLeaf) {
//	cadCandidata += etiquetaMiNivel;
//	if (Palindromo.esPalindromo1(cadCandidata)) 
//		res.add(cadCandidata);	
//}
//else {
//	res = mayorPalindromo(tree, b, nivel+1);
//	
//}
//
//return res;
//}
