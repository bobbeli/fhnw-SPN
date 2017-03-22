import java.math.BigInteger;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class ProgrammieraufgabeSPN {


	public static void main(String[] args) {

		
		// Start Decrypt Chiffre Text
		//SPNDecrypter decrypted = new SPNDecrypter();
		
		//Testing SBox
		//testingSBox();
		
		//Testing Bitpermutation
		testingBitpermutation();
		
		//Testing Key
		//testingKeys();


		
	}
	
	public static void testingKeys(){
		String k = "00111010100101001101011000111111";
		Key keys = new Key(k);
		
		//System.out.println((keys.getKeyPartAsInt(0)));
		System.out.println((keys.getKeyPartAsInt(1)));
		System.out.println(Integer.toString(keys.getKeyPartAsInt(1),2));
		System.out.println(Integer.toString(keys.getKeyPartAsInt(2),2));
		
	}
	
	public static void testingSBox(){
		SBox sbox = new SBox("0123456789ABCDEF", "E4D12FB83A6C5907", false);

		System.out.println("Equasls 0100: " + sbox.getSxBox("0010"));
		System.out.println("Equasls 0011: " + sbox.getSxBox("0001"));
		System.out.println("Equasls 1101: " + sbox.getSxBox("1001"));
	}
	
	public static void testingBitpermutation(){
		Bitpermutation p = new Bitpermutation();
		
		System.out.println("Equals 1010101010101010: "+p.permutString(new StringBuilder("1111000011110000")));
	}
	

	


	

}
