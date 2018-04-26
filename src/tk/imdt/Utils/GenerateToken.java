package tk.imdt.Utils;

import java.util.Random;

public class GenerateToken {
	/*
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			System.out.println(RandomString());
		}
	}
	*/
	public static String RandomString() {
		return RandomString(15);
	}
	public static String RandomString(int length) {
	    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random = new Random();
	    StringBuffer buf = new StringBuffer();
	    for (int i = 0; i < length; i++) {
	        int num = random.nextInt(str.length());
	        buf.append(str.charAt(num));
	    }
	    return buf.toString();
	}
}
