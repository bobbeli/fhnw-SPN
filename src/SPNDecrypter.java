import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SPNDecrypter {

	String chiffreText = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
	public int l = 16;
	public String k;
	ArrayList<Integer> listOfSplittedBinary;
	Map encryptedList;
	Key keys;
	SBox sbox;
	Bitpermutation permut;
	
	public SPNDecrypter () {
		permut = new Bitpermutation();
		
		listOfSplittedBinary = splitInputText(chiffreText);
		
		// SetUp SBox 
		// Map mit Key (s), Value (sx) in Binary Form
		// False for Decrypt, True for Encrypt
		sbox = new SBox("0123456789ABCDEF", "E4D12FB83A6C5907", false);
		
		k = "00111010100101001101011000111111";
		keys = new Key(k);
		
		
		ctrModiDecrypt();
	}
	
	
	/*
	 * Method splits chiffre text into blocks of length of 16
	 * @param chiffreText string representation of the chiffre text
	 * @return arraylist with splitted blocks
	 * 
	 */
	public ArrayList<Integer> splitInputText(String input) {
		
		ArrayList<Integer> listOfSplittedBinary = new ArrayList<>();
		
		String[] temp = input.split("(?<=\\G.{16})");
		
		for(String e: temp) {
			listOfSplittedBinary.add(Integer.parseInt(e, 2));
		}
		
		return listOfSplittedBinary;
		
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
			encryptedList.put(i, encrypt((int) ((value + i) % Math.pow(2,l)), i) ^ yi+i);
			
		}
	}
	
	

	
	
	public int encrypt(int chiffre, int kKey){
		// Schritt 1: Chiffre xor Key
		int init = chiffre ^ keys.getKeyPartAsInt(kKey);
		
		// Schritt 2: S Box
		String convertedKey = Integer.toString(init,2);
		String aftersBox = "";
		for(int i= 0; i<4;i++){
			aftersBox += sbox.getSxBox(convertedKey);
		}
		
		// Schritt 3: Permutation
		if(kKey == 0 || kKey == 3)
			String x = permut.permutString(new StringBuilder(aftersBox));
		
		
		return x;
	}
	
	//3)
	// SPN Decrypt
	
		// Method S Box und Inverse
	
		// Methode BitPermutation 
	
		// Methode X-OR Calc

}
