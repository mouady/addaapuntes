package parcial12425;

import java.util.Comparator;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.TEmpty;
import us.lsi.tiposrecursivos.TLeaf;
import us.lsi.tiposrecursivos.TNary;
import us.lsi.tiposrecursivos.Tree;

public class arbol {
	
	public static <E> Pair<Integer, Boolean> estaCompensado(Tree<E> tree) {
		return verificarCompensacion(tree);
	}
	
	
	private static <E> Pair<Integer, Boolean> verificarCompensacion(Tree<E> tree) {
		return switch(tree) {
		case TEmpty<E> t -> Pair.of(0, true);
		case TLeaf<E> t -> Pair.of(0, true);
		case TNary<E> t -> {
			Integer res1 = t.children().size() + t.children().stream().map(ch -> verificarCompensacion(ch)).mapToInt(par -> par.first()).sum();
			Boolean res2 = !(res1 - t.children().stream()
					.map(ch -> verificarCompensacion(ch)).min(Comparator.comparing(par -> par.first())).get().first() > 1);
			yield Pair.of(res1, res2);
		}
		};
	}


	public static void main(String[] args) {
		Tree<Integer> arbol1 = Tree.nary(1, 
			    Tree.nary(2, Tree.leaf(5), Tree.leaf(6)),
			    Tree.leaf(3),
			    Tree.nary(4,Tree.leaf(7), Tree.leaf(8))
			);

		Tree<Integer> arbol2 = Tree.nary(1, Tree.nary(2, Tree.leaf(5), Tree.leaf(6)),Tree.nary(3, Tree.leaf(8)),Tree.nary(4, Tree.leaf(7))
			);
		
		System.out.println(estaCompensado(arbol1));
		System.out.println(estaCompensado(arbol2));

	}

}
