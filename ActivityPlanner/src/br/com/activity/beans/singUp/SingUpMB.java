package br.com.activity.beans.singUp;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.activity.facade.ActivityFacade;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;


@ViewScoped
@ManagedBean
public class SingUpMB implements Serializable {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 8302820868156967760L;
	
	private UsersTO newUsers;
	
	public SingUpMB(){
		setNewUsers(new UsersTO());
	}
	
	public void save(){
		Users users = newUsers.toVO();
		
		try {
			ActivityFacade.getInstance().saveUser(users);
			setNewUsers(new UsersTO());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UsersTO getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(UsersTO newUsers) {
		this.newUsers = newUsers;
	}
	

}
