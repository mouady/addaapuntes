package ejercicio1;

import java.util.List;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class Ejercicio1AG implements ValuesInRangeData<Integer,SolucionAlmacen>{

	private static int FACTOR = 10000;
	
	public static Ejercicio1AG create(String fichero) {
		return new Ejercicio1AG(fichero);
	}
	
	public Ejercicio1AG(String fichero) {
		DatosAlmacenes.iniDatos(fichero);
	}
	
	@Override
	public Integer size() {
		return DatosAlmacenes.getNumProductos();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		return +funObjetivo(value)
			   // error R1 ya implicito
			   -FACTOR*(errorR2(value)+errorR3(value));
	}

	private double funObjetivo(List<Integer> value) {
		int cont = 0;
		int numAlmacenes = DatosAlmacenes.getNumAlmacenes();
		// Si hay algun producto cuyo almacen > numAlmacenes, es que no esta siendo alamacenado
		for (int elem :value) {
			if (elem < numAlmacenes) {
				cont++;
			}
		}
		return cont;
	}
	
	private double errorR3(List<Integer> value) {
		 Integer incompatibles = 0;
		 for(int j=0; j<DatosAlmacenes.getNumAlmacenes(); j++) {
			 for(int i=0; i<DatosAlmacenes.getNumProductos(); i++) {
				 for(int i2=i; i2<DatosAlmacenes.getNumProductos(); i2++) {
						 if(DatosAlmacenes.sonIncompatibles(i,i2)) {
							 incompatibles++;
						 }
			 }
		 	}
		 }
		 return Math.pow(incompatibles, 2);
	}
	
	private double errorR2(List<Integer> value) {
		double error = 0.;
		
		for (int j=0; j<DatosAlmacenes.getNumAlmacenes(); j++) {
			 Integer volumen = 0;
			 for(int i=0; i<DatosAlmacenes.getNumProductos(); i++) {
				 volumen+=DatosAlmacenes.getMetrosCubicosProducto(i);
			 }
			 if(volumen>DatosAlmacenes.getMetrosCubicosAlmacen(j)) {
				 error+=Math.pow(DatosAlmacenes.getMetrosCubicosAlmacen(j)-volumen, 2);
				 }
		}
		return error;
	}

	@Override
	public SolucionAlmacen solucion(List<Integer> value) {
		return SolucionAlmacen.create(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosAlmacenes.getNumAlmacenes()+1; //pq +1?? -> pq no esta incluido el almacen ficticio
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	

}
