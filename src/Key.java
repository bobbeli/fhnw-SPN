
public class Key {
	String key;
	int round;
	Bitpermutation permut;
	
	public Key(String key){
		this.key = key;
		this.permut = new Bitpermutation();
		
	}
	
	public int getKeyPartAsInt(int keyNumber){
		String keyNr = "";
		
		switch(keyNumber){
		case 0: keyNr = key.substring(16, 16+16);
			break;
		case 1: keyNr = permut.permutString(new StringBuilder(key.substring(12, 12+16)));
			break;
		case 2: keyNr = permut.permutString(new StringBuilder(key.substring(8, 8+16)));
			break;
		case 3: keyNr = permut.permutString(new StringBuilder(key.substring(4, 4+16)));
			break;
		case 4: keyNr = key.substring(0, 16);
		
		}
		return Integer.parseInt(keyNr, 2);
	}
	

	
	
}
