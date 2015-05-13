package br.com.activity.entidade.users;

import org.hibernate.Session;

import br.com.activity.hibenate.DAO;

public class UsersDAO  {
	
	private static UsersDAO instance;
	
	private UsersDAO() {}

	public static UsersDAO getInstance() {
		if (instance == null) {
			instance = new UsersDAO();
			
		}
		return instance;
	}
//	public final void save(Users users){
//		
////		Session session = DAO.getSession();
////		
////		session.save(users);
//		
//				EntityManager em = factory.createEntityManager();
//				em.getTransaction().begin();
//				em.persist(users);
//				em.getTransaction().commit();
//				em.close();
//				factory.close();
//
//	}
	
	public void save(Users users) {
		Session session = DAO.getSession();
		session.save(users);
		}

}
