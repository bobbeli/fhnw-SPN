import java.util.ArrayList;
import java.util.List;

public class SPNDecrypter {

	String chiffreText = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
	//String chiffreText = "10010101";
	//public byte[] key = 00111010100101001101011000111111;
	public SPNDecrypter () {
		splitInputText(chiffreText);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////
	//Gegeben:
	/*
	 * SBox-Umwandlung
	 * Bit-Permutation
	 * Runden r
	 * 
	 */
/////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	 * Method splits chiffre text into blocks of length of 16
	 * @param chiffreText string representation of the chiffre text
	 * @return arraylist with splitted blocks
	 * 
	 */
	public ArrayList<Integer> splitInputText(String chiffreText) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		String[] temp = chiffreText.split("(?<=\\G.{16})");
		
		for(String e: temp) {
			list.add(Integer.parseInt(e, 2));
		}
		return list;
	}
	
	
	//2)
	//xi = E((y−1 + i) mod 2l; k) ⊕ yi (i = 0; : : : n − 1)
	
	public void ctrModiDecrypt () {
		
	}
	
	//3)
	// SPN Decrypt
	
		// Method S Box und Inverse
	
		// Methode BitPermutation 
	
		// Methode X-OR Calc

}
