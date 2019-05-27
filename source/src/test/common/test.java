package test.common;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class test {
	
	public static String md5(String input) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] messageDigest = md.digest(input.getBytes());
	    BigInteger number = new BigInteger(1, messageDigest);
	    return number.toString(16);
	}

	public String decrypt(String encryptedData, String initialVectorString, String secretKey) {
	    String decryptedData = null;
	    try {
	        SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
	        IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
	        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, initialVector);
	        byte[] encryptedByteArray = (new org.apache.commons.codec.binary.Base64()).decode(encryptedData.getBytes());
	        byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
	        decryptedData = new String(decryptedByteArray, "UTF8");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return decryptedData;
	}
	
	public String encrypt(String encryptedData, String initialVectorString, String secretKey) {
	    String decryptedData = null;
	    try {
	        SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
	        IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
	        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initialVector);
	        
	        byte[] stringBytes = encryptedData.getBytes("UTF8");
	        
	    	// encrypt using the cypher
	    	byte[] raw = cipher.doFinal(stringBytes);
	     
	    	// converts to base64 for easier display.
	    	BASE64Encoder encoder = new BASE64Encoder();
	    	String base64 = encoder.encode(raw);
	    	return base64;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return decryptedData;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String plainText = "test1234";
		String secretKeyString = "Encrypt with PHP Decrypt with Java";
		String initialVectorString = "0123456789123456";
		
		test obj = new test();
		String encryptedData = obj.encrypt(plainText,  initialVectorString, secretKeyString);
		System.out.println(encryptedData);
		String plainText2 = obj.decrypt(encryptedData, initialVectorString, secretKeyString);
		System.out.println(plainText2);
	}

}
