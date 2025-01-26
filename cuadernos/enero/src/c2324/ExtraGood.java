package c2324;

import java.util.List;
import java.util.Set;
import us.lsi.tiposrecursivos.*;

public class ExtraGood {

	public static Integer caminoLargoSinRepetir(Tree<String> t) {
		return caminoLargoSinRepetir(t, 0, Set.of());
	}
	private static Integer caminoLargoSinRepetir(Tree<String> tree, Integer ac, Set<String> visitados) {
		return switch(tree) {
		case TEmpty<String> t -> 0;
		case TLeaf<String> t -> {
			int r = 0;
			if (!visitados.contains(t.label())) {
				visitados.add(t.label());
				r = 1;
			}
			yield r;
		}
		case TNary<String> t -> {
			int numHijos = t.childrenNumber();
			int r = 0;
			for(int i = 0; i<numHijos; i++) {
				r += caminoLargoSinRepetir(t.child(i), ac, visitados);
			}
			yield r;
		}
		
		};
				
	}
	public static void main(String[] args) {
		 
	       // Crear nodos intermedios
	       Tree<String> hoja6 = Tree.leaf("f");
	       
	       Tree<String> hoja5 = Tree.leaf("d");
	       Tree<String> nodoIntermedio4 = Tree.nary("b", List.of(hoja6));
	       Tree<String> nodoIntermedio2 = Tree.nary("c", List.of(nodoIntermedio4, hoja5));
	       
	       Tree<String> hoja2 = Tree.leaf("d");
	       Tree<String> hoja3 = Tree.leaf("e");
	       Tree<String> hoja4 = Tree.leaf("f");
	       Tree<String> nodoIntermedio3 = Tree.nary("a", List.of(hoja2, hoja3, hoja4));
	       Tree<String> hoja1 = Tree.leaf("b");
	       Tree<String> nodoIntermedio1 = Tree.nary("b", List.of(nodoIntermedio3, hoja1));

	       Tree<String> tree = Tree.nary("a", List.of(nodoIntermedio1, nodoIntermedio2));
	       System.out.println(tree);
	       System.out.println(caminoLargoSinRepetir(tree));

	}
}
