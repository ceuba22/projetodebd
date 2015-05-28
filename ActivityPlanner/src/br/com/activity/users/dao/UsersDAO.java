package br.com.activity.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import br.com.activity.beans.users.UsersMB;
import br.com.activity.hibenate.ConnectionFactory;
import br.com.activity.users.entidade.Users;
import br.com.activity.util.ActivityUtil;

public class UsersDAO {
	private UsersMB usersMB;
	private Connection connection;
	String nome;
	String cargo;
	Date criado_em;
	Date atualizado_em;
	String email;
	String senha;

	private static UsersDAO instance;

	public static UsersDAO getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new UsersDAO();
		}
		return instance;
	} 

	public UsersDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Users users){

		String sql = "INSERT INTO users(nome, cargo, criado_em, atualizado_em, email, senha) VALUES(?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, users.getNome());
			stmt.setString(2, users.getCargo());
			stmt.setDate(3,new java.sql.Date(users.getCriadoEm().getTime()));
			stmt.setDate(4, new java.sql.Date(users.getAtualizadoEm().getTime()));
			stmt.setString(5, users.getEmail());
			stmt.setString(6, ActivityUtil.getInstance().md5(users.getSenha()));
			stmt.execute();
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	public boolean isloginUsers(Users users){
		boolean islogin = false;
		String sql = "SELECT * FROM users where EMAIL = '"+users.getEmail()+"' AND SENHA = '"+ActivityUtil.getInstance().md5(users.getSenha())+"'";
		PreparedStatement stmt;
		
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				usersMB = new UsersMB();
				usersMB.setNome(rs.getString("NOME"));
				usersMB.setSenha(sql);
				usersMB.setId(rs.getLong("ID"));
				usersMB.setCargo(rs.getString("CARGO"));
				usersMB.setEmail(rs.getString("EMAIL"));
				usersMB.setManager(rs.getBoolean("MANAGER"));
				usersMB.setCriadoEm(rs.getDate("CRIADO_EM"));
				usersMB.setAtualizadoEm(rs.getDate("ATUALIZADO_EM"));
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usersMB", usersMB);
				
				islogin = true;
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return islogin;
	}

	public UsersMB getUsersMB() {
		return usersMB;
	}

	public void setUsersMB(UsersMB usersMB) {
		this.usersMB = usersMB;
	}
}