package br.com.activity.facade;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
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
	
//	====================== ATIVIDADES ============================
	
	public void inserirAtividade(Atividade atividade) throws ClassNotFoundException{
		AtividadeDAO.getInstance().inserirAtividade(atividade);
	}
	
//	===============================================================
	
	
			
//	======================= USERS =================================
	
	public void saveUser(Users users) throws ClassNotFoundException{
		UsersDAO.getInstance().adiciona(users);
	}
	public boolean isloginUsers(Users users) throws ClassNotFoundException{
		return UsersDAO.getInstance().isloginUsers(users);
	}
	
//	===============================================================
}
