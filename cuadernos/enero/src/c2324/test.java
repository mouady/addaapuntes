package c2324;

import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import us.lsi.graphs.views.SubGraphView;

public class test {
	
	public record Pozo() {}
	public record Tuberia() {}
	
	public static Boolean esPeligroso (Graph<Pozo,Tuberia> g, Pozo p) { 
		ConnectivityInspector<Pozo, Tuberia> ci_antes = new 
		ConnectivityInspector<>(g); 
		Integer componentes_antes = ci_antes.connectedSets().size(); 
		
		
		
		
		
		Predicate<Pozo> filtro_vertice = pozo -> !pozo.equals(p); 
		
		
		Predicate<Tuberia> filtro_arista = 
				tuberia -> g.getEdgeTarget(tuberia)!=p || g.getEdgeSource(tuberia)!=p ; 
		
		
		
		Graph<Pozo,Tuberia> grafo_nuevo = SubGraphView.of(g, 
		filtro_vertice, filtro_arista); 
		ConnectivityInspector<Pozo, Tuberia> ci_despues = new 
		ConnectivityInspector<>(grafo_nuevo); 
		Integer componentes_despues = ci_despues.connectedSets().size(); 
		return componentes_antes<componentes_despues; 
		
	}
	
	
	
		
}
