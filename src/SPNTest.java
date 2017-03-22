import junit.framework.TestCase;

public class SPNTest extends TestCase {
	Key keys;
	static SBox sbox;
	
	protected void setup(){
		String k = "00111010100101001101011000111111";
		keys = new Key(k);
		sbox = new SBox("0123456789ABCDEF", "E4D12FB83A6C5907", false);
	}
	
	public void testingKeys(){
		
		
		System.out.println((keys.getKeyPartAsInt(0)));
		System.out.println((keys.getKeyPartAsInt(1)));
		System.out.println(Integer.toString(keys.getKeyPartAsInt(1),2));
		System.out.println(Integer.toString(keys.getKeyPartAsInt(2),2));
		
	}
	
	public static void testingSBox(){
		

		System.out.println("Equasls 0100: " + sbox.getSxBox("0010"));
		System.out.println("Equasls 0011: " + sbox.getSxBox("0001"));
		System.out.println("Equasls 1101: " + sbox.getSxBox("1001"));
	}
	
	public static void testingBitpermutation(){
		Bitpermutation p = new Bitpermutation();
		
		System.out.println("Equals 1111000000001111: "+p.permutString(new StringBuilder("0100110101100011")));
	}
	
	

}
