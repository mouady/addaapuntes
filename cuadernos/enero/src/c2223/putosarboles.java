package c2223;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import us.lsi.tiposrecursivos.*;

public class putosarboles {
	record T(List<Integer> camino, int suma) {
		public static T of(List<Integer> camino) {
			int s = camino.stream().reduce(0, (x,y) -> x+y);
			return new T(new ArrayList<Integer>(camino), s);
		}
		public static T of(List<Integer> camino, int suma) {
			return new T(new ArrayList<Integer>(camino), suma);
		}
		public static T first() {
			return T.of(List.of());
		}
		
	}
	
	public static List<Integer> f(Tree<Integer> tree) {
		T b = g(tree);
		return b==null?null:b.camino();
		
	}

	private static T g(Tree<Integer> tree) {
		return switch(tree) {
		case TEmpty<Integer> t -> null;
		case TLeaf<Integer> t -> {
			List<Integer> nuevoCamino = new ArrayList<Integer>();
			if (!(t.label()%2==0)) nuevoCamino.add(t.label());
			yield T.of(nuevoCamino, t.label()); // la suma empieza con esa etiqueta
		}
		case TNary<Integer> t -> {
			T res = null;
			if (!(t.label()%2==0)) {
				T caminoMin = t.children().stream()
						.map(ch -> g(ch))
						.filter(x -> x!=null)
						.min(Comparator.comparing(T::suma))
						.orElse(null);
				
				if(caminoMin!=null) {
					caminoMin.camino().add(0, t.label());
					res = T.of(caminoMin.camino(), caminoMin.suma() + t.label());
				}
			} else res = null;					
			yield res;
		}
		};
	}
	
	public static void main(String[] args) {
		 // Crear hojas
       Tree<Integer> hoja1 = Tree.empty();
       Tree<Integer> hoja2 = Tree.leaf(6);
       Tree<Integer> hoja3 = Tree.leaf(10);
       Tree<Integer> hoja4 = Tree.leaf(11);
       Tree<Integer> hoja5 = Tree.leaf(8);
       Tree<Integer> hoja6 = Tree.leaf(9);
       Tree<Integer> hoja7 = Tree.leaf(13);
       Tree<Integer> hoja8 = Tree.leaf(14);
       Tree<Integer> hoja9 = Tree.leaf(16);
       Tree<Integer> hoja10 = Tree.leaf(17);

       // Crear nodos intermedios
       Tree<Integer> nodoIntermedio3 = Tree.nary(7, List.of(hoja3, hoja4));
       Tree<Integer> nodoIntermedio5 = Tree.nary(12, List.of(hoja7, hoja8));
       Tree<Integer> nodoIntermedio6 = Tree.nary(15, List.of(hoja9, hoja10));
       Tree<Integer> nodoIntermedio1 = Tree.nary(2, List.of(hoja1, hoja2, nodoIntermedio3));
       Tree<Integer> nodoIntermedio2 = Tree.nary(3, List.of(hoja5, hoja6));
       Tree<Integer> nodoIntermedio4 = Tree.nary(4, List.of(nodoIntermedio5, nodoIntermedio6));

       Tree<Integer> tree = Tree.nary(1, List.of(nodoIntermedio1, nodoIntermedio2, nodoIntermedio4));
       System.out.println(f(hoja1));
       System.out.println(f(hoja4));
       System.out.println(f(tree));
       
      

	}
}
