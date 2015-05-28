package br.com.activity.beans.principal;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.activity.beans.users.UsersMB;
import br.com.activity.users.to.UsersTO;


@ManagedBean
@ViewScoped
public class PrincipalMB  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100456552860528688L;
	
	private UsersTO usersTO;
	
	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");
	
	public PrincipalMB(){
		loadBean();
		
	}
	
	public void loadBean() {
		setUsersTO(new UsersTO());
		usersTO.setNome(usersMB.getNome());
		usersTO.setId(usersMB.getId());
		usersTO.setCargo(usersMB.getCargo());
		usersTO.setManager(usersMB.isManager());
		
	}

	public UsersMB getUsuario() {
		return usersMB;
	}

	public void setUsuario(UsersMB usersMB) {
		this.usersMB = usersMB;
	}

	public UsersTO getUsersTO() {
		return usersTO;
	}

	public void setUsersTO(UsersTO usersTO) {
		this.usersTO = usersTO;
	}

}
