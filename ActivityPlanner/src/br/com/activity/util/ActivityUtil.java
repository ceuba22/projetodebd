package br.com.activity.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ActivityUtil {

	private static ActivityUtil instance;

	public static ActivityUtil getInstance() {
		if (instance == null) {
			instance = new ActivityUtil();
		}
		return instance;
	} 

	public ActivityUtil(){

	}

	public String md5(String senha){  
		String sen = "";  
		MessageDigest md = null;  
		try {  
			md = MessageDigest.getInstance("MD5");  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		}  
		BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));  
		sen = hash.toString(16);              
		return sen;  
	}  
}
