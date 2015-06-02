package br.com.activity.beans.principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeTO;
import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.projetos.to.ProjetosTO;
import br.com.activity.users.to.UsersTO;


@ManagedBean
@ViewScoped
public class PrincipalMB  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100456552860528688L;

	private UsersTO usersTO;

	private AtividadeTO atividaTo;
	
	private ProjetosTO projetosTO;

	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");

	private DualListModel<String> departamentos;

	//Lista temporária em String pois dados estão estaticos
	private List<String> departamentoSource;
	private List<String> departamentosTarget;

	public PrincipalMB(){
		loadBean();
		atividaTo = new AtividadeTO();

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
	public void cleanFormAtividade(){
		setAtividaTo(new AtividadeTO());
	}
	
	public void cleanFormProjeto(){
		setProjetosTO(new ProjetosTO());
	}
	
	public void insertProjeto(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		
		
		if(hasError){
			
		}
	}

	public void insertAtividade(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		
		if(atividaTo.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome da atividade está vazio."));
		}
		
		if(atividaTo.getDescricao().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome da descricao está vazio."));
		}
		if (atividaTo.getPeso() <= 0) {
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "O peso não pode ser igual ou inferior a 0."));
		}
		
		if(atividaTo.getTempoExecucao() <= 0 ){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "O tempo de execucao não pode ser igual ou inferior a 0."));
		}

		Atividade atividade = atividaTo.toVo();
		if(!hasError){
			try {
				ActivityFacade.getInstance().inserirAtividade(atividade);
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.execute("PF('criarAtividadeDialog').hide();");
				setAtividaTo(new AtividadeTO());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

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

	public AtividadeTO getAtividaTo() {
		return atividaTo;
	}

	public void setAtividaTo(AtividadeTO atividaTo) {
		this.atividaTo = atividaTo;
	}

	public ProjetosTO getProjetosTO() {
		return projetosTO;
	}

	public void setProjetosTO(ProjetosTO projetosTO) {
		this.projetosTO = projetosTO;
	}

}
