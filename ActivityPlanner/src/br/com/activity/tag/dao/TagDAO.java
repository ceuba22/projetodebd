package br.com.activity.tag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Tag> listTags(){
			String sql = "SELECT * FROM tag";
			PreparedStatement stmt;
			ResultSet rs;
			List<Tag> listTags = new ArrayList<Tag>();
			try {
				stmt = connection.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					Tag tag = new Tag();
					tag.setId(rs.getLong("ID"));
					tag.setNome(rs.getString("NOME"));
					tag.setDescricao("DESCRICAO");
					listTags.add(tag);
				}
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				listTags = new ArrayList<Tag>();
			}
			return listTags;
	}
	
	public Tag getTag(long tagID){
		String sql = "SELECT * FROM tag WHERE ID = "+tagID;
		PreparedStatement stmt;
		ResultSet rs;
		Tag tag = new Tag();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				tag.setNome(rs.getString("NOME"));
				tag.setId(rs.getLong("ID"));
				tag.setDescricao(rs.getString("DESCRICAO"));
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tag;
	}

}
