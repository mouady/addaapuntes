package c2324;

import java.util.ArrayList;
import java.util.List;
import us.lsi.tiposrecursivos.*;

public class ArbolCarpetas {
	public static List<List<String>> busca_carpetas(Tree<String> t,String nombre_fichero) {
		List<List<String>> acum = new ArrayList<>();
		List<String> camino = new ArrayList<String>();
		acum = busca_carpetas(t,nombre_fichero, camino, acum);
		return acum;
	}

	private static List<List<String>> busca_carpetas(Tree<String> tree, String nombre_fichero,
			List<String> camino, List<List<String>> ac) {
		
		List<List<String>> res = ac;
		return switch(tree) {
		case TEmpty<String> t -> res;
		case TLeaf<String> t -> {
			String posibleFichero = t.label();
			if(posibleFichero.equals(nombre_fichero)) {
				List<String> aux = new ArrayList<String>(camino);
				res.add(aux);
			}
			yield res;
		}
		case TNary<String> t -> {
			List<String> aux = new ArrayList<String>(camino);
			aux.add(t.label());
			t.children().stream().forEach(ch -> busca_carpetas(ch, nombre_fichero, aux, ac));
			yield res;
							     
		}
		};
		
		
		
	}
	
	public static void main(String[] args) {
        // Nodo "perdidos" con un solo hijo "1x01.avi"
        Tree<String> perdidos = Tree.nary("perdidos", Tree.leaf("1x01.avi"));

        // Nodo "series" con el subárbol "perdidos"
        Tree<String> series = Tree.nary("series", perdidos);

        // Nodo "docs" con los archivos "lista.txt" y "agenda.txt"
        Tree<String> docs = Tree.nary("docs", 
            Tree.leaf("lista.txt"), 
            Tree.leaf("agenda.txt")
        );

        // Nodo "personal" con los subárboles "series" y "docs"
        Tree<String> personal = Tree.nary("personal", series, docs);

        // Nodo "so" con el archivo "Server-base.qcow2"
        Tree<String> so = Tree.nary("so", Tree.leaf("Server-base.qcow2"));

        // Nodo "adda" con el archivo "PI5.zip"
        Tree<String> adda = Tree.nary("adda", Tree.leaf("PI5.zip"));

        // Nodo "trabajo" con los archivos y subárboles "lista.txt", "so" y "adda"
        Tree<String> trabajo = Tree.nary("trabajo", 
            Tree.leaf("lista.txt"), 
            so, 
            adda
        );

        // Nodo "temporal" con el archivo "lista.txt"
        Tree<String> temporal = Tree.nary("temporal", Tree.leaf("lista.txt"));

        // Nodo raíz "root" con los subárboles "personal", "trabajo" y "temporal"
        Tree<String> tree = Tree.nary("root", personal, trabajo, temporal);

        // Representación en cadena del árbol
        System.out.println(tree.toString());
        System.err.println(busca_carpetas(tree, "lista.txt"));
        System.err.println(busca_carpetas(tree, "avatar.avi"));
    }

}
