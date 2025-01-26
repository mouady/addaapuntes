package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import exercises.Exercise4;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestExercise4 {
	
//	// Para A = 14 guardamos el resultado entero esperado:
//	static final Integer RES_ESPERADO = 1064; //1064.8943661
	
	
	private static Integer nMin = 100; // n mínimo para el cálculo de potencia
	private static Integer nMax = 50000; // n máximo para el cálculo de potencia
	private static Integer razon = 1000; // incremento en los valores de n del cálculo de potencia
	private static Integer nIter = 1; // número de iteraciones para cada medición de tiempo
	private static Integer nIterWarmup = 10; // número de iteraciones para warmup
		
	
	public static void genData (Consumer<Integer> algorithm, String file) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,nMin,nMax,razon,nIter,nIterWarmup);

	}
	
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}
	
	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("out/ex4/potFuncRecDouble.txt",
						"out/ex4/potFuncRecBig.txt",
						"out/ex4/potFuncItDouble.txt",
						"out/ex4/potFuncItBig.txt"), 
				List.of("FuncRecDouble","FuncRecBig","FuncItDouble","FuncItBig")
				
				);
	}
	
	
	public static void main(String[] args) {
		testFunciones(14);
		//generar();
		mostrar();
		
		

	
		
		
//		// Grafico
//		Function<Integer, Long> ft = GenData.time(Exercise4::funcItDouble);
//		
//		String file = "./out/datos.txt";
//		GenData.tiemposEjecucionAritmetica(ft, file, 0, 10, 1, 1, 10);
//		
//		List<WeightedObservedPoint> datos = DataFile.points(file);
//		Fit ajuste = PowerLog.of();
//		ajuste.fit(datos);
//		MatPlotLib.show(file, ajuste.getFunction(), ajuste.getExpression());
		
		
		
		}

	private static void testFunciones(Integer A) {
		System.out.println("Test Exercise4");
		System.out.println("Test funcRecDouble: " + Exercise4.funcRecDouble(A));
		System.out.println("Test funcRecBig: " + Exercise4.funcRecBig(A));
		System.out.println("Test funcItDouble: " + Exercise4.funcItDouble(A));
		System.out.println("Test funcItBig: " + Exercise4.funcItBig(A));
	}
	
	
	@SuppressWarnings("unused")
	private static void generar() {
		//genData(t -> Exercise4.funcRecDouble(t), "out/ex4/potFuncRecDouble.txt");
		genData(t -> Exercise4.funcRecBig(t), "out/ex4/potFuncRecBig.txt");
		//genData(t -> Exercise4.funcItDouble(t), "out/ex4/potFuncItDouble.txt");
		genData(t -> Exercise4.funcItBig(t), "out/ex4/potFuncItBig.txt");
		
	}
	
	
	private static void mostrar() {
		show(PowerLog.of(), "out/ex4/potFuncRecDouble.txt","FuncRecDouble");
		show(PowerLog.of(List.of(Pair.of(3, 0.))), "out/ex4/potFuncRecBig.txt","FuncRecBig");
		show(PowerLog.of(), "out/ex4/potFuncItDouble.txt","FuncItDouble");
		show(PowerLog.of(List.of(Pair.of(3, 0.))), "out/ex4/potFuncItBig.txt","FuncItBig");
		
		showCombined();
		
	}
	
	

}
