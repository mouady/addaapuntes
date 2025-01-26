package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import exercises.Exercise5;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise5 {
	
	private static Integer nMin = 50; // tamaño min
	private static Integer nMax = 50000; // tamaño max
	private static Integer razon = 2; // incremento
	private static Integer iter = 30; // numero de iter por cada medicion
	private static Integer warmup = 100; // iteraciones para warmup
	private static final List<String> files = List.of("out/ex5/potRecursivo-Double.txt",
												"out/ex5/potRecursivo-Big.txt",
												"out/ex5/potIterativa-Double.txt",
												"out/ex5/potIterativa-Big.txt");
	private static final List<String> labels = List.of("Recursivo-Double",
												 "Recursivo-Big",
												 "Iterativa-Double",
												 "Iterativa-Big");
	
	
	public static void genData(Consumer<Integer> algorithm, String file) {
		Function<Integer, Long> fn = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(fn, file, nMin, nMax, razon, iter, warmup);
	}
	
	public static void genDataGeo(Consumer<Integer> algorithm, String file) {
		Function<Integer, Long> fn = GenData.time(algorithm);
		GenData.tiemposEjecucionGeometrica(fn, file, nMin, nMax, razon, iter, warmup);
	}
	
	
	public static void show(Fit pl, String file, String label) {	
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				files,
				labels);
	}
	
		
	public static void main(String[] args) {
		testFunciones(10);
		generar();
		mostrar();
		
		
		
	}

	
	private static void generar() {
		//genData(n -> Exercise5.ejercicio5Double(n), files.get(0));
		//genData(n -> Exercise5.ejercicio5BigInteger(n), files.get(1));
		//genData(n -> Exercise5.ejercicio5DoubleIter(n), files.get(2));
		genDataGeo(n -> Exercise5.ejercicio5BigIntegerIter(n), files.get(3));
		
	}
	
	
	private static void mostrar() {
		show(PowerLog.of(), files.get(0), labels.get(0));
		show(PowerLog.of(), files.get(1), labels.get(1));
		show(PowerLog.of(), files.get(2), labels.get(2));
		show(PowerLog.of(), files.get(3), labels.get(3));
		
		showCombined();
	}

	
	private static void testFunciones(int A) {
		System.out.println("Test Exercise 5");
		System.out.println("Test ejercicio5Double: " + Exercise5.ejercicio5Double(A));
		System.out.println("Test ejercicio5BigInteger: " + Exercise5.ejercicio5BigInteger(A));
		System.out.println("Test ejercicio5DoubleIter: " + Exercise5.ejercicio5DoubleIter(A));
		System.out.println("Test ejercicio5BigIntegerIter: " + Exercise5.ejercicio5BigIntegerIter(A));
		// Res: 1.409848164447009E63
		
	}
	
	

}
