package c2324;

import java.util.List;

import us.lsi.common.DoublePair;
import us.lsi.tiposrecursivos.*;

public class Arbol_PrimerParcial {

	// Records auxiliares
	public record Familiar(String nombre, char genero) {}
	
	public static Double proporcionPadre(Tree<Familiar> g) {
		Familiar raiz = g.optionalLabel().get();
		DoublePair acum = new DoublePair(0.0, 0.0);
		proporcionPadre(g, raiz, acum);
		if (acum.second() == 0) return 0.0;
		else return acum.first() / acum.second();
	}

	private static void proporcionPadre(Tree<Familiar> g, Familiar raiz, DoublePair acum) {
		switch (g) {
		case TEmpty<Familiar> t:
			break;
		
		case TLeaf<Familiar> t:
			String nombre = t.label().nombre();
	    	char genero = t.label().genero();
	    	if (genero == raiz.genero()) {
	    		acum = acum.add(DoublePair.of(0.0, 1.0));
	    		if (nombre.equals("padre")) acum = acum.add(DoublePair.of(1.0, 0.0));
	    	}
	    	break;
		
		case TNary<Familiar> t:
			String nom = t.label().nombre();
    		char gen = t.label().genero();
    		if (gen == raiz.genero()) {
    			acum = acum.add(DoublePair.of(0.0, 1.0));
    			if (nom.equals("padre")) acum = acum.add(DoublePair.of(1.0, 0.0));
    		}
    		for (Tree<Familiar> tt : t.children()) {
				proporcionPadre(tt, raiz, acum);
			}
    		break;
			
		}
		
	}
	
	public static void main(String[] args) {
		
		Tree<Familiar> prueba = new TNary<Familiar>(new Familiar("hijo", 'H'), List.of(
                new TNary<Familiar>(new Familiar("padre", 'H'), List.of(
                        new TLeaf<Familiar>(new Familiar("hijo", 'H')),
                        new TLeaf<Familiar>(new Familiar("hija", 'M'))
                        )),
                new TNary<Familiar>(new Familiar("madre", 'M'), List.of(
                        new TLeaf<Familiar>(new Familiar("Jose", 'H')),
                        new TLeaf<Familiar>(new Familiar("hija", 'M'))
                        )),
                new TNary<Familiar>(new Familiar("padreBinarie", 'O'), List.of(
                        new TLeaf<Familiar>(new Familiar("hijo", 'H')),
                        new TLeaf<Familiar>(new Familiar("Marisa", 'M'))

			))));

		System.out.println(proporcionPadre(prueba));
	}
	
	
		
	
	
	
	
		
	
}
