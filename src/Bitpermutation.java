import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Bitpermutation {
	static Map<Integer, Integer> pBox;
	static int[] bx = new int[]{ 0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15 };
	
	public Bitpermutation(){
		init();

	}
	
	public void init(){
		
		pBox = new HashMap<Integer, Integer>();
		
		
		for(int i = 0; i<16; i++){
			pBox.put(bx[i], i);
		}

	}
	


	public Map getpBox() {
		return pBox;
	}

	public void setpBox(Map pBox) {
		this.pBox = pBox;
	}
	
	public static String permutString(StringBuilder permut){
		char[] origString = permut.toString().toCharArray();
		
		for(int i = 0; i < permut.length()-1; i++){
			permut.setCharAt(pBox.get(i), origString[i]);
		}
		
		return permut.toString();
	}


}
