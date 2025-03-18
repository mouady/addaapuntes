package ejercicio3;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio3AG implements ValuesInRangeData<Integer, SolucionFestival> {

	private static final int PENALIZACION = 10000;
	
	public Ejercicio3AG(String fichero) {
		DatosFestival.iniDatos(fichero);
	}
	
	@Override
	public Integer size() {
		return DatosFestival.getNumAreas()*DatosFestival.getNumTiposEntrada();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return -funcionObjetivo(value) + PENALIZACION*(errorR1(value)+errorR2(value));
	}
	
	private double funcionObjetivo(List<Integer> value) {
		return IntStream.range(0, value.size())					 
				.mapToDouble(i -> DatosFestival.getCosteAsignacion(i%3, value.get(i)))
				.sum();
	}
	
	private double errorR1(List<Integer> value) {
		double error = 0.;
		
		for (int j = 0; j < DatosFestival.getNumAreas(); j++) {
            int suma = 0;
            int numTiposEntrada = DatosFestival.getNumTiposEntrada();
            for (int i = 0; i < numTiposEntrada; i++) { // Siempre 3 columnas
                suma += value.get(i + numTiposEntrada*j); // si [0][2] y numTipos=3 => elemento 6 = 0 + 3*2
            }
            
            int aforoMaxArea = DatosFestival.getAforoMaximoArea(j);
			if (suma > aforoMaxArea ) error += suma-aforoMaxArea;
        }
		
		return Math.pow(error, 2);
	}

	private double errorR2(List<Integer> value) {
		double error = 0.;
		
		for (int i = 0; i < DatosFestival.getNumTiposEntrada(); i++) {
            int suma = 0;
            int numAreas = DatosFestival.getNumAreas();
            for (int j = 0; j < numAreas; j++) {
                suma += value.get(i + numAreas*j); 
            }
            
            int cuotaMinima = DatosFestival.getCuotaMinima(i);
            if (suma < cuotaMinima) error += cuotaMinima-suma;
        }
		
		return Math.pow(error, 2);
	}


	@Override
	public SolucionFestival solucion(List<Integer> value) {
		return SolucionFestival.create(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosFestival.getAforoMaximoArea(i);
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
