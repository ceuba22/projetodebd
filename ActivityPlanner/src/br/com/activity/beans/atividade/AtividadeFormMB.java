package br.com.activity.beans.atividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeTO;
import br.com.activity.beans.users.UsersMB;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.tag.dao.TagDAO;
import br.com.activity.tag.entidade.Tag;
import br.com.activity.users.to.UsersTO;

@ManagedBean
@ViewScoped
public class AtividadeFormMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 647442147590404223L;

	private AtividadeTO atividaTo;

	private List<Tag> listTag;

	private UsersTO usersTO;

	private long atividadeId;

	private UsersMB usersMB = (UsersMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usersMB");


	public AtividadeFormMB() {
		atividaTo = new AtividadeTO();

		setUsersTO(new UsersTO());
		usersTO.setNome(usersMB.getNome());
		usersTO.setId(usersMB.getId());
		usersTO.setCargo(usersMB.getCargo());
		usersTO.setManager(usersMB.isManager());
		loadListTag();
	}

	public void loadEditAtividade() throws ClassNotFoundException{
		Atividade atividade = ActivityFacade.getInstance().getAtividade(this.atividadeId);
		atividaTo = new AtividadeTO(atividade); 
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
				if(atividaTo.getId() != 0){
//criar metodo de update
				}else{
					ActivityFacade.getInstance().inserirAtividade(atividade);
				}
				cleanFormAtividade();
				requestContext.update("formPrincipal");
				setAtividaTo(new AtividadeTO());
			} catch (Exception e) {
			}
		}

	}


	public void loadListTag(){
		listTag = new ArrayList<Tag>();
		try {
			if(TagDAO.getInstance().listTags().size() > 0){
				for (Tag tag : ActivityFacade.getInstance().listTags()) {
					listTag.add(tag);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void cleanFormAtividade(){
		setAtividaTo(new AtividadeTO());
	}

	public AtividadeTO getAtividaTo() {
		return atividaTo;
	}

	public void setAtividaTo(AtividadeTO atividaTo) {
		this.atividaTo = atividaTo;
	}

	public List<Tag> getListTag() {
		return listTag;
	}

	public void setListTag(List<Tag> listTag) {
		this.listTag = listTag;
	}

	public UsersTO getUsersTO() {
		return usersTO;
	}

	public void setUsersTO(UsersTO usersTO) {
		this.usersTO = usersTO;
	}

	public long getAtividadeId() {
		return atividadeId;
	}

	public void setAtividadeId(long atividadeId) {
		this.atividadeId = atividadeId;
	}

}
