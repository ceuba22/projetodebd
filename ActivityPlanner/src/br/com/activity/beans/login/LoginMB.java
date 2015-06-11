package br.com.activity.beans.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;


@ManagedBean
@ViewScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -9175881404259491316L;
	
	private UsersTO userLogin;
	
	private UsersMB usuario;
	
	public LoginMB(){
		userLogin =  new UsersTO();
	}
		
	public void login(){
		FacesContext context = FacesContext.getCurrentInstance();
		Users users = userLogin.toVO();
		try {
			if(ActivityFacade.getInstance().isloginUsers(users)){
				setUserLogin(new UsersTO());
				usuario = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
				context.getExternalContext().redirect("public/principal.jsf"); 
			}else{
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Login ou senha incorretos, favor verificar dados digitados."));	
			}
		} catch (ClassNotFoundException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Login ou senha incorretos, favor verificar dados digitados."));
			e.printStackTrace();
		} catch (IOException e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Login ou senha incorretos, favor verificar dados digitados."));
			e.printStackTrace();
		}
	}

	public UsersTO getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UsersTO userLogin) {
		this.userLogin = userLogin;
	}

	public UsersMB getUsuario() {
		return usuario;
	}

	public void setUsuario(UsersMB usuario) {
		this.usuario = usuario;
	}


}
