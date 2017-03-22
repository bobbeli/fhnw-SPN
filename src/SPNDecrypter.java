import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.ws.util.StringUtils;


public class SPNDecrypter {

	String chiffreText = "00000100110100100000101110111000000000101000111110001110011111110110000001010001010000111010000000010011011001110010101110110000";
	
	public int l = 16;
	public String k;
	ArrayList<Integer> listOfSplittedBinary;
	Map<Integer, Integer> encryptedList;
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
		
		// Key Object with all Key of every round
		k = "00111010100101001101011000111111";
		keys = new Key(k);
		
		// Fills decrypted Values form SPN to encryptedList
		ctrModiDecrypt();
	}
	

	
	/*
	 * This Methdo handles Modi Decryption
	 */
	public void ctrModiDecrypt () {
		encryptedList = new HashMap();
		
		// get random number [y-1] from chiffre text 
		int yi = listOfSplittedBinary.get(0);
		
		listOfSplittedBinary.remove(0);
		

		for(int i = 0; i < listOfSplittedBinary.size(); i++){
			int tt = listOfSplittedBinary.get(i);
			encryptedList.put(i, encrypt( (yi + i) % (int) Math.pow(2,16)) ^  listOfSplittedBinary.get(i));
		}
		printEncryptedVals();
	

	}
	

	public int encrypt(int chiffre){
		// Initialer Weissschritt: Chiffre xor Key
		int valk0 = chiffre ^ keys.getKeyPartAsInt(0);
		System.out.println("Initiale Runde");
		String afterFirstSBox;
		String valk0Binary; 
		int temp;
		
		//Reguläre Runden
		for(int key = 1; key < 4; key++) {
			
			valk0Binary = Integer.toString(valk0,2);
			valk0Binary = fillZeros(valk0Binary);
			afterFirstSBox = sbox.getSxBox(valk0Binary);
			String dimi = permut.permutString(new StringBuilder(afterFirstSBox));
			temp = Integer.parseInt(dimi, 2);
			valk0 = temp ^ keys.getKeyPartAsInt(key);
		}
		
		//Verkuerzte letzte Runde
		valk0Binary = Integer.toString(valk0,2);
		
		valk0Binary = fillZeros(valk0Binary);
		// TODO Binari valk0Binar -> Binary with zero left. 
		afterFirstSBox = sbox.getSxBox(valk0Binary);
		
		return Integer.parseInt(afterFirstSBox, 2) ^ keys.getKeyPartAsInt(4);	
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
	
	public void printEncryptedVals(){
		 System.out.println();
		 System.out.println();
		 System.out.println("Resultat: ");
		 for (Map.Entry entry : encryptedList.entrySet()) {
			    int val = (int)entry.getValue();
			    String valBin = Integer.toString(val, 2);
				String[] temp = valBin.split("(?<=\\G.{8})");
				
				for(String res : temp){
					char finalres = (char)Integer.parseInt(res, 2 );
					System.out.print(finalres);
				}


			}
	 }

	
}
