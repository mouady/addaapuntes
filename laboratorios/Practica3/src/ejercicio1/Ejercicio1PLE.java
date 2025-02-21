package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ejercicio1.DatosAlmacenes.Almacen;
import ejercicio1.DatosAlmacenes.Producto;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	public static List<Almacen> almacenes;
	public static List<Producto> productos;
	
	public static void ejercicio1_model() throws IOException {
		DatosAlmacenes.iniDatos("resources/ejercicio1/DatosEntrada3.txt");

		almacenes = DatosAlmacenes.getAlmacenes();
		productos = DatosAlmacenes.getProductos();
		
		// Cambiamos de Ejercicio1PLE a DatosAlmacenes para no tener que poner aqui las cabeceras
		AuxGrammar.generate(DatosAlmacenes.class,"modelos/modeloEj1.lsi","gurobi_models/gurobiEj1Datos3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/gurobiEj1Datos3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
	}
	
	public static void main(String[] args) throws IOException {	
		ejercicio1_model();
	}

}
