package br.com.activity.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ViewScoped
@ManagedBean
public class HelloMB {

	@PostConstruct 
	public void init(){ 
		System.out.println(" Bean executado! "); 
	} 

	public String getMessage(){
		return "Hello World JSF!"; 
	} 


}
