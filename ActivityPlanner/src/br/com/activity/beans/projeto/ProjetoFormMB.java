package br.com.activity.beans.projeto;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.projetos.to.ProjetoTO;
import br.com.activity.users.to.UsersTO;
import br.com.activity.util.StatusTipo;

@ManagedBean
@ViewScoped
public class ProjetoFormMB implements Serializable {
	
	private static final long serialVersionUID = 6100456552860528688L;

	private ProjetoTO projetoTO;
	private List<Atividade> atividadeSource;
	private List<String> atividadeTarget;
	private List<ProjetoTO> listProjetoTO;
	private List<Grupo> departamentoSource;
	private List<String> departamentosTarget;
	private UsersTO usersTO;

	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");


	public ProjetoFormMB() {
		departamentoSource = new ArrayList<Grupo>();
		departamentosTarget = new ArrayList<String>();
		atividadeSource = new ArrayList<Atividade>();
		atividadeTarget = new ArrayList<String>();
	}

	public void loadBean(){

		projetoTO = new ProjetoTO();
		listProjetoTO = new ArrayList<ProjetoTO>();
		setUsersTO(new UsersTO());
		usersTO.setNome(usersMB.getNome());
		usersTO.setId(usersMB.getId());
		usersTO.setCargo(usersMB.getCargo());
		usersTO.setManager(usersMB.isManager());
		loadListAtividade();
		loadListGrupos();

	}

	public void insertProjeto(ActionEvent event) throws ClassNotFoundException, IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext requestContext = RequestContext.getCurrentInstance();

		boolean hasError = false;

		//		if(ProjetoTO.getNome().trim().isEmpty()){
		//			hasError = true;
		//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome do Projeto está vazio."));
		//			requestContext.update("formPrincipal:messageTag");
		//		}


		if(!hasError){
			Projeto projetos = projetoTO.toVO();
			projetos.setStatus(StatusTipo.ABERTO);
			for (String atividadeId : getAtividadeTarget()) {
				projetos.getListAtividade().add(AtividadeDAO.getInstance().getAtividade(Long.valueOf(atividadeId))) ;
			}
			
			for (String departamentoId : getDepartamentosTarget()) {
				projetos.getListGrupo().add(GrupoDAO.getInstance().getGrupo(Long.valueOf(departamentoId))) ;
			}
			try {
				ActivityFacade.getInstance().inserirProjeto(projetos);
				loadListProjetos();
				projetoTO = new ProjetoTO();
				requestContext.update("formPrincipal");
				context.getExternalContext().redirect("inicio");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	public void loadListProjetos(){
		try {
			if(ActivityFacade.getInstance().listProjetos().size() > 0){
				listProjetoTO = new ArrayList<ProjetoTO>();
				for (Projeto projetos : ActivityFacade.getInstance().listProjetos()) {
					listProjetoTO.add(new ProjetoTO(projetos));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadListAtividade(){
		try {
			if (ActivityFacade.getInstance().listAtividades().size() > 0){
				for (Atividade atividade : ActivityFacade.getInstance().listAtividades()) {
					atividadeSource.add(atividade);

				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void loadListGrupos(){
		try {
			if(ActivityFacade.getInstance().listGrupos().size() > 0){
				departamentoSource = new ArrayList<Grupo>();
				for (Grupo grupo : ActivityFacade.getInstance().listGrupos()) {
					departamentoSource.add(grupo);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public ProjetoTO getProjetoTO() {
		return projetoTO;
	}

	public void setProjetoTO(ProjetoTO projetoTO) {
		this.projetoTO = projetoTO;
	}
	public List<Atividade> getAtividadeSource() {
		return atividadeSource;
	}

	public void setAtividadeSource(List<Atividade> atividadeSource) {
		this.atividadeSource = atividadeSource;
	}

	public List<String> getAtividadeTarget() {
		return atividadeTarget;
	}

	public void setAtividadeTarget(List<String> atividadeTarget) {
		this.atividadeTarget = atividadeTarget;
	}

	public List<ProjetoTO> getListProjetoTO() {
		return listProjetoTO;
	}

	public void setListProjetoTO(List<ProjetoTO> listProjetoTO) {
		this.listProjetoTO = listProjetoTO;
	}

	public List<Grupo> getDepartamentoSource() {
		return departamentoSource;
	}

	public void setDepartamentoSource(List<Grupo> departamentoSource) {
		this.departamentoSource = departamentoSource;
	}

	public List<String> getDepartamentosTarget() {
		return departamentosTarget;
	}

	public void setDepartamentosTarget(List<String> departamentosTarget) {
		this.departamentosTarget = departamentosTarget;
	}

	public UsersTO getUsersTO() {
		return usersTO;
	}

	public void setUsersTO(UsersTO usersTO) {
		this.usersTO = usersTO;
	}

	public UsersMB getUsersMB() {
		return usersMB;
	}

	public void setUsersMB(UsersMB usersMB) {
		this.usersMB = usersMB;
	}
}
