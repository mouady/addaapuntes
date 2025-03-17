package ejercicio1;

import java.util.List;

import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeDatosAlmacenes implements ValuesInRangeData<Integer,SolucionAlmacen>{

	public InRangeDatosAlmacenes(String fichero) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolucionAlmacen solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer max(Integer i) {
		return DatosAlmacenes.get;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
