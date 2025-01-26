package tests;

import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedGraph;
import exercise4.*;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestExercise4 {
	
	static final List<String> files = List.of("./resources/Exercise4/Exercise4_1.txt",
			  "./resources/Exercise4/Exercise4_2.txt",
			  "./resources/Exercise4/Exercise4_3.txt");

	static final List<String> labels = List.of("ApartadoA",
			   "ApartadoB",
			   "ApartadoC",
			   "ApartadoD");
	
	public static SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> readFile(String file) {
    	SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> res = GraphsReader
				.newGraph(file, //fichero de datos
						Aplicacion::ofFormat, //factoria para construir los vertices
						RelacionPrecedencia::ofFormat, //factoria para crear las aristas
						Graphs2::simpleDirectedGraph); //creador del grafo
    	return res;
    }

    public static void main(String[] args) {
    	testA(files.get(0));
    	testB(files.get(0));
    	testC(files.get(0));
    	testD(files.get(0));
    }
    
    public static void testA(String file) {
    	SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g = readFile(file);
    	System.out.println("A: " + Exercise4.apartadoA(g));
    }
    
    public static void testB(String file) {
    	SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g = readFile(file);
    	List<Set<Aplicacion>> res = Exercise4.apartadoB(g);
    	Set<Aplicacion> sinAnteriores = res.get(0); 
    	Set<Aplicacion> sinPosteriores = res.get(1);
    	System.out.println("B: App(s) sin dependencias anteriores: " + sinAnteriores + "\n   " +
    			"App(s) sin dependencias posteriores: " + sinPosteriores);
    	Exercise4.mostrarGrafoApartadoB(g, sinAnteriores, sinPosteriores, labels.get(1));
    }
    
    public static void testC(String file) {
    	SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g = readFile(file);
    	Set<Aplicacion> appsMasDependientes = Exercise4.apartadoC(g);
    	System.out.println("C: App(s) más dependiente(s): " + appsMasDependientes);
    	Exercise4.mostrarGrafoApartadoC(g, appsMasDependientes, labels.get(2));
    }
    
    public static void testD(String file) {
    	SimpleDirectedGraph<Aplicacion, RelacionPrecedencia> g = readFile(file);
    	String appObj = "Aplicacion5";
    	Set<Aplicacion> res = Exercise4.apartadoD(g, appObj);
		System.out.println("A: Aplicaciones que dependen de " + appObj + res);
		
		Exercise4.mostrarGrafoApartadoD(g, res, labels.get(3), appObj);
    }
    
    
    
    
	
}
