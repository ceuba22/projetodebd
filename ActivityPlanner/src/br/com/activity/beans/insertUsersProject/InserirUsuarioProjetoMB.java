package br.com.activity.beans.insertUsersProject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividadeAlocada.to.AtividadeAlocadaTO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.projetos.dao.ProjetosDAO;
import br.com.activity.projetos.entidade.Projeto;
import br.com.activity.projetos.to.ProjetoTO;
import br.com.activity.users.dao.UsersDAO;
import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;

@ManagedBean
@ViewScoped
public class InserirUsuarioProjetoMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2399322228195378573L;

	private ProjetoTO projetoTO;

	private AtividadeAlocadaTO selectedAtividadeAlocadaTO;

	private long atividadeID;


	private long usersID;

	private List<UsersTO> listUsuariosDisponiveis;

	private List<AtividadeAlocadaTO> listAtividadeAlocadaTO;

	//	private ProjetoMB projetoMB = (ProjetoMB)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projetoMB");

	private long projetoId;

	public InserirUsuarioProjetoMB() throws ClassNotFoundException{
		//		loadProject();
	}

	public void loadBean() throws ClassNotFoundException{
		selectedAtividadeAlocadaTO = new AtividadeAlocadaTO();
		loadlistUsuariosPorGrupos();
		loadListAtividadesAlocadas();
	}

	public void loadProject(){
		try {
			Projeto projeto = ProjetosDAO.getInstance().getProjetoById(this.projetoId);
			projetoTO = new ProjetoTO();
			projetoTO.setId(projeto.getId());
			projetoTO.setDescricao(projeto.getDescricao());
			projetoTO.setCriadoEm(projeto.getCriadoEm());
			projetoTO.setNome(projeto.getNome());
			projetoTO.setPrioridade(projeto.getPrioridade());
			projetoTO.setListGrupo(projeto.getListGrupo());
			projetoTO.setStatus(projeto.getStatus());
			projetoTO.setListAtividade(projeto.getListAtividade());
			projetoTO.setCriadoPor(new UsersTO(projeto.getCriadoPor()));
			projetoTO.setListAtividadesAlocadas(projeto.getListAtividadeAlocada());
			loadBean();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadListAtividadesAlocadas(){
		listAtividadeAlocadaTO = new ArrayList<AtividadeAlocadaTO>();
		try {
			if(AtividadeDAO.getInstance().listAtividadesAlocadas(projetoTO.getId()).size() > 0){
				listAtividadeAlocadaTO.addAll(AtividadeDAO.getInstance().listAtividadesAlocadas(projetoTO.getId())) ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void loadlistUsuariosPorGrupos() throws ClassNotFoundException{
		List<Long> listGruposId= new ArrayList<Long>();
		for (Grupo grupo : projetoTO.getListGrupo()) {
			listGruposId.add(grupo.getId());
		}
		listUsuariosDisponiveis = new ArrayList<UsersTO>();
		for (Users users : UsersDAO.getInstance().listUsuariosPorListaDeGrupos(listGruposId)) {
			listUsuariosDisponiveis.add(new UsersTO(users)) ;
		}
	}
	public void removerAtividadeAlocada(AtividadeAlocadaTO atividadeAlocada) throws ClassNotFoundException{
		AtividadeDAO.getInstance().removerAtividadeAlocada(atividadeAlocada.getUsersTO().getId(),atividadeAlocada.getAtividadeTO().getId());

	}

	public void addAtividadeAlocada(){
		FacesContext context = FacesContext.getCurrentInstance();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		try {
			if(usersID != 0 && atividadeID != 0 ){
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sucesso", "Tarefa alocada com sucesso."));
				AtividadeDAO.getInstance().inserirAtividadeAlocada(usersID, atividadeID, new Date().getTime(), projetoTO.getId() );
				requestContext.update("formPrincipal");
				loadBean();
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

	public AtividadeAlocadaTO getSelectedAtividadeAlocadaTO() {
		return selectedAtividadeAlocadaTO;
	}

	public void setSelectedAtividadeAlocadaTO(
			AtividadeAlocadaTO selectedAtividadeAlocadaTO) {
		this.selectedAtividadeAlocadaTO = selectedAtividadeAlocadaTO;
	}

	public long getAtividadeID() {
		return atividadeID;
	}

	public void setAtividadeID(long atividadeID) {
		this.atividadeID = atividadeID;
	}

	public long getUsersID() {
		return usersID;
	}

	public void setUsersID(long usersID) {
		this.usersID = usersID;
	}

	public List<UsersTO> getListUsuariosDisponiveis() {
		return listUsuariosDisponiveis;
	}

	public void setListUsuariosDisponiveis(List<UsersTO> listUsuariosDisponiveis) {
		this.listUsuariosDisponiveis = listUsuariosDisponiveis;
	}

	public List<AtividadeAlocadaTO> getListAtividadeAlocadaTO() {
		return listAtividadeAlocadaTO;
	}

	public void setListAtividadeAlocadaTO(
			List<AtividadeAlocadaTO> listAtividadeAlocadaTO) {
		this.listAtividadeAlocadaTO = listAtividadeAlocadaTO;
	}

	public long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(long projetoId) {
		this.projetoId = projetoId;
	}


}
