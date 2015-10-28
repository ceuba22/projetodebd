package br.com.activity.beans.insertUsersDepart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.grupo.to.GrupoTO;
import br.com.activity.users.dao.UsersDAO;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;


@ManagedBean
@ViewScoped
public class InsertUsuarioDepartamentoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6065531722255415018L;

	private List<GrupoTO> listGrupoTO;

	private GrupoTO selectedGrupo;

	private List<Users> listUserAutoComplete;

	private List<UsersTO> listUserByGrupo;
	
	private List<Users> listUsers;

	private long selectedUser;

	private boolean active;



	public InsertUsuarioDepartamentoMB(){
		try {
			setActive(false);
			selectedGrupo = new GrupoTO();
			listUsers = new ArrayList<Users>();
			for (Users user : UsersDAO.getInstance().listUsuarios()) {
				listUsers.add(user);
				
			}
			loadListGrupo();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void cleanFormDepartamento(){
		selectedGrupo = new GrupoTO();
		selectedUser = 0;
		setActive(false);

	}

	public void addUsuarioAoDepto(ActionEvent event) throws ClassNotFoundException{
		RequestContext requestContext = RequestContext.getCurrentInstance();
		if(!GrupoDAO.getInstance().jaPossuiUsuarioNoGrupo(selectedUser, selectedGrupo.getId())){
			GrupoDAO.getInstance().addUsuarioAoDepto(selectedUser, selectedGrupo.getId());
			selectedUser = 0;
			listarUsuariosPorGrupo(selectedGrupo);
			requestContext.update("formPrincipal");
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Colaborador já é membro do departamento."));
		}
	}

	public List<Users> listUsuariosPorLike(String query) throws ClassNotFoundException{
		listUserAutoComplete = new ArrayList<Users>();
		if(UsersDAO.getInstance().listUsuariosPorLike(query).size() > 0){
			for (Users usuario : UsersDAO.getInstance().listUsuariosPorLike(query)) {
				listUserAutoComplete.add(usuario);
			}

		}
		return listUserAutoComplete;
	}

	public void loadDialogAddUsers(ActionEvent event) throws ClassNotFoundException {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		selectedGrupo = (GrupoTO) event.getComponent().getAttributes().get("selectedGrupo");
		listUserByGrupo = new ArrayList<UsersTO>();
		if(selectedGrupo.getId() != 0){
			listarUsuariosPorGrupo(selectedGrupo);
			setActive(true);
			requestContext.update("formPrincipal");

		}else{
			listarUsuariosPorGrupo(selectedGrupo);
			setActive(true);
			requestContext.update("formPrincipal");
		}

	}

	public void listarUsuariosPorGrupo(GrupoTO grupo)throws ClassNotFoundException{
		listUserByGrupo = new ArrayList<UsersTO>();
		for (Users usuario : UsersDAO.getInstance().listUsuariosPorGrupo(grupo.getId())) {
			listUserByGrupo.add(new UsersTO(usuario));

		}
	}

	public void loadListGrupo()  throws ClassNotFoundException{
		listGrupoTO = new ArrayList<GrupoTO>();
		if(GrupoDAO.getInstance().listGrupos().size() > 0){
			for (Grupo grupo : GrupoDAO.getInstance().listGrupos()) {
				listGrupoTO.add(new GrupoTO(grupo));
			}
		}
	}

	public List<GrupoTO> getListGrupoTO() {
		return listGrupoTO;
	}

	public void setListGrupoTO(List<GrupoTO> listGrupoTO) {
		this.listGrupoTO = listGrupoTO;
	}

	public GrupoTO getSelectedGrupo() {
		return selectedGrupo;
	}

	public void setSelectedGrupo(GrupoTO selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
	}

	public long getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(long selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<Users> getListUserAutoComplete() {
		return listUserAutoComplete;
	}

	public void setListUserAutoComplete(List<Users> listUserAutoComplete) {
		this.listUserAutoComplete = listUserAutoComplete;
	}

	public List<UsersTO> getListUserByGrupo() {
		return listUserByGrupo;
	}

	public void setListUserByGrupo(List<UsersTO> listUserByGrupo) {
		this.listUserByGrupo = listUserByGrupo;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

}
