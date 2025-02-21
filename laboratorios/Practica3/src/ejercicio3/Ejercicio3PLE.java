package ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import ejercicio3.DatosFestival.Area;
import ejercicio3.DatosFestival.TipoEntrada;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	
	public static Boolean tests = false;
    public static List<Area> areas;
    public static List<TipoEntrada> tiposEntrada;
	
	public static void ejercicio3_model() throws IOException {
		DatosFestival.iniDatos("resources/ejercicio3/DatosEntrada3.txt");

		areas = DatosFestival.getAreas();
		tiposEntrada = DatosFestival.getTiposEntrada();
		
		// Cambiamos de Ejercicio3PLE a DatosFestival para no tener que poner aqui las cabeceras
		AuxGrammar.generate(DatosFestival.class,"modelos/modeloEj3.lsi", "gurobi_models/gurobiEj3Datos3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/gurobiEj3Datos3.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		}
	
	public static void main(String[] args) throws IOException {	
		ejercicio3_model();
	}

}
