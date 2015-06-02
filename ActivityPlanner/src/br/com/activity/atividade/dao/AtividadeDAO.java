package br.com.activity.atividade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.activity.atividade.entidade.Atividade;
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
		String sql = "INSERT INTO atividade(NOME, DESCRICAO, CRIADO_EM, PESO, TEMPO_EXECUCAO_PREVISTO, TEMPO_TIPO) VALUES(?,?,?,?,?,?)";
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, atividade.getNome());
			stmt.setString(2, atividade.getDescricao());
			stmt.setDate(3, new java.sql.Date(atividade.getCriadoEm().getTime()));
//			stmt.setLong(4, atividade.getTag().getId());
			stmt.setInt(4, atividade.getPeso());
			stmt.setLong(5, atividade.getTempoExecucao());
			stmt.setString(6, atividade.getTipoExecucao());
			stmt.execute();
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	} 

}
