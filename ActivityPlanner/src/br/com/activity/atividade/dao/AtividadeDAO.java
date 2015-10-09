package br.com.activity.atividade.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.atividade.to.AtividadeTO;
import br.com.activity.atividadeAlocada.to.AtividadeAlocadaTO;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.hibenate.ConnectionFactory;
import br.com.activity.users.dao.UsersDAO;
import br.com.activity.users.to.UsersTO;

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
			stmt.setTimestamp(3, new java.sql.Timestamp(atividade.getCriadoEm().getTime()));
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

	public void inserirAtividadeAlocada(long usersId, long atividadeId, long date, long projetoID){
		String sql = "INSERT INTO atividade_users (USER_ID, ATIVIDADE_ID, DATA_INICIO_REAL, PROJETO_ID) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usersId);
			stmt.setLong(2, atividadeId);
			stmt.setTimestamp(3, new java.sql.Timestamp(date));
			stmt.setLong(4, projetoID);
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
				atividade.setCriadoEm(rs.getTimestamp("CRIADO_EM"));
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

	public List<AtividadeAlocadaTO> listAtividadesAlocadas(long projetoId){
		String sql = "SELECT DISTINCT "
				+ "AU.ID,"
				+ " AU.USER_ID,"
				+ " AU.ATIVIDADE_ID,"
				+ " AU.TEMPO_EXECUCAO_REAL,"
				+ " AU.DATA_INICIO_REAL,"
				+ " PA.PROJETO_ID"
				+ " FROM atividade_users AU, users U, atividade A, projeto_atividade PA, grupo_users GU, projeto P "
				+ "WHERE AU.USER_ID = U.ID"
				+ " AND AU.ATIVIDADE_ID = A.ID"
				+ " AND AU.ATIVIDADE_ID = PA.ATIVIDADE_ID "
				+ "AND AU.USER_ID = GU.GRUPO_ID "
				+ "AND PA.PROJETO_ID = "+projetoId;

		

		PreparedStatement stmt;
		ResultSet rs;
		List<AtividadeAlocadaTO> listAtividadesAlocadas = new ArrayList<AtividadeAlocadaTO>();
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				AtividadeAlocadaTO atividadeAlocada = new AtividadeAlocadaTO();
				atividadeAlocada.setId(rs.getLong("ID"));
				atividadeAlocada.setUsersTO(new UsersTO(UsersDAO.getInstance().getUsers((rs.getLong("USER_ID")))));;
				atividadeAlocada.setAtividadeTO(new AtividadeTO(AtividadeDAO.getInstance().getAtividade(rs.getLong("ATIVIDADE_ID"))));
				atividadeAlocada.setDataInicioReal((rs.getTimestamp("DATA_INICIO_REAL")));
				atividadeAlocada.setTempoExecucaoReal((rs.getTimestamp("TEMPO_EXECUCAO_REAL")));
				listAtividadesAlocadas.add(atividadeAlocada);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			listAtividadesAlocadas = new ArrayList<AtividadeAlocadaTO>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			listAtividadesAlocadas = new ArrayList<AtividadeAlocadaTO>();
		}
		return listAtividadesAlocadas;
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
				atividade.setCriadoEm(rs.getTimestamp("CRIADO_EM"));
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

	public List<Atividade> listAtividadesByProjeto(long projetoId){
		String sql = "SELECT * FROM  atividade A, projeto_atividade PA WHERE PA.PROJETO_ID = "+projetoId+" AND PA.ATIVIDADE_ID = A.ID; ";
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
				atividade.setCriadoEm(rs.getTimestamp("CRIADO_EM"));
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
	
	public void removerAtividadeAlocada(long projeto_Id, long atividade_Id){
		String sql = "DELETE FROM projeto_atividade WHERE PROJETO_ID="+projeto_Id+" AND ATIVIDADE_ID ="+atividade_Id;
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	} 

}
