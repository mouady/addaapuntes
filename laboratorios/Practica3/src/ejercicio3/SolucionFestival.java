package ejercicio3;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Map2;

import java.util.HashMap;

public class SolucionFestival {

    public static SolucionFestival create(List<Integer> ls) {
        return new SolucionFestival(ls);
    }

    private Integer numAsignaciones;
    private Map<Integer, Integer> solucion;
    private Double costeTotal;
    private Integer unidadesTotales;

    private SolucionFestival(List<Integer> ls) {
    	solucion = Map2.empty();
    	
    	for (int i = 0; i < ls.size(); i++) {
    		
    	}
    	
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Resumen de asignaciones:\n");

        Map<Integer, Integer> aforoOcupadoPorArea = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> entradasPorArea = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : solucion.entrySet()) {
            Integer tipoEntrada = entry.getKey() / DatosFestival.getNumAreas();
            Integer area = entry.getKey() % DatosFestival.getNumAreas();
            Integer unidades = entry.getValue();

            if (unidades > 0) {
                aforoOcupadoPorArea.put(area, aforoOcupadoPorArea.getOrDefault(area, 0) + unidades);
                entradasPorArea.computeIfAbsent(area, k -> new HashMap<>())
                        .put(tipoEntrada, entradasPorArea.get(area).getOrDefault(tipoEntrada, 0) + unidades);
            }
        }

        for (int i = 0; i < DatosFestival.getNumAreas(); i++) {
            Integer aforoOcupado = aforoOcupadoPorArea.getOrDefault(i, 0);
            if (aforoOcupado > 0) {
                result.append(String.format("Aforo área %s: %d/%d\n",
                        DatosFestival.getArea(i).nombre(),
                        aforoOcupado,
                        DatosFestival.getAforoMaximoArea(i)));

                entradasPorArea.getOrDefault(i, new HashMap<>()).forEach((tipoEntrada, unidades) ->
                        result.append(String.format("Tipo de entrada %s asignadas: %d unidades\n",
                                DatosFestival.getTipoEntrada(tipoEntrada).tipo(), unidades)
                ));
            }
        }

        result.append(String.format("\nCoste total: %.2f\nUnidades totales: %d\n", costeTotal, unidadesTotales));

        return result.toString();
    }

    public Integer getNumAsignaciones() {
        return numAsignaciones;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getCosteTotal() {
        return costeTotal;
    }

    public Integer getUnidadesTotales() {
        return unidadesTotales;
    }
    
    public static void main(String[] args) {
    	Locale.setDefault(Locale.of("en", "US"));
		
		AlgoritmoAG.ELITISM_RATE  = 0.10;
		AlgoritmoAG.CROSSOVER_RATE = 0.95;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		AlgoritmoAG.POPULATION_SIZE = 1000;
		
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		
		for (int i = 1; i <= 3; i++) {
			
			System.out.println("================================");
			System.out.println("DATOS DE ENTRADA: " + i);
			System.out.println("================================");
			
			Ejercicio3AG p = new Ejercicio3AG("resources/ejercicio3/DatosEntrada" + i + ".txt");
			
			AlgoritmoAG<List<Integer>, SolucionFestival> ap = AlgoritmoAG.of(p);
			ap.ejecuta();
			
			System.out.println("================================");
			System.out.println(ap.bestSolution());
			System.out.println("================================\n");
		}
    }
}
