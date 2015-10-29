package br.com.activity.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;

import br.com.activity.beans.users.UsersMB;

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
	
	public long getUsersTOLogado(){
		UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");
		return usersMB.getId();
	}

	
    public Date formataData(String data) throws Exception {   
        if (data == null || data.equals(""))  
            return null;  
          
        Date date = null;  
        try {  
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
            date = (java.util.Date)formatter.parse(data);  
        } catch (ParseException e) {              
            throw e;  
        }  
        return date;  
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
