package c2324;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;
import java.util.function.BiPredicate;

public class EJ1_PrimerParcial {

	static final BiPredicate<Integer, Integer> P1 = (a,b) -> a <= 2 && b>20;
	static final BiPredicate<Integer, Integer> P2 = (a,b) -> a%2==0;
	
	public static Map<Integer, String> nofinal(Integer a, Integer b) {
		Map<Integer, String> acum = nofinalg(a,b);
		return acum;
	}

	private static Map<Integer, String> nofinalg(Integer a, Integer b) {
		Map<Integer, String> r = new HashMap<Integer, String>();
		if (P1.test(a, b)) {
			r.put(a+b, a.toString()+b.toString());
		}
		
		else if (P2.test(a, b)) {
			r = nofinalg(a-1,b+3);
			r.put(a, b.toString());
		}
		else {
			r = nofinalg(a-3,b+5);
			r.put(b, b.toString()+a.toString());
		}
		return r;
	}
	
	@SuppressWarnings("unused")
	private static Map<Integer, String> finall(Integer a, Integer b) {
		Map<Integer, String> acum = new HashMap<Integer, String>();
		acum = finallg(a, b, acum);
		return acum;
	}
	
	private static Map<Integer, String> finallg(Integer a, Integer b, Map<Integer, String> acum) {
		Map<Integer, String> r = acum;
		if (P1.test(a, b)) {
			r.put(a+b, a.toString()+b.toString());
		}
		
		else if (P2.test(a, b)) {
			acum.put(a, b.toString());
			r = finallg(a-1,b+3, acum);
			
		}
		else {
			acum.put(b, b.toString()+a.toString());
			r = finallg(a-3,b+5, acum);
			
		}
		return r;
	}
	
	public static Map<Integer, String> imperativa(Integer a, Integer b) {
		Integer ea = Integer.valueOf(a);
		Integer eb = Integer.valueOf(b);
		Map<Integer, String> acum = new HashMap<Integer, String>();
		
		while(!P1.test(ea, eb)) {
			
			if (P2.test(ea, eb)) {
				acum.put(ea, eb.toString());
				ea -= 1;
				eb += 3;
			}
			else {
				acum.put(eb, eb.toString()+ea.toString());
				ea -= 3;
				eb += 5;
			}
		}
		acum.put(ea+eb, ea.toString()+eb.toString());
		return acum;
	}
	
	public record T(Integer a, Integer b, Map<Integer,String> acum) {
		
		public static T of(Integer a, Integer b, Map<Integer,String> acum) {
			return new T(a,b, acum);
		}
		
		public static T first(Integer a, Integer b) {
			return T.of(a, b, new HashMap<Integer, String>());
		}
		
		public boolean hasNext() {
			return P1.test(a, b);
		}
		
		public T next() {
			if (P2.test(a, b)) {
				acum.put(a, b.toString());
				return T.of(a-1, b+3, acum);
			}
			else {
				acum.put(b, b.toString()+a.toString());
				return T.of(a-3, b+5,acum);
			}
		}
		
		
	}
	
	public static Map<Integer, String> versionFuncional(Integer a, Integer b){  
		T res=Stream.iterate(T.first(a, b),t->t.next())  
				  .filter(e->e.hasNext())  
				  .findFirst()  
				  .get();  
		 
		 if(!(res.acum.containsKey(a+b))){  
		  res.acum.put(res.a+res.b, res.a.toString()+res.b.toString()); 
		 }  
		 
		 return res.acum;  
		} 
	
	
	public static void main(String[] args) {
		System.out.println(versionFuncional(2, 30));
		System.out.println(versionFuncional(4, 10));
		System.out.println(versionFuncional(3, 30));
	}

	
	
	
}
