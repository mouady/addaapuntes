package ejercicio4;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class SolucionEstaciones {

    public static SolucionEstaciones create(List<Integer> ls) {
        return new SolucionEstaciones(ls);
    }
    
    private Integer numEstaciones;
    private List<Estacion> camino;
    private Double tiempoTotal;
    private Double tiempoMedio;

    // Constructor privado
    private SolucionEstaciones(List<Integer> ls) {
    	 numEstaciones = ls.size();
    	 camino = ls.stream()
    	 .map(i -> Ejercicio4AG.grafoTiempo.getVertex(i))
    	 .collect(Collectors.toList());
    	 
    	 tiempoTotal = getTiempoTotal(camino);
    	 tiempoMedio = tiempoTotal / numEstaciones;	
    }

    private Double getTiempoTotal(List<Estacion> camino) {
        Double res = 0.;
        camino.add(camino.get(0));
        for (int i = 0; i<camino.size()-1; i+=2) {
        	res += Ejercicio4AG.grafoCompleto.getEdge(camino.get(i), camino.get(i+1)).tiempo();
        }
        return res;
        }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen del recorrido:\n");

        result.append("Camino seguido: ").append(camino.stream()
                .map(Estacion::nombre)
                .collect(Collectors.joining(" -> "))).append("\n");

        result.append(String.format("Tiempo total: %.2f min\n", tiempoTotal));
        result.append(String.format("Tiempo medio por estación: %.2f min\n", tiempoMedio));

        return result.toString();
    }

    public Integer getNumEstaciones() {
        return numEstaciones;
    }

    public List<Estacion> getCamino() {
        return camino;
    }

    public Double getTiempoTotal() {
    	return tiempoTotal;
    }

    public Double getTiempoMedio() {
        return tiempoMedio;
    }

    public static void main(String[] args) {
    	Locale.setDefault(Locale.of("en", "US"));

		AlgoritmoAG.ELITISM_RATE = 0.15;
		AlgoritmoAG.CROSSOVER_RATE = 0.90;
		AlgoritmoAG.MUTATION_RATE = 0.9;
		AlgoritmoAG.POPULATION_SIZE = 2000;

		StoppingConditionFactory.NUM_GENERATIONS = 500;
		StoppingConditionFactory.MAX_ELAPSEDTIME = 50;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

		
		Ejercicio4AG p = new Ejercicio4AG("resources/ejercicio4/DatosEntrada2.txt");
		AlgoritmoAG<List<Integer>, SolucionEstaciones> ap = AlgoritmoAG.of(p);
		ap.ejecuta();

		System.out.println("================================");
		System.out.println(ap.bestSolution());
		System.out.println("================================");
	}
}
