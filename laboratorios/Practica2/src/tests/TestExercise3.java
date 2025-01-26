package tests;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleWeightedGraph;

import exercise3.*;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestExercise3 {

	static final List<String> files = List.of("./resources/Exercise3/Exercise3_1.txt",
											  "./resources/Exercise3/Exercise3_2.txt",
											  "./resources/Exercise3/Exercise3_3.txt");
	
	static final List<String> labels = List.of("ApartadoA",
											   "ApartadoB",
											   "ApartadoC",
											   "ApartadoD",
											   "ApartadoE",
											   "ApartadoF",
											   "ApartadoG");
    // Funciones que asignan pesos:
	static Function<Tramo, Double> asignarPesoCoste = e -> e.costeBillete();
	static Function<Tramo, Double> asignarPesoTiempo = e -> e.tiempo();
	
	// Lectores de files para grafos (uno sin asignar pesos y otro donde elegimos que asignamos como peso):
	public static SimpleWeightedGraph<Estacion, Tramo> readFile(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> res = GraphsReader
				.newGraph(file, //fichero de datos
						Estacion::ofFormat, //factoria para construir los vertices
						Tramo::ofFormat, //factoria para crear las aristas
						Graphs2::simpleWeightedGraph); //creador del grafo
    	return res;
    }
	
	public static SimpleWeightedGraph<Estacion, Tramo> readFile(String file, Function<Tramo, Double> asignarPeso) {
    	SimpleWeightedGraph<Estacion, Tramo> res = GraphsReader
				.newGraph(file, //fichero de datos
						Estacion::ofFormat, //factoria para construir los vertices
						Tramo::ofFormat, //factoria para crear las aristas
						Graphs2::simpleWeightedGraph, //creador del grafo
						asignarPeso);	// Funcion que proporciona el peso de la arista. 
									  	// Debe ser distinto de null si el grafo esde tipo WeightedGraph<V,E>. Si es null no se usa.
    	return res;
    }
	
	public static void main(String[] args) {
		testA(files.get(0));
		testB(files.get(0));
		testC(files.get(0));
		testD(files.get(0));
		testE(files.get(0));
		testG(files.get(0));
    }
    
    public static void testA(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> g = readFile(file);
    	Graph<Estacion, Tramo> vistaA = Exercise3.apartadoA(g);
    	Exercise3.mostrarGrafoApartadoA(g, vistaA, labels.get(0));
    	
    }
    
    public static void testB(String file) {
		SimpleWeightedGraph<Estacion, Tramo> g = readFile(file, asignarPesoCoste);
    	GraphPath<Estacion, Tramo> camino = Exercise3.apartadoB(g, "Nuevos Ministerios", "Chueca");
    	Exercise3.mostrarGrafoApartadoB(g, camino, labels.get(1));
    	
    }
 
    public static void testC(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> g = readFile(file, asignarPesoTiempo);
    	GraphPath<Estacion, Tramo> camino = Exercise3.apartadoC(g);
    	Exercise3.mostrarGrafoApartadoB(g, camino, labels.get(2));
    	
    }
    
    public static void testD(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> g = readFile(file, asignarPesoCoste);
    	Set<Tramo> tramosNecesarios = Exercise3.apartadoD(g);
    	Exercise3.mostrarGrafoApartadoD(g, tramosNecesarios, labels.get(3));
    	
    }
    
    public static void testE(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> g = readFile(file);
    	Exercise3.apartadoE(g, labels.get(4));   	
    }
    
    public static void testG(String file) {
    	SimpleWeightedGraph<Estacion, Tramo> g = readFile(file);
    	Map<Estacion, Integer> mapaColoreado = Exercise3.apartadoG(g);
    	Exercise3.mostrarGrafoApartadoG(g, mapaColoreado, labels.get(6));
    	
    }

}

