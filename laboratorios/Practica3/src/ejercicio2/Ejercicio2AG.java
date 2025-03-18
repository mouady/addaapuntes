package ejercicio2;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import ejercicio2.DatosCursos.Curso;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio2AG implements ValuesInRangeData<Integer, SolucionCursos> {
										// Lo personalizamos para que se comporte como binario
	
	private static final int k = 10000;
	
	public Ejercicio2AG(String fichero) {
		DatosCursos.iniDatos(fichero);
	}	
	
	@Override
	public Integer size() {
		// Numero de genes del cromosoma
		return DatosCursos.getNumCursos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return +funcionObjetivo(value) -k*(errorR1(value)+errorR2(value)+errorR3(value)+errorR4(value));
	}

	private double errorR4(List<Integer> value) {
		double error = 0.;
		
		double costeTotal = IntStream.range(0, value.size()).boxed()
				.mapToDouble(i -> DatosCursos.getCurso(i).coste() * value.get(i)).sum();
		if (costeTotal > DatosCursos.getPresupuestoTotal()) error = Math.pow(costeTotal-DatosCursos.getPresupuestoTotal(),2);
		
		return error;
	}

	private double errorR3(List<Integer> value) {
		double error = 0.;
		
		double duracionMedia = IntStream.range(0, value.size()).boxed()
				.mapToDouble(i -> DatosCursos.getCurso(i).duracion() * value.get(i)).sum()/value.size();
		if (duracionMedia < 20) error = Math.pow(20-duracionMedia,2);
		
		return error;
	}

	private double errorR2(List<Integer> value) {
		double error = 0.;
		
		// Area/num de cursos
		Map<Integer, Integer> mapa = new java.util.HashMap<>();
		Integer areaTecnologia = 0; // Segun enunciado
		for (int i:value) {
			Curso curso = DatosCursos.getCurso(i);
			Integer areaDelCurso = curso.area();
			if (!mapa.containsKey(areaDelCurso)) mapa.put(areaDelCurso, 0);
			if (i==1) mapa.put(areaDelCurso, mapa.get(areaDelCurso)+1);
			
		}
		
		Integer cursosTecnologia = mapa.get(areaTecnologia);
		Integer maxMapa = mapa.values().stream().max(Integer::compare).get();
		if (cursosTecnologia < maxMapa) error = Math.pow(maxMapa-cursosTecnologia,2);
		
		return error;
	}

	private double errorR1(List<Integer> value) {
		double error = 0.;
		List<Integer> areas = DatosCursos.getAreas();
		IntStream.range(0, value.size()).boxed().forEach(i -> areas.remove(DatosCursos.getCurso(i).area()));
		error = Math.pow(areas.size(),2);
		return error;
	}

	private double funcionObjetivo(List<Integer> value) {
		return IntStream.range(0, value.size()).boxed()
				.mapToDouble(i -> DatosCursos.getCurso(i).relevancia()*value.get(i))
				.sum();
	}

	@Override
	public SolucionCursos solucion(List<Integer> value) {
		return SolucionCursos.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
