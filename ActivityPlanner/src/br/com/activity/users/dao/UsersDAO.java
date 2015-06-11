package br.com.activity.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		String sql = "INSERT INTO users(nome, cargo, criado_em, atualizado_em, email, senha, MANAGER) VALUES(?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, users.getNome());
			stmt.setString(2, users.getCargo());
			stmt.setDate(3,new java.sql.Date(users.getCriadoEm().getTime()));
			stmt.setDate(4, new java.sql.Date(users.getAtualizadoEm().getTime()));
			stmt.setString(5, users.getEmail());
			stmt.setString(6, ActivityUtil.getInstance().md5(users.getSenha()));
			stmt.setBoolean(7, users.isManager());
			stmt.execute();
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}
	
	public List<Users> listUsuariosPorGrupo( long grupoID){
		String sql = "SELECT * FROM users U, grupo_users GU WHERE U.ID = GU.USER_ID AND GU.GRUPO_ID = "+grupoID;
		PreparedStatement stmt;
		ResultSet rs;
		List<Users> listUsuario = new ArrayList<Users>();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Users usuario = new Users();
				usuario.setNome(rs.getString("NOME"));
				usuario.setSenha(rs.getString("SENHA"));
				usuario.setId(rs.getLong("ID"));
				usuario.setCargo(rs.getString("CARGO"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setManager(rs.getBoolean("MANAGER"));
				usuario.setCriadoEm(rs.getDate("CRIADO_EM"));
				usuario.setAtualizadoEm(rs.getDate("ATUALIZADO_EM"));
				listUsuario.add(usuario);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			listUsuario = new ArrayList<Users>();
		}
		return listUsuario;
	}
	
	public List<Users> listUsuariosPorListaDeGrupos( List<Long> listGruposID){
		List<Users> listUsuario = new ArrayList<Users>();
		for (Long grupoID : listGruposID) {
			String sql = "SELECT U.ID,"
					+ " U.NOME,"
					+ " U.ATUALIZADO_EM,"
					+ " U.ATUALIZADO_POR,"
					+ " U.CARGO, U.EMAIL,"
					+ " U.SENHA,"
					+ " U.MANAGER, "
					+ " U.CRIADO_EM,"
					+ " U.CRIADO_POR"
					+ " FROM users U, grupo G, grupo_users GU WHERE U.ID = GU.USER_ID AND G.ID = GU.GRUPO_ID AND U.ID= "+grupoID;
			PreparedStatement stmt;
			ResultSet rs;
			try {
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					Users usuario = new Users();
					usuario.setNome(rs.getString("NOME"));
					usuario.setSenha(rs.getString("SENHA"));
					usuario.setId(rs.getLong("ID"));
					usuario.setCargo(rs.getString("CARGO"));
					usuario.setEmail(rs.getString("EMAIL"));
					usuario.setManager(rs.getBoolean("MANAGER"));
					usuario.setCriadoEm(rs.getDate("CRIADO_EM"));
					usuario.setAtualizadoEm(rs.getDate("ATUALIZADO_EM"));
					listUsuario.add(usuario);
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				listUsuario = new ArrayList<Users>();
			}
		}
		return listUsuario;
	}
	
	
	public List<Users> listUsuariosPorLike( String query){
		String sql = "SELECT * FROM users WHERE NOME LIKE'%"+query+"%'";
		PreparedStatement stmt;
		ResultSet rs;
		List<Users> listUsuario = new ArrayList<Users>();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Users usuario = new Users();
				usuario.setNome(rs.getString("NOME"));
				usuario.setSenha("SENHA");
				usuario.setId(rs.getLong("ID"));
				usuario.setCargo(rs.getString("CARGO"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setManager(rs.getBoolean("MANAGER"));
				usuario.setCriadoEm(rs.getDate("CRIADO_EM"));
				usuario.setAtualizadoEm(rs.getDate("ATUALIZADO_EM"));
				listUsuario.add(usuario);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			listUsuario = new ArrayList<Users>();
		}
		return listUsuario;
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
				usersMB.setSenha("SENHA");
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
			e.printStackTrace();
		}
		return islogin;
	}
	
	public Users getUsers(long usersID){
		String sql = "SELECT * FROM users WHERE ID = "+usersID;
		PreparedStatement stmt;
		ResultSet rs;
		Users users = new Users();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				users.setNome(rs.getString("NOME"));
				users.setSenha("SENHA");
				users.setId(rs.getLong("ID"));
				users.setCargo(rs.getString("CARGO"));
				users.setEmail(rs.getString("EMAIL"));
				users.setManager(rs.getBoolean("MANAGER"));
				users.setCriadoEm(rs.getDate("CRIADO_EM"));
				users.setAtualizadoEm(rs.getDate("ATUALIZADO_EM"));
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public UsersMB getUsersMB() {
		return usersMB;
	}

	public void setUsersMB(UsersMB usersMB) {
		this.usersMB = usersMB;
	}
}