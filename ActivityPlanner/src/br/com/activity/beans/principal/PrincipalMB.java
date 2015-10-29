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
import br.com.activity.beans.projeto.ProjetoMB;
import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.grupo.to.GrupoTO;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.projetos.to.ProjetoTO;
import br.com.activity.tag.dao.TagDAO;
import br.com.activity.tag.entidade.Tag;
import br.com.activity.tag.to.TagTO;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;
import br.com.activity.util.StatusTipo;


@ManagedBean
@ViewScoped
public class PrincipalMB  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100456552860528688L;

	private UsersTO usersTO;

	private AtividadeTO atividaTo;

	private ProjetoTO projetosTO;

	private TagTO tagTO;

	private UsersTO newUsers;

	private GrupoTO grupoTO;

	private List<Tag> listTag;

	private List<Grupo> departamentoSource;

	private List<Grupo> departamentosTarget; 

	private DualListModel<Grupo> departamentos;

	private List<Atividade> atividadeSource;

	private List<Atividade> atividadeTarget;

	private DualListModel<Atividade> atividades;

	private List<ProjetoTO> listProjetoTO;
	
	private ProjetoTO selectedProjetoTO;


	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");



	public PrincipalMB(){
		listTag = new ArrayList<Tag>();
		atividaTo = new AtividadeTO();
		tagTO = new TagTO();
		newUsers = new UsersTO();
		grupoTO = new GrupoTO();
		projetosTO = new ProjetoTO();
		departamentoSource = new ArrayList<Grupo>();
		departamentosTarget = new ArrayList<Grupo>();
		atividadeSource = new ArrayList<Atividade>();
		atividadeTarget = new ArrayList<Atividade>();
		listProjetoTO = new ArrayList<ProjetoTO>();
		selectedProjetoTO = new ProjetoTO();
		loadBean();
	}


	public void loadBean() {
		setUsersTO(new UsersTO());
		usersTO.setNome(usersMB.getNome());
		usersTO.setId(usersMB.getId());
		usersTO.setCargo(usersMB.getCargo());
		usersTO.setManager(usersMB.isManager());
		loadListAtividade();
		loadListGrupos();
		loadListProjeto();
		loadListTag();

		departamentos = new DualListModel<Grupo>(departamentoSource, departamentosTarget);
		atividades = new DualListModel<Atividade>(atividadeSource, atividadeTarget);

	}

	public void loadListTag(){
		try {
			if(TagDAO.getInstance().listTags().size() > 0){
				listTag.addAll(TagDAO.getInstance().listTags());
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

	public void loadListProjeto(){
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
	
	public void redirectDepartamentoUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("departamento/inserirUsuario-Departamento.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void redirectMonitoramento() throws IOException{
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("monitoramento/monitoramentoDetalhe.jsf");
	}
	
	public void redirectProjetoUsuario(ProjetoTO projeto){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ProjetoMB projetoMB = new ProjetoMB();
			projetoMB.setId(projeto.getId());
			projetoMB.setDescricao(projeto.getDescricao());
			projetoMB.setCriadoEm(projeto.getCriadoEm());
			projetoMB.setNome(projeto.getNome());
			projetoMB.setPrioridade(projeto.getPrioridade());
			projetoMB.setListGrupo(projeto.getListGrupo());
			projetoMB.setStatus(projeto.getStatus());
			projetoMB.setListAtividade(projeto.getListAtividade());
			projetoMB.setCriadoPor(projeto.getCriadoPor());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projetoMB", projetoMB);
			context.getExternalContext().redirect("projeto/inserirUsuario-projeto.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void redirectAtividade(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("atividade/atividadesViews.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertDepartamento(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		RequestContext requestContext = RequestContext.getCurrentInstance();

		if(grupoTO.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome do Departamento está vazio."));
			requestContext.update("formPrincipal:messageUsuario");
		}

		if(!hasError){
			Grupo grupo = grupoTO.toVO();
			try {
				GrupoDAO.getInstance().insertGrupo(grupo);
				cleanFormDepartamento();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarDepartamentoDialog').hide()");
			} catch (Exception e) {
			}

		}
	}

	public void insertUsuario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		boolean hasError = false;
		RequestContext requestContext = RequestContext.getCurrentInstance();

		if (newUsers.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome do Usuário está vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getEmail().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Email do Usuário está vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getCargo().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Cargo do Usuário está vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if(newUsers.getSenha().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Senha do Usuário está vazio."));
			requestContext.update("formPrincipal:messageUsuario");

		}

		if (!hasError) {
			Users users = newUsers.toVO();
			try {
				ActivityFacade.getInstance().saveUser(users);
				cleanFormUsers();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarUsuarioDialog').hide()");
			} catch (ClassNotFoundException e) {
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
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome da Tag está vazio."));
			requestContext.update("formPrincipal:messageTag");
		}

		if(tagTO.getDescricao().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Descrição da Tag está vazio."));
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
				e.printStackTrace();
			}

		}
	}

	public void loadPageCadastroUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("public/SingUp.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertProjeto(ActionEvent event){
		@SuppressWarnings("unused")
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext requestContext = RequestContext.getCurrentInstance();

		boolean hasError = false;

		//		if(projetosTO.getNome().trim().isEmpty()){
		//			hasError = true;
		//			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome do Projeto está vazio."));
		//			requestContext.update("formPrincipal:messageTag");
		//		}


		if(!hasError){
			Projeto projetos = projetosTO.toVO();
			projetos.setStatus(StatusTipo.ABERTO);
			projetos.setListAtividade(getAtividades().getTarget());
			projetos.setListGrupo(getDepartamentos().getTarget());
			try {
				ActivityFacade.getInstance().inserirProjeto(projetos);
				loadListProjeto();
				requestContext.update("formPrincipal");
				requestContext.execute("PF('criarProjetoDialog').hide()");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	public void insertAtividade(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext requestContext = RequestContext.getCurrentInstance();

		boolean hasError = false;

		if(atividaTo.getNome().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome da atividade está vazio."));
			requestContext.update("formPrincipal:messageAtividade");
		}

		if(atividaTo.getDescricao().trim().isEmpty()){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "Nome da descricao está vazio."));
			requestContext.update("formPrincipal:messageAtividade");
		}
		if (atividaTo.getPeso() <= 0) {
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "O peso não pode ser igual ou inferior a 0."));
			requestContext.update("formPrincipal:messageAtividade");
		}

		if(atividaTo.getTempoExecucao() <= 0 ){
			hasError = true;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Atenção", "O tempo de execucao não pode ser igual ou inferior a 0."));
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
			}
		}

	}

	public void cleanFormAtividade(){
		setAtividaTo(new AtividadeTO());
	}

	public void cleanFormProjeto(){
		setProjetoTO(new ProjetoTO());
	}

	public void cleanFormTag(){
		setTagTO(new TagTO());
	}

	public void cleanFormUsers(){
		setNewUsers(new UsersTO());
	}

	public void cleanFormDepartamento(){
		setGrupoTO(new GrupoTO());
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

	public DualListModel<Grupo> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(DualListModel<Grupo> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Grupo> getDepartamentoSource() {
		return departamentoSource;
	}

	public void setDepartamentoSource(List<Grupo> departamentoSource) {
		this.departamentoSource = departamentoSource;
	}

	public List<Grupo> getDepartamentosTarget() {
		return departamentosTarget;
	}

	public void setDepartamentosTarget(List<Grupo> departamentosTarget) {
		this.departamentosTarget = departamentosTarget;
	}

	public AtividadeTO getAtividaTo() {
		return atividaTo;
	}

	public void setAtividaTo(AtividadeTO atividaTo) {
		this.atividaTo = atividaTo;
	}

	public ProjetoTO getProjetoTO() {
		return projetosTO;
	}

	public void setProjetoTO(ProjetoTO projetosTO) {
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

	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}

	public List<Tag> getListTag() {
		return listTag;
	}

	public void setListTag(List<Tag> listTag) {
		this.listTag = listTag;
	}

	public List<Atividade> getAtividadeSource() {
		return atividadeSource;
	}

	public void setAtividadeSource(List<Atividade> atividadeSource) {
		this.atividadeSource = atividadeSource;
	}

	public List<Atividade> getAtividadeTarget() {
		return atividadeTarget;
	}

	public void setAtividadeTarget(List<Atividade> atividadeTarget) {
		this.atividadeTarget = atividadeTarget;
	}

	public DualListModel<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(DualListModel<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<ProjetoTO> getListProjetoTO() {
		return listProjetoTO;
	}

	public void setListProjetoTO(List<ProjetoTO> listProjetoTO) {
		this.listProjetoTO = listProjetoTO;
	}


	public ProjetoTO getSelectedProjetoTO() {
		return selectedProjetoTO;
	}


	public void setSelectedProjetoTO(ProjetoTO selectedProjetoTO) {
		this.selectedProjetoTO = selectedProjetoTO;
	}


}
