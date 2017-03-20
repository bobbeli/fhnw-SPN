import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SBox {
	Map<String, String> sBox;
	String x, sx;
	boolean encrypt;
	
	
	/**
	 * Instanz of SBox
	 * @param x			Values 0-F in hex
	 * @param sx		S(x) Values in hex
	 * @param encrypt	ture represents a encrypt sbox, otherwise decrypt sbox
	 */
	
	public SBox(String x, String sx, boolean encrypt){
		this.x = x;
		this.sx = sx;
		this.encrypt = encrypt;
		init();
		
	}
	
	public void init(){
		String[] xSplit = x.split("");
		String[] sxSplit = sx.split("");
		
		sBox = new HashMap<String, String>();
		
		if(encrypt){
			for(int i = 0; i<16; i++){
				sBox.put(hexToBin(xSplit[i]), hexToBin(sxSplit[i]));
			}
		}else{
			for(int i = 0; i<16; i++){			
				sBox.put(hexToBin(sxSplit[i]), hexToBin(xSplit[i]));
			}
		}
	}
	
	public String hexToBin(String s) {
		  return new BigInteger(s, 16).toString(2);
	}

	public Map getsBox() {
		return sBox;
	}

	public void setsBox(Map sBox) {
		this.sBox = sBox;
	}
	
	public String getSxBox(String binaryX){
		return sBox.get(binaryX);
	}

}
