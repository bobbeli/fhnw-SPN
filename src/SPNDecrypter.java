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
		
		System.out.println("Kontrolle: " + keys.getKeyPartAsInt(0));
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
	
	//encryptedList.put(i, encrypt((int) ((value + i) % Math.pow(2,l)), i) ^ yi+i);
	/**
	 * This Methdo handles Modi Decryption
	 */
	public void ctrModiDecrypt () {
		encryptedList = new HashMap();
		
		// get random number [y-1] from chiffre text 
		int yi = listOfSplittedBinary.get(0);
		
		listOfSplittedBinary.remove(0);
		

		for(int i = 0; i < listOfSplittedBinary.size(); i++){
			encryptedList.put(i, encrypt( (yi + i) % (int) Math.pow(2,16)) ^ listOfSplittedBinary.get(i));
		}

	}


	public int encrypt(int chiffre){
		// Initialer Weissschritt: Chiffre xor Key
		int valk0 = chiffre ^ keys.getKeyPartAsInt(0);
		
		String afterFirstSBox;
		String valk0Binary; 
		int temp;
		
		//Reguläre Runden
		for(int key = 1; key < 4; key++) {
			valk0Binary = Integer.toString(valk0,2);
			afterFirstSBox = sbox.getSxBox(valk0Binary);
			String dimi = permut.permutString(new StringBuilder(afterFirstSBox));
			temp = Integer.parseInt(dimi, 2);
			valk0 = temp ^ keys.getKeyPartAsInt(key);
		}
		
		
		//Verkürzte letzte Runde
		valk0Binary = Integer.toString(valk0,2);
		afterFirstSBox = sbox.getSxBox(valk0Binary);
		
		return Integer.parseInt(afterFirstSBox, 2)^ keys.getKeyPartAsInt(4);	
	}

}
