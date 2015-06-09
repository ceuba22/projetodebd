package br.com.activity.atividade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.hibenate.ConnectionFactory;

public class AtividadeDAO {

	private static AtividadeDAO instance;

	private Connection connection;

	public static AtividadeDAO getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new AtividadeDAO();
		}
		return instance;
	}

	public AtividadeDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}

	public void inserirAtividade(Atividade atividade){
		String sql = "INSERT INTO atividade(NOME, DESCRICAO, CRIADO_EM, TAG_ID, PESO, TEMPO_EXECUCAO_PREVISTO, TEMPO_TIPO) VALUES(?,?,?,?,?,?,?)";
		try {

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, atividade.getNome());
			stmt.setString(2, atividade.getDescricao());
			stmt.setDate(3, new java.sql.Date(atividade.getCriadoEm().getTime()));
			stmt.setInt(4, atividade.getPeso());
			stmt.setLong(5, atividade.getTag().getId());
			stmt.setLong(6, atividade.getTempoExecucao());
			stmt.setString(7, atividade.getTipoExecucao());
			stmt.execute();
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	public List<Atividade> listAtividades(){
		String sql = "SELECT * FROM atividade";
		PreparedStatement stmt;
		ResultSet rs;
		List<Atividade> listAtividades = new ArrayList<Atividade>();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Atividade atividade = new Atividade();
				atividade.setId(rs.getLong("ID"));
				atividade.setNome(rs.getString("NOME"));
				atividade.setDescricao(rs.getString("DESCRICAO"));
				atividade.setCriadoEm(rs.getDate("CRIADO_EM"));
				atividade.setPeso(rs.getInt("PESO"));
				atividade.setTag(ActivityFacade.getInstance().getTag(rs.getLong("TAG_ID")));
				atividade.setTempoExecucao(rs.getLong("TEMPO_EXECUCAO_PREVISTO"));
				atividade.setTipoExecucao(rs.getString("TEMPO_TIPO"));
				listAtividades.add(atividade);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			listAtividades = new ArrayList<Atividade>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			listAtividades = new ArrayList<Atividade>();
		}
		return listAtividades;
	}
	
	public Atividade getAtividade (long atividadeID){
		String sql = "SELECT * FROM atividade WHERE ID ="+atividadeID;
		Atividade atividade = new Atividade();
		ResultSet rs;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				atividade.setId(rs.getLong("ID"));
				atividade.setNome(rs.getString("NOME"));
				atividade.setDescricao(rs.getString("DESCRICAO"));
				atividade.setCriadoEm(rs.getDate("CRIADO_EM"));
				atividade.setPeso(rs.getInt("PESO"));
				atividade.setTag(ActivityFacade.getInstance().getTag(rs.getLong("TAG_ID")));
				atividade.setTempoExecucao(rs.getLong("TEMPO_EXECUCAO_PREVISTO"));
				atividade.setTipoExecucao(rs.getString("TEMPO_TIPO"));
			}
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return atividade;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	} 

}
