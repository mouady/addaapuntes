package ejercicio1;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import ejercicio1.DatosAlmacenes.Producto;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Map2;

public class SolucionAlmacen {
	
	public static SolucionAlmacen create(List<Integer> ls) {
		return new SolucionAlmacen(ls);
	}

	private Integer numproductos;
	private Map<Producto, Integer> solucion;

	// Constructor privado
	private SolucionAlmacen(List<Integer> ls) {
		solucion = Map2.empty();
		int idx = 0;
		for (int almacen : ls) {
			if (almacen<DatosAlmacenes.getNumAlmacenes())
				solucion.put(DatosAlmacenes.getProducto(idx), almacen);
			idx++;
		}
		numproductos = solucion.size();
		
	}
	
	@Override
	public String toString() {
		return solucion.entrySet().stream()
		.map(p -> p.getKey().producto()+": Almacen "+p.getValue())
		.collect(Collectors.joining("\n", "Reparto de productos y almacen en el que se coloca cada uno de ellos:\n", String.format("\nProductos colocados: %d", numproductos)));
	}

	
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		AlgoritmoAG.ELITISM_RATE  = 0.20;
		AlgoritmoAG.CROSSOVER_RATE = 0.9;
		AlgoritmoAG.MUTATION_RATE = 0.6;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;
		
		
		Ejercicio1AG p = new Ejercicio1AG("resources/ejercicio1/DatosEntrada3.txt");
		
		AlgoritmoAG<List<Integer>, SolucionAlmacen> ap = AlgoritmoAG.of(p);
		ap.ejecuta();
		
		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
		
	}
}
