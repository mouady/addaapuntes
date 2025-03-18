package ejercicio2;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.Map2;

public class SolucionCursos {

    public static SolucionCursos create(List<Integer> ls) {
        return new SolucionCursos(ls);
    }

    private Integer numCursos;
    		//Curso/Area
    private Map<Integer, Integer> solucion;
    private Double puntuacionTotal;
    private Integer costeTotal;

    private SolucionCursos(List<Integer> ls) {
    	solucion = Map2.empty();
    	puntuacionTotal = 0.;
    	costeTotal = 0;
    	numCursos = 0;
    	
    	for (int i = 0; i < ls.size(); i++) {
    		if (ls.get(i) == 1) {
    			solucion.put(i, 1);
    			numCursos++;
    			puntuacionTotal += DatosCursos.getCurso(i).relevancia();
    			costeTotal += DatosCursos.getCurso(i).coste();
    		}
    	}
    }

    @Override
    public String toString() {
        return solucion.entrySet().stream()
                .map(p -> "Curso " + p.getKey() + ": Seleccionado")
                .collect(Collectors.joining("\n", "Cursos seleccionados:\n", String.format("\nTotal de cursos seleccionados: %d\nPuntuación total: %.2f\nCoste total: %d", numCursos, puntuacionTotal, costeTotal)));
    }

    public Integer getNumCursos() {
        return numCursos;
    }

    public Map<Integer, Integer> getSolucion() {
        return solucion;
    }

    public Double getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Integer getCosteTotal() {
        return costeTotal;
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
			
			Ejercicio2AG p = new Ejercicio2AG("resources/ejercicio2/DatosEntrada" + i + ".txt");
			
			AlgoritmoAG<List<Integer>, SolucionCursos> ap = AlgoritmoAG.of(p);
			ap.ejecuta();
			
			System.out.println("================================");
			System.out.println(ap.bestSolution());
			System.out.println("================================\n");
		}
		
		
	}
}
