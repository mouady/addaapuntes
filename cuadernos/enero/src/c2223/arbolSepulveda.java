package c2223;

import java.util.ArrayList;
import java.util.List;
import us.lsi.tiposrecursivos.BEmpty;
import us.lsi.tiposrecursivos.BLeaf;
import us.lsi.tiposrecursivos.BTree;
import us.lsi.tiposrecursivos.BinaryTree;

public class arbolSepulveda {

	public static List<Integer> f(BinaryTree<Integer> tree) {
		List<Integer> res = new ArrayList<Integer>();
		g(tree, res, 0);
		return res;
	}
	
	private static int g(BinaryTree<Integer> tree, List<Integer> acum, int nivel) {
		if(acum.size() <= nivel) acum.add(0); 
		return switch(tree) {
		case BEmpty<Integer> t -> 0;
		
		case BLeaf<Integer> t -> {
			if (t.label() > nivel) acum.set(nivel, acum.get(nivel)+1);
			yield t.label();
			
		}
			
		case BTree<Integer> t -> {
			int suma = g(t.left(), acum, nivel+1) +  g(t.right(), acum, nivel+1);
			if (t.label() > suma) {
				acum.set(nivel, acum.get(nivel)+1);
			}
			yield t.label();
			}
		};
		
		
	}

	public static void main(String[] args) {
		
		BTree<Integer> a51 = new BTree<>(1, new BLeaf<>(2), new BLeaf<>(3));
		BEmpty<Integer> a61 = new BEmpty<Integer>(); // Árbol vacío
		BinaryTree<Integer> tree1 = new BTree<>(3, a51, a61);
		System.out.println(f(tree1));

	}

}
