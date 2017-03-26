import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.ws.util.StringUtils;


public class SPNDecrypter {


	public int l = 16;
	public String k;
	ArrayList<String> listOfSplittedBinary;
	Map<Integer, String> encryptedList;
	SBox sbox;
	Bitpermutation permut;
	
	public SPNDecrypter () {
		// Erstellt ein Bitpermutations Object.
		// Auf diesem lässt sich ein Bin String der Laenge 16 Bermutieren
		permut = new Bitpermutation();
		
		// Initalier Chiffre Text wird in 16 Bit grosse Teilstuecke unterteilt
		String chiffreText = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
	
		listOfSplittedBinary = splitInputText(chiffreText);
		
		// Erstellt ein S-Box Object 
		// Map mit Key (s), Value (sx) in Binary Form
		// Fuer Decryption gibt man als dritter Paramter False mit
		sbox = new SBox("0123456789ABCDEF", "E4D12FB83A6C5907", false);
		
		// Erstellt ein Key Object 
		// enthaelt eine Methode zum Abrufen eines Key 
		// z.B k1 = getKeyPartAsInt(1)
		k = "00111010100101001101011000111111";
		Key keys = new Key(k);
		
		// Wiederspiegelt den CTR Modi
		 ctrModiDecrypt(keys);
		
		// Test SPN
		String kTest = "00010001001010001000110000000000";
		String testChiffreY = "1010111010110100"; 
		Key testKey = new Key(kTest);
		String testResX = encrypt(testChiffreY, testKey);
		
		System.out.println("Entschlüsselungs Test: ");
		System.out.println(testResX);
		System.out.println("0001001010001111");
	}
	

	
	/**
	 * Diese Methode wiederspiegelt den CTR Modi
	 */
	public void ctrModiDecrypt (Key keys) {
		encryptedList = new HashMap();
		
		
		// Fuehrt für jeden Chiffretext den encrypt Algorithmus (SPN) durch
		for(int i = 0; i < listOfSplittedBinary.size()-1; i++){
			
			int yiAsInt = (Integer.parseInt(listOfSplittedBinary.get(i), 2) + i);
			String yi = String.format("%" + 16 + "s", Integer.toBinaryString(yiAsInt)).replace(" ", "0");
			
			String encryptedVal = xOr( encrypt( yi, keys ) ,  listOfSplittedBinary.get(i+1));
			encryptedList.put(i, encryptedVal);
		}
		printEncryptedVals();
	

	}
	
	/**
	 * Diese Methode wiederspiegelt die SPN funktionalitate
	 * @param chiffre value von ChiffreText
	 * @return decrypted value 
	 */
	public String encrypt(String chiffre, Key keys){
		// Initialer Weissschritt: XOR Key 0
		String keyinit = keys.getKeyPartAsInt(0);
		String nachXor = xOr(chiffre, keyinit);
		
		String nachSBox;
		String valBinary; 
		
		// Regulaere Runden (2-4)
		for(int key = 1; key < 4; key++) {
			
			valBinary = fillZeros(nachXor);
			
			// SBox
			nachSBox = sbox.getSxBox(valBinary);
			
			// String Permutation
			String nachPermutation = permut.permutStringInv(new StringBuilder(nachSBox));
			
			
			// XOR mit entsprechendem Key
			// Key 1,2,3 sind bereits permutiert
			nachXor = xOr(nachPermutation, keys.getKeyPartAsInt(key));
		}
		
		//Verkuerzte letzte Runde 4
		valBinary = fillZeros(nachXor);
		
		// SBox
		nachSBox = sbox.getSxBox(valBinary);
		
		// XOR mit Key 4
		String decryptedVal = xOr(nachSBox, keys.getKeyPartAsInt(4));
		return decryptedVal;	
	}
	/**
	 * Xor von zwei Strings
	 * @param a
	 * @param b
	 * @return
	 */
	public static String xOr(String x, String y) {
		byte[] xString = x.getBytes();
		byte[] yString = y.getBytes();
		
		byte[] array = new byte[xString.length];

		for (int i = 0; i < array.length; i++) {
			array[i] = (byte) (xString[i] ^ yString[i]);
		}
		
		return Arrays.toString(array).replaceAll("\\[|\\]|,|\\s", "");
	}
	
	/**
	 * Method splitted Chiffre Text in 16 Bit lange Int Vals
	 * @param chiffreText string
	 * @return arraylist mit 16 Bit langen Int 
	 * 
	 */
	public ArrayList<String> splitInputText(String input) {
		
		ArrayList<String> listOfSplittedBinary = new ArrayList<>();
		
		String[] temp = input.split("(?<=\\G.{16})");
		
		for(String e: temp) {
			listOfSplittedBinary.add(e);
		}
		
		return listOfSplittedBinary;
		
	}
	
	/**
	 * Füllt ein String mit 0 auf bis dieser lengt = 16 hat
	 * @param fillIT
	 * @return string mit length = 16
	 */
	public String fillZeros(String fillIT){
		if(fillIT.length() < 16){
			String zero = "0";
			while(fillIT.length() < 16){
				zero += fillIT;
				fillIT = zero;
				zero = "0";
			}
		}
		return fillIT;
	}
	
	/**
	 * Printed entschlüsselte Werte 
	 */
	public void printEncryptedVals(){
		 System.out.println();
		 System.out.println();
		 System.out.println("Resultat: ");
		 int listLengt = encryptedList.size();
		 
		 
		 for (Map.Entry entry : encryptedList.entrySet()) {
			 
			    String val = (String) entry.getValue();    			    
			    
				String[] temp = val.split("(?<=\\G.{8})");
				
				for(String res : temp){
					System.out.println(res);

					char finalres = (char)Integer.parseInt(res, 2 );
					System.out.println("Char Value: "+finalres);
					System.out.println();
				}

			}
			
	 }

	
}
