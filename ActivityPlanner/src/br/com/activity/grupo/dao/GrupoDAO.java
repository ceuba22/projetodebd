package br.com.activity.grupo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.hibenate.ConnectionFactory;

public class GrupoDAO {

	private Connection connection;

	private static GrupoDAO instance;


	public static GrupoDAO getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new GrupoDAO();
		}
		return instance;
	} 

	public GrupoDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insertGrupo(Grupo grupo){

		String sql = "INSERT INTO grupo(NOME, DESCRICAO, CRIADO_EM) VALUES(?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, grupo.getNome());
			stmt.setString(2, grupo.getDescricao());
			stmt.setDate(3, new java.sql.Date(grupo.getCriadoEm().getTime()));
			stmt.execute();
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}

	}
	public boolean jaPossuiUsuarioNoGrupo(long usersId, long grupoId){
		boolean possui = false;
		String sql = "SELECT * FROM grupo_users WHERE GRUPO_ID ="+grupoId+" AND USER_ID = "+usersId;
		ResultSet rs;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				possui = true;
			}
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return possui;
	}

	public Grupo getGrupo(long grupoID){
		String sql = "SELECT * FROM grupo WHERE ID ="+grupoID;
		Grupo grupo = new Grupo();
		ResultSet rs;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				grupo.setId(rs.getLong("ID"));
				grupo.setNome(rs.getString("NOME"));
				grupo.setDescricao(rs.getString("DESCRICAO"));
				grupo.setCriadoEm(rs.getDate("CRIADO_EM"));
			}
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return grupo;
	}

	public void addUsuarioAoDepto(long usersId, long grupoId ){

		String sql = "INSERT INTO grupo_users(USER_ID, GRUPO_ID) VALUES (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usersId);
			stmt.setLong(2, grupoId);
			stmt.execute();
			stmt.close();
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	public List<Grupo> listGrupos(){
		String sql = "SELECT * FROM grupo";
		PreparedStatement stmt;
		List<Grupo> listgrupo = new ArrayList<Grupo>();
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Grupo grupo = new Grupo();
				grupo.setId(rs.getLong("ID"));
				grupo.setNome(rs.getString("NOME"));
				grupo.setDescricao(rs.getString("DESCRICAO"));
				grupo.setCriadoEm(rs.getDate("CRIADO_EM"));
				listgrupo.add(grupo);
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listgrupo;

	}
	
	public List<Grupo> listGruposByProject(long projectId){
		String sql = "SELECT * FROM  grupo G, projeto_grupo PG WHERE PG.ID_PROJETO ="+projectId+"  AND PG.ID_GRUPO = G.ID";
		PreparedStatement stmt;
		List<Grupo> listgrupo = new ArrayList<Grupo>();
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Grupo grupo = new Grupo();
				grupo.setId(rs.getLong("ID"));
				grupo.setNome(rs.getString("NOME"));
				grupo.setDescricao(rs.getString("DESCRICAO"));
				grupo.setCriadoEm(rs.getDate("CRIADO_EM"));
				listgrupo.add(grupo);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listgrupo;

	}

}
