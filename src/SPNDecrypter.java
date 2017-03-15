import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SPNDecrypter {

	String chiffreText = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
	public int l = 16;
	public String k = "00111010100101001101011000111111";
	ArrayList<Integer> listOfSplittedBinary;
	Map encryptedList;


	public SPNDecrypter () {
		
		splitInputText();
		
		System.out.println("dsfd");
		
		ctrModiDecrypt();
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
	public void splitInputText() {
		
		listOfSplittedBinary = new ArrayList<>();
		
		String[] temp = chiffreText.split("(?<=\\G.{16})");
		
		for(String e: temp) {
			listOfSplittedBinary.add(Integer.parseInt(e, 2));
		}
		
	}
	
	
	/**
	 * This Methdo handles Modi Decryption
	 */
	public void ctrModiDecrypt () {
		encryptedList = new HashMap();
		int i = 0;
		int yi = listOfSplittedBinary.get(0);
		listOfSplittedBinary.remove(0);
		
		for(int value : listOfSplittedBinary){
			i++;
			encryptedList.put(i, encrypt((value + i) % 2*l, Integer.parseInt(k,2)) ^ yi);
			
		}
	}
	
	
	public int encrypt(int chiffre, int k){
		return 1;
	}
	
	//3)
	// SPN Decrypt
	
		// Method S Box und Inverse
	
		// Methode BitPermutation 
	
		// Methode X-OR Calc

}
