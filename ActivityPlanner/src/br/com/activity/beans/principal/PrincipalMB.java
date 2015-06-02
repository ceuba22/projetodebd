package br.com.activity.beans.principal;

import java.io.IOException;
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

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeTO;
import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.projetos.to.ProjetosTO;
import br.com.activity.tag.dao.TagDAO;
import br.com.activity.tag.entidade.Tag;
import br.com.activity.tag.to.TagTO;
import br.com.activity.users.entidade.Users;
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

	private TagTO tagTO;

	private UsersTO newUsers;

	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");

	private DualListModel<String> departamentos;

	//Lista tempor�ria em String pois dados est�o estaticos
	private List<String> departamentoSource;
	private List<String> departamentosTarget;

	public PrincipalMB(){
		loadBean();
		atividaTo = new AtividadeTO();
		tagTO = new TagTO();
		newUsers = new UsersTO();

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

	public void cleanFormTag(){
		setTagTO(new TagTO());
	}

	public void cleanFormUsers(){
		setNewUsers(new UsersTO());
	}

	public void insertUsuario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		RequestContext requestContext = RequestContext.getCurrentInstance();

		if (newUsers.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Nome do Usu�rio est� vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getEmail().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Email do Usu�rio est� vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getCargo().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Cargo do Usu�rio est� vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getSenha().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Senha do Usu�rio est� vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if (!hasError) {
			Users users = usersTO.toVO();
			try {
				ActivityFacade.getInstance().saveUser(users);
				cleanFormUsers();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarUsuarioDialog').hide()");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void insertTag(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		RequestContext requestContext = RequestContext.getCurrentInstance();

		if(tagTO.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Nome da Tag est� vazio."));
			requestContext.update("formPrincipal:messageTag");
		}

		if(tagTO.getDescricao().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Descri��o da Tag est� vazio."));
			requestContext.update("formPrincipal:messageTag");
		}

		if(!hasError){
			try {
				Tag tag = tagTO.toVO();
				TagDAO.getInstance().inserirTag(tag);
				cleanFormTag();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarTagDialog').hide()");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void loadPageCadastroUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("public/SingUp.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertProjeto(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;



		if(!hasError){

		}
	}

	public void insertAtividade(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext requestContext = RequestContext.getCurrentInstance();

		boolean hasError = false;

		if(atividaTo.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Nome da atividade est� vazio."));
			requestContext.update("formPrincipal:messageAtividade");
		}

		if(atividaTo.getDescricao().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "Nome da descricao est� vazio."));
			requestContext.update("formPrincipal:messageAtividade");
		}
		if (atividaTo.getPeso() <= 0) {
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "O peso n�o pode ser igual ou inferior a 0."));
			requestContext.update("formPrincipal:messageAtividade");
		}

		if(atividaTo.getTempoExecucao() <= 0 ){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Aten��o", "O tempo de execucao n�o pode ser igual ou inferior a 0."));
			requestContext.update("formPrincipal:messageAtividade");
		}

		if(!hasError){
			try {
				Atividade atividade = atividaTo.toVo();
				ActivityFacade.getInstance().inserirAtividade(atividade);
				cleanFormAtividade();
				requestContext.update("formPrincipal");
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

	public TagTO getTagTO() {
		return tagTO;
	}

	public void setTagTO(TagTO tagTO) {
		this.tagTO = tagTO;
	}

	public UsersTO getNewUsers() {
		return newUsers;
	}

	public void setNewUsers(UsersTO newUsers) {
		this.newUsers = newUsers;
	}

}
