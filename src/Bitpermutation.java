import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Bitpermutation {
	static Map<Integer, Integer> pBoxInv, pBox;
	static int[] bx = new int[]{ 0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15 };
	
	public Bitpermutation(){
		init();
	}
	
	public void init(){
		pBoxInv = new HashMap<Integer, Integer>();
		pBox    = new HashMap<Integer, Integer>();
		
		for(int i = 0; i<16; i++){
			pBoxInv.put(bx[i], i);
			pBox.put(i,bx[i]);
		}
	}
	
	
	public static String permutString(StringBuilder permut) {
		char[] origString = permut.toString().toCharArray();
		if(origString.length!=16){
			System.out.println("permut String not valid"+ permut);
		}
		for(int i = 0; i < permut.length()-1; i++){
			permut.setCharAt(pBox.get(i), origString[i]);
		}
		
		return permut.toString();
	}
	
	public static String permutStringInv(StringBuilder permut) {
		char[] origString = permut.toString().toCharArray();
		if(origString.length!=16){
			System.out.println("permut String not valid"+ permut);
		}
		for(int i = 0; i < permut.length()-1; i++){
			permut.setCharAt(pBoxInv.get(i), origString[i]);
		}
		
		return permut.toString();
	}


}
