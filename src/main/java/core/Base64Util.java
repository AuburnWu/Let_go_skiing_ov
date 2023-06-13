package core;

import java.util.Base64;

public class Base64Util {

	public static String encode(String input) {
		byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes());
		return new String(encodedBytes);
	}

	public static String decode(String encodedInput) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedInput.getBytes());
		return new String(decodedBytes);
	}
}
