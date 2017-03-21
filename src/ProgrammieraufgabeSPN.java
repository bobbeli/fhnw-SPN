import java.math.BigInteger;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class ProgrammieraufgabeSPN {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Gegeben:
		/*
		 * SBox-Umwandlung
		 * Bit-Permutation
		 * Runden r
		 * 
		 */
		
		// Start Decrypt Chiffre Text
		new SPNDecrypter();
		//System.out.println(hexToBin("D"));
		
	}
	
	public static String hexToBin(String s) {
		  return new BigInteger(s, 16).toString(2);
	}
	


	

}
