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

}
