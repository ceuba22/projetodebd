package br.com.activity.facade;

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
	
	public void saveUser(Users users) throws ClassNotFoundException{
		UsersDAO.getInstance().adiciona(users);
	}
	public boolean isloginUsers(Users users) throws ClassNotFoundException{
		return UsersDAO.getInstance().isloginUsers(users);
	}
}
