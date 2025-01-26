package c2122;


import us.lsi.tiposrecursivos.*;


public class Arbol_PrimerParcial {
	public static Boolean alg(BinaryTree<Integer> tree) {
		return switch (tree) {
		case BTree<Integer> t -> {
			int sumaIzq = algPriv(t.left());
			int sumaDer = algPriv(t.right());
			System.out.println(sumaIzq + " " + sumaDer);
			yield sumaIzq == sumaDer;
		}
		default -> Boolean.TRUE;
		
		};
	}

	private static Integer algPriv(BinaryTree<Integer> tree) {
		return switch (tree) { 
		case BEmpty<Integer> t -> 0;
		case BLeaf<Integer> t -> t.label();	
		case BTree<Integer> t -> {
			int label = tree.optionalLabel().get();
			int sumaIzq = algPriv(t.left());
			int sumaDer = algPriv(t.right());
			yield label + sumaIzq + sumaDer;
		}
		};
		
	}
	
	public static void main(String[] args) {
		// Primer árbol
		BLeaf<Integer> a7 = new BLeaf<>(2);
		BLeaf<Integer> a8 = new BLeaf<>(2);
		BLeaf<Integer> a9 = new BLeaf<>(4);
		BLeaf<Integer> a10 = new BLeaf<>(4);
		BTree<Integer> a5 = new BTree<>(4, a7, a8);
		BTree<Integer> a6 = new BTree<>(0, a9, a10);
		BinaryTree<Integer> tree1 = new BTree<>(8, a5, a6);

		// Segundo árbol
		BLeaf<Integer> a11 = new BLeaf<>(-1);
		BLeaf<Integer> a12 = new BLeaf<>(-1);
		BTree<Integer> a91 = new BTree<>(2, a11, a12);
		BEmpty<Integer> emptyLeaf = new BEmpty<Integer>(); // Árbol vacío
		BTree<Integer> a71 = new BTree<>(6, emptyLeaf, a91);
		BLeaf<Integer> a81 = new BLeaf<>(6);
		BTree<Integer> a51 = new BTree<>(8, new BLeaf<>(4), new BLeaf<>(4));
		BTree<Integer> a61 = new BTree<>(4, a71, a81);
		BinaryTree<Integer> tree2 = new BTree<>(0, a51, a61);

		
		System.err.println(alg(tree1));
		System.err.println(alg(tree2));
		
		
		
	}
	
	
}
