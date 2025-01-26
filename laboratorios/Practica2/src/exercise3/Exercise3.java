package exercise3;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm.SpanningTree;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.GraphWalk;
import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class Exercise3 {
	
	//NOTA: Se elimina file ya no por error, si no para no modularizar por ahora 
	
	static final List<String> routes = List.of();
	
	public static Graph<Estacion, Tramo> apartadoA(SimpleWeightedGraph<Estacion,Tramo> g) {
		Function<Estacion, Double>  mediaCostesTramos = v -> g.edgesOf(v).stream().mapToDouble(e -> e.costeBillete()).average().getAsDouble();
		Predicate<Estacion> pv = v -> v.numeroEmpleados() > 7 && mediaCostesTramos.apply(v) < 10;
		Predicate<Tramo> pa = e -> {return true;};
		return SubGraphView.of(g, pv, pa);
	}

	public static void mostrarGrafoApartadoA(SimpleWeightedGraph<Estacion,Tramo> g, Graph<Estacion, Tramo> vista, 
			String file) {
		String nombreArchivo = file;

		GraphColors.toDot(g,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "",
				v->GraphColors.colorIf(Color.blue, vista.containsVertex(v)),
				e->GraphColors.colorIf(Color.blue, vista.containsEdge(e)));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");
	
	}

	public static GraphPath<Estacion, Tramo> apartadoB(SimpleWeightedGraph<Estacion, Tramo> gf, String e1, String e2) {
		Estacion est1 = gf.vertexSet().stream().filter(e -> e.nombre().equals(e1)).findFirst().get();
		Estacion est2 = gf.vertexSet().stream().filter(e -> e.nombre().equals(e2)).findFirst().get();
		GraphPath<Estacion, Tramo> dijkstra = DijkstraShortestPath.findPathBetween(gf, est1, est2);
		return dijkstra;
	}
		
	public static void mostrarGrafoApartadoB(SimpleWeightedGraph<Estacion, Tramo> gf, GraphPath<Estacion, Tramo> gp, 
			String file) {
		String nombreArchivo = file;

		GraphColors.toDot(gf,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "", 
				v->GraphColors.colorIf(Color.blue, gp.getVertexList().contains(v)),
				e->GraphColors.colorIf(Color.blue, gp.getEdgeList().contains(e)));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");
	}
	
	
	public static GraphPath<Estacion, Tramo> apartadoC(SimpleWeightedGraph<Estacion, Tramo> gf) {
		// RECORDAR: tiempo medio = double tiempo
		// Se elimino el atributo file
		
		 GraphPath<Estacion, Tramo> circuito = new HeldKarpTSP<Estacion, Tramo>().getTour(gf);
		 if (circuito == null) {
			// Devolvemos un camino sin nada para que a la hora resaltar no se resalte nada
			 System.out.println("No existe caminoTSP");
			return new GraphWalk<Estacion, Tramo>(gf, List.of(), 0);
		 }	 
		 else return circuito;
	}
	
	public static void mostrarGrafoApartadoC(SimpleWeightedGraph<Estacion, Tramo> gf, GraphPath<Estacion, Tramo> caminoTSP, 
			String file) {
		String nombreArchivo = file;

		GraphColors.toDot(gf,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "",
				v->GraphColors.colorIf(Color.blue, caminoTSP.getVertexList().contains(v)),
				e->GraphColors.colorIf(Color.blue, caminoTSP.getEdgeList().contains(e)));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");
	}

	public static Set<Tramo> apartadoD(SimpleWeightedGraph<Estacion, Tramo> gf) {
		// Se elimino el atributo file
		SpanningTree<Tramo> arbolExpansionMinimo = new KruskalMinimumSpanningTree<>(gf).getSpanningTree();
		return arbolExpansionMinimo.getEdges();
	}
	
	public static void mostrarGrafoApartadoD(SimpleWeightedGraph<Estacion, Tramo> gf, Set<Tramo> tramosNecesarios, 
			String file) {
		String nombreArchivo = file;

		GraphColors.toDot(gf,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "",
				v->GraphColors.color(Color.black),
				e->GraphColors.colorIf(Color.blue, tramosNecesarios.contains(e)));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");
	}
	
	// Componentes conexas: si al romper un tramo se generan nuevas componentes conexas, es que es critico
	public static void apartadoE(SimpleWeightedGraph<Estacion, Tramo> gf, String file) {
		Set<Tramo> tramosCriticos = new HashSet<Tramo>();
		ConnectivityInspector<Estacion, Tramo> algIni = new ConnectivityInspector<>(gf);
		int numComConexasInicial = algIni.connectedSets().size();
		
		for (Tramo e:gf.edgeSet()) {
			var gf_aux = new SimpleWeightedGraph<Estacion, Tramo>(gf.getVertexSupplier(),gf.getEdgeSupplier());
			gf_aux.removeEdge(e);
			ConnectivityInspector<Estacion, Tramo> alg = new ConnectivityInspector<>(gf_aux);
			int numComConexas = alg.connectedSets().size();
			if(numComConexas > numComConexasInicial) tramosCriticos.add(e);
		}
		
		// Mostrado del grafo
		String nombreArchivo = file;
		GraphColors.toDot(gf,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "",
				v->GraphColors.color(Color.black),
				e->GraphColors.colorIf(Color.red, tramosCriticos.contains(e)));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");	
	}
	
	//?
	public static Set<Estacion> apartadoF(SimpleWeightedGraph<Estacion, Tramo> gf, String file) {
		
		return null;
	}
	
	public static void mostrarGrafoApartadoF(SimpleWeightedGraph<Estacion, Tramo> gf, Set<Estacion> estacionesMinimas,
			String file) {

	}
	
	// Coloreado
	public static Map<Estacion, Integer> apartadoG(SimpleWeightedGraph<Estacion, Tramo> gf) {
		// Integer represanta el numero de franja
		// Se elimino el atributo file
		Coloring<Estacion> coloreado = new GreedyColoring<Estacion, Tramo>(gf).getColoring();
		return coloreado.getColors();
	}
	
	public static void mostrarGrafoApartadoG(SimpleWeightedGraph<Estacion, Tramo> gf, Map<Estacion, Integer> map, 
			String file) {
		String nombreArchivo = file;

		GraphColors.toDot(gf,"./out/ex3/" + nombreArchivo + ".gv",
				x->x.nombre(),
				x-> "",
				v->GraphColors.color(map.get(v)),
				e->GraphColors.color(Color.black));
		
		System.out.println(nombreArchivo + ".gv generado en " + "./out/ex3/");
	}

}
