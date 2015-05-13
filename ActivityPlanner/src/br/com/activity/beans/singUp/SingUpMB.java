package br.com.activity.beans.singUp;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.activity.entidade.users.Users;
import br.com.activity.entidade.users.UsersDAO;
import br.com.activity.to.users.UsersTO;


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
		UsersDAO.getInstance().save(users);
	}

	public UsersTO getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(UsersTO newUsers) {
		this.newUsers = newUsers;
	}
	

}
