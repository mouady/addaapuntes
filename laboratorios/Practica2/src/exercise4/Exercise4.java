package exercise4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class Exercise4 {

	public static List<Aplicacion> apartadoA(SimpleDirectedGraph<Aplicacion,RelacionPrecedencia> g) {
		// Se elimino el atributo file
		List<Aplicacion> res = new ArrayList<Aplicacion>();
		var iterador = new TopologicalOrderIterator<Aplicacion, RelacionPrecedencia>(g);
		while(iterador.hasNext()) res.add(iterador.next());
		return res;
	}
	
	public static List<Set<Aplicacion>> apartadoB(SimpleDirectedGraph<Aplicacion,RelacionPrecedencia> g) {
		// Se elimino el atributo file
		Set<Aplicacion> appSinDependenciasAnteriores = new HashSet<Aplicacion>(); // Miramos por los incomingEdges
		Set<Aplicacion> appSinDependenciasPosteriores = new HashSet<Aplicacion>(); // Miramos por los outgoingEdges
		
		for (Aplicacion a:g.vertexSet()) {
			int incoming = g.incomingEdgesOf(a).size(); 
			int outgoing = g.outgoingEdgesOf(a).size();
			
			if (incoming == 0) appSinDependenciasAnteriores.add(a);
			if (outgoing == 0) appSinDependenciasPosteriores.add(a);
		}
		 return List.of(appSinDependenciasAnteriores, appSinDependenciasPosteriores); 
	}
	
	public static void mostrarGrafoApartadoB(SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g, 
			Set<Aplicacion> appSinDependenciasAnteriores, Set<Aplicacion> appSinDependenciasPosteriores, String file) {
		String nombreArchivo = file;
		
		GraphColors.toDot(g,"./out/ex4/" + nombreArchivo + ".gv",
				x->x.id(),
				x-> "",
				v->GraphColors.colorIf(List.of(appSinDependenciasAnteriores.contains(v), appSinDependenciasPosteriores.contains(v)),
						List.of(Color.magenta, Color.green)),
				e->GraphColors.color(Color.black));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex4/");
	}
	
	// No es muy eficiente, pero funciona :D
	public static Set<Aplicacion> apartadoC(SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g) {
		// Se elimino el atributo file
		Set<Aplicacion> res = new HashSet<Aplicacion>();
		Map<Aplicacion, Integer> aux = new HashMap <Aplicacion, Integer>();
		
		Aplicacion primerNodo = apartadoA(g).getFirst();
		HashSet<Aplicacion> lisVertices = new HashSet<Aplicacion>(g.vertexSet());
		lisVertices.remove(primerNodo);
		
		for(Aplicacion a: lisVertices) {
			GraphPath<Aplicacion,RelacionPrecedencia> caminoMinimo = DijkstraShortestPath.findPathBetween(g, primerNodo, a);
			int numVerticesNecesarios = caminoMinimo.getVertexList().size();
			aux.put(a, numVerticesNecesarios);
		}
		
		int maxValue = aux.values().stream().max(Integer::compare).get();
		aux.keySet().forEach(k -> {if(aux.get(k) == maxValue) res.add(k);});
		return res;

	}
	
	public static void mostrarGrafoApartadoC(SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g,
			Set<Aplicacion> appsMasDependientes, String file) {
		String nombreArchivo = file;
		
		GraphColors.toDot(g,"./out/ex4/" + nombreArchivo + ".gv",
				x->x.id(),
				x-> "",
				v->GraphColors.colorIf(Color.magenta, appsMasDependientes.contains(v)),
				e->GraphColors.color(Color.black));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex4/");

	}
	
	public static Set<Aplicacion> apartadoD(SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g, String idApp) {
		// Se elimino el atributo file
		Set<Aplicacion> res = new HashSet<Aplicacion>();
		Aplicacion appObj = g.vertexSet().stream().filter(v -> v.id().equals(idApp)).findFirst().get();
		for (Aplicacion a: g.vertexSet()) {
			
			if(g.containsEdge(appObj, a)) {
				res.add(a);
			} 
			else {
				Set<Aplicacion> aux = new HashSet<Aplicacion>(res);
				aux.forEach(v -> {if (g.containsEdge(v, a)) res.add(a);});
			}
		}
		
		return res;
	
	}
	
	public static void mostrarGrafoApartadoD(SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g,
			Set<Aplicacion> appDesordenadas, String file, String idApp) {
		String nombreArchivo = file;
		
		GraphColors.toDot(g,"./out/ex4/" + nombreArchivo + ".gv",
				x->x.id(),
				x-> "",
				v->GraphColors.colorIf(List.of(v.id().equals(idApp), appDesordenadas.contains(v)),
						List.of(Color.green, Color.magenta)),
				e->GraphColors.color(Color.black));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex4/");
		
	}

	
}
