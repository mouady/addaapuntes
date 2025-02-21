package ejercicio2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import ejercicio2.DatosCursos.Curso;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {
	public static List<Integer> areas;
    public static List<Curso> cursos;
    public static Integer presupuestoTotal;
	
	public static void ejercicio2_model() throws IOException {
		DatosCursos.iniDatos("resources/ejercicio2/DatosEntrada1.txt");

		areas = DatosCursos.getAreas();
		cursos = DatosCursos.getCursos();
		presupuestoTotal = DatosCursos.getPresupuestoTotal();
		
		// Cambiamos de Ejercicio2PLE a DatosCursos para no tener que poner aqui las cabeceras
		AuxGrammar.generate(DatosCursos.class,"modelos/modeloEj2.lsi", "gurobi_models/gurobiEj2Datos1.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/gurobiEj2Datos1.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		}
	
	public static void main(String[] args) throws IOException {	
		ejercicio2_model();
	}

}
