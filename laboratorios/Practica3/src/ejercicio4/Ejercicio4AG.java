package ejercicio4;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.jgrapht.graph.SimpleWeightedGraph;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.IntegerVertexGraphView;

public class Ejercicio4AG implements SeqNormalData<SolucionEstaciones> {
	
	private static final double CASTIGO = 10000;
	
	public static IntegerVertexGraphView<Estacion,Tramo> grafoTiempo;
	public static IntegerVertexGraphView<Estacion,Tramo> grafoCoste;
	public static Integer n;
	
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}
	
	public Ejercicio4AG(String fichero) throws IOException {	
		
		SimpleWeightedGraph<Estacion, Tramo> grafoT = GraphsReader.newGraph(fichero,
				Estacion::ofFormat, 
				Tramo::ofFormat,
				Graphs2::simpleWeightedGraph,
				Tramo::tiempo);
		SimpleWeightedGraph<Estacion, Tramo> grafoC = GraphsReader.newGraph(fichero,
				Estacion::ofFormat, 
				Tramo::ofFormat,
				Graphs2::simpleWeightedGraph,
				Tramo::costeBillete);
		
		// Correspondencia Estacion - Numero
		grafoTiempo = IntegerVertexGraphView.of(grafoT);
		grafoCoste = IntegerVertexGraphView.of(grafoC);
		
		// Es indiferente el peso de la cantidad de estaciones:
		n = grafoTiempo.vertexSet().size();	
	}

	private double tiempoTramo_ij(int i, int j) {
		Double res = CASTIGO;
		if (grafoTiempo.containsEdge(i, j)) {
			res = grafoTiempo.getEdgeWeight(i, j);
		}
		return res;
	}
	
	private double funcionObjetivo(List<Integer> value) {
		// El %n lo ponemos para que el ultimo nodo se conecte con el primero
		return IntStream.range(0, n).boxed()
				.mapToDouble(i -> tiempoTramo_ij(value.get(i), value.get(i + 1%n)))
				.sum();
	}
	
	private double getCosteTramo(Integer i, Integer j) {
		double res = CASTIGO;
		if(grafoCoste.containsEdge(i, j)) {
			res = grafoCoste.getEdgeWeight(i, j);
		}
		return res;
	}
	
	private double errorR1(List<Integer> value) {
		double res = 0.0;
		double sumaTotal = grafoCoste.edgeSet().stream()
				.mapToDouble(e -> e.weight()).sum();
		double costeTrayecto = IntStream.range(0, n).boxed()
				.mapToDouble(i -> getCosteTramo(value.get(i), value.get(i + 1%n)))
						.sum();
		
		if (costeTrayecto > 0.75 * sumaTotal) {
            res = costeTrayecto - 0.75 * sumaTotal ;
        }
		
		return res;
	}
	
	private boolean checkSatisfaccionConsec(int i, int j) {
		boolean res = false;
		if (grafoTiempo.containsEdge(i, j)) {
			res = grafoTiempo.getVertex(i).satisfaccionClientes() >= 7
					&& grafoTiempo.getVertex(j).satisfaccionClientes() >= 7;
		}
		return res;
	}
	
	private double errorR2(List<Integer> value) {
		double res = CASTIGO;
		
		boolean alMenosDosEstacionesConsec = IntStream.range(0, n).boxed()
		.map(i -> checkSatisfaccionConsec(value.get(i), value.get(i + 1%n)))
		.filter(bool-> bool)
		.count() >= 2;
		
		if (alMenosDosEstacionesConsec) res = 0.;
		return res;
	}
	
	

	// List<Integer> value representa al cromosoma
		@Override
		public Double fitnessFunction(List<Integer> value) {
			return -funcionObjetivo(value)
					-errorR1(value)
					-errorR2(value);
		}

	@Override
	public SolucionEstaciones solucion(List<Integer> value) {
		return SolucionEstaciones.create(value);
	}

	@Override
	public Integer itemsNumber() {
		return n;
	}

}
