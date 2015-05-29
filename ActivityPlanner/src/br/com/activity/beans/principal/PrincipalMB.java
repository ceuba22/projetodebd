package br.com.activity.beans.principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;

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
	
	private DualListModel<String> departamentos;
	
	//Lista temporária em String pois dados estão estaticos
	private List<String> departamentoSource;
	private List<String> departamentosTarget;
	
	public PrincipalMB(){
		loadBean();
		
	}
	
	public void loadBean() {
		List<String> departamentoSource = new ArrayList<String>();
		List<String> departamentosTarget = new ArrayList<String>();
		
		departamentoSource.add("San Francisco");
		departamentoSource.add("London");
		departamentoSource.add("Paris");
		departamentoSource.add("Istanbul");
		departamentoSource.add("Berlin");
		departamentoSource.add("Barcelona");
        departamentoSource.add("Rome");
        
        departamentos = new DualListModel<String>(departamentoSource, departamentosTarget);
        
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

	public DualListModel<String> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(DualListModel<String> departamentos) {
		this.departamentos = departamentos;
	}

	public List<String> getDepartamentoSource() {
		return departamentoSource;
	}

	public void setDepartamentoSource(List<String> departamentoSource) {
		this.departamentoSource = departamentoSource;
	}

	public List<String> getDepartamentosTarget() {
		return departamentosTarget;
	}

	public void setDepartamentosTarget(List<String> departamentosTarget) {
		this.departamentosTarget = departamentosTarget;
	}

}
