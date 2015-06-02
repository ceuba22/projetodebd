package br.com.activity.tag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.activity.hibenate.ConnectionFactory;
import br.com.activity.tag.entidade.Tag;

public class TagDAO {
	
private static TagDAO instance;
	
	private Connection connection;

	public static TagDAO getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new TagDAO();
		}
		return instance;
	}
	
	public TagDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserirTag(Tag tag){
		String sql = "INSERT INTO tag (NOME, DESCRICAO) VALUES (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tag.getNome());
			stmt.setString(2, tag.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
