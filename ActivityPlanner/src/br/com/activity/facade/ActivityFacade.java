package br.com.activity.facade;

import java.util.List;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.projetos.dao.ProjetosDAO;
import br.com.activity.projetos.entidade.Projetos;
import br.com.activity.tag.dao.TagDAO;
import br.com.activity.tag.entidade.Tag;
import br.com.activity.users.dao.UsersDAO;
import br.com.activity.users.entidade.Users;


public class ActivityFacade {

	private static ActivityFacade instance;

	public static ActivityFacade getInstance() {
		if (instance == null) {
			instance = new ActivityFacade();
		}
		return instance;
	} 

	public ActivityFacade(){}
	
	//======================== PROJETOS ==============================
	
	public void inserirProjeto(Projetos projetos) throws ClassNotFoundException{
		ProjetosDAO.getInstance().insertProjetos(projetos);
	}
	
	public List<Projetos> listProjetos() throws ClassNotFoundException{
		return ProjetosDAO.getInstance().listProjetos();
	}
	
	//================================================================



	//	====================== ATIVIDADES ============================

	public void inserirAtividade(Atividade atividade) throws ClassNotFoundException{
		AtividadeDAO.getInstance().inserirAtividade(atividade);
	}

	public List<Atividade> listAtividades() throws ClassNotFoundException{
		return AtividadeDAO.getInstance().listAtividades();
	}

	//	===============================================================



	//	======================= USERS =================================

	public void saveUser(Users users) throws ClassNotFoundException{
		UsersDAO.getInstance().adiciona(users);
	}

	public Users getUsers(long usersId) throws ClassNotFoundException{
		return UsersDAO.getInstance().getUsers(usersId);
	}
	public boolean isloginUsers(Users users) throws ClassNotFoundException{
		return UsersDAO.getInstance().isloginUsers(users);
	}

	//	===============================================================


	//========================= TAG ================================

	public Tag getTag(long tagId) throws ClassNotFoundException{
		return TagDAO.getInstance().getTag(tagId);
	}

	//==============================================================



	//======================= GRUPO ================================

	public List<Grupo> listGrupos() throws ClassNotFoundException{
		return GrupoDAO.getInstance().listGrupos();
	}
}
