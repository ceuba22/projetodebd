package br.com.activity.projetos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.activity.atividade.dao.AtividadeDAO;
import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.grupo.dao.GrupoDAO;
import br.com.activity.grupo.entidade.Grupo;
import br.com.activity.hibenate.ConnectionFactory;
import br.com.activity.projetos.entidade.Projetos;
import br.com.activity.util.PrioridadeTipo;
import br.com.activity.util.StatusTipo;

public class ProjetosDAO {
	private Connection connection;

	private static ProjetosDAO instance;


	public static ProjetosDAO getInstance() throws ClassNotFoundException {
		if (instance == null) {
			instance = new ProjetosDAO();
		}
		return instance;
	} 

	public ProjetosDAO() throws ClassNotFoundException{
		this.connection = new ConnectionFactory().getConnection();
	}

	public void insertProjetoATividade(List<Atividade> listAtividade, long projetoId){
		for (Atividade atividade : listAtividade) {

			String sql = 
					"INSERT INTO projeto_atividade (PROJETO_ID, ATIVIDADE_ID) VALUES (?, ?);";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, projetoId);
				stmt.setLong(2, atividade.getId());
				stmt.execute();
				stmt.close();

			} catch (SQLException u) {
				throw new RuntimeException(u);
			}

		}

	}

	public void insertProjetoDepartamento(List<Grupo> listGrupo, long projetoId){
		for (Grupo grupo : listGrupo) {

			String sql = 
					"INSERT INTO projeto_grupo (ID_PROJETO, ID_GRUPO) VALUES (?, ?);";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, projetoId);
				stmt.setLong(2, grupo.getId());
				stmt.execute();
				stmt.close();

			} catch (SQLException u) {
				throw new RuntimeException(u);
			}

		}
	}


	public void insertProjetos(Projetos projetos){

		String sql = 
				"INSERT INTO projeto (NOME, DESCRICAO, PRIORIDADE, STATUS, CRIADO_EM, CRIADO_POR, PRAZO_CONCLUSAO) VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, projetos.getNome());
			stmt.setString(2, projetos.getDescricao());
			stmt.setString(3, projetos.getPrioridade().getValue());
			stmt.setString(4, projetos.getStatus().getValue());
			stmt.setDate(5, new java.sql.Date(projetos.getCriadoEm().getTime()));
			stmt.setLong(6, projetos.getCriadoPor().getId());
			stmt.setDate(7, new java.sql.Date(projetos.getPrazoDeConclusao().getTime()));
			stmt.execute();
			stmt.close();
			insertProjetoATividade(projetos.getListAtividade(), getProjeto(projetos).getId());
			insertProjetoDepartamento(projetos.getListGrupo(), getProjeto(projetos).getId() );

		} catch (SQLException u) {
			throw new RuntimeException(u);
		}

	}

	public Projetos getProjeto(Projetos projetos){
		String sql = "SELECT * FROM projeto WHERE NOME = '"+projetos.getNome()+"' AND DESCRICAO = '"+projetos.getDescricao()+"' AND PRIORIDADE = '"+projetos.getPrioridade()+"' AND STATUS = '"+projetos.getStatus()+"'";
		Projetos projeto = new Projetos();
		ResultSet rs;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				projeto.setId(rs.getLong("ID"));
				projeto.setNome(rs.getString("NOME"));
				projeto.setDescricao(rs.getString("DESCRICAO"));
				projeto.setCriadoEm(rs.getDate("CRIADO_EM"));
				projeto.setCriadoPor(ActivityFacade.getInstance().getUsers(rs.getLong("CRIADO_POR")));
				if(rs.getString("STATUS").equals("ABERTO")){
					projeto.setStatus(StatusTipo.ABERTO);
				}
				if(rs.getString("STATUS").equals("ENCERRADO")){
					projeto.setStatus(StatusTipo.ENCERRADO);
				}
				if(rs.getString("STATUS").equals("INATIVO")){
					projeto.setStatus(StatusTipo.INATIVO);
				}
				if(rs.getString("PRIORIDADE").equals("ALTA")){
					projeto.setPrioridade(PrioridadeTipo.ALTA);
				}
				if(rs.getString("PRIORIDADE").equals("MEDIA")){
					projeto.setPrioridade(PrioridadeTipo.MEDIA);
				}
				if(rs.getString("PRIORIDADE").equals("BAIXA")){
					projeto.setPrioridade(PrioridadeTipo.BAIXA);
				}
				projeto.setListGrupo(GrupoDAO.getInstance().listGruposByProject(rs.getLong("ID")));
				projeto.setListAtividade(AtividadeDAO.getInstance().listAtividadesByProjeto(rs.getLong("ID")));
				projeto.setPrazoDeConclusao(rs.getDate("PRAZO_CONCLUSAO"));
			}
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return projeto;
	}
	
	public Projetos getProjetoById(long projetosId){
		String sql = "SELECT * FROM projeto WHERE ID = "+projetosId;
		Projetos projeto = new Projetos();
		ResultSet rs;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			if(rs.first()){
				projeto.setId(rs.getLong("ID"));
				projeto.setNome(rs.getString("NOME"));
				projeto.setDescricao(rs.getString("DESCRICAO"));
				projeto.setCriadoEm(rs.getDate("CRIADO_EM"));
				projeto.setCriadoPor(ActivityFacade.getInstance().getUsers(rs.getLong("CRIADO_POR")));
				if(rs.getString("STATUS").equals("ABERTO")){
					projeto.setStatus(StatusTipo.ABERTO);
				}
				if(rs.getString("STATUS").equals("ENCERRADO")){
					projeto.setStatus(StatusTipo.ENCERRADO);
				}
				if(rs.getString("STATUS").equals("INATIVO")){
					projeto.setStatus(StatusTipo.INATIVO);
				}
				if(rs.getString("PRIORIDADE").equals("ALTA")){
					projeto.setPrioridade(PrioridadeTipo.ALTA);
				}
				if(rs.getString("PRIORIDADE").equals("MEDIA")){
					projeto.setPrioridade(PrioridadeTipo.MEDIA);
				}
				if(rs.getString("PRIORIDADE").equals("BAIXA")){
					projeto.setPrioridade(PrioridadeTipo.BAIXA);
				}
				projeto.setListGrupo(GrupoDAO.getInstance().listGruposByProject(rs.getLong("ID")));
				projeto.setListAtividade(AtividadeDAO.getInstance().listAtividadesByProjeto(rs.getLong("ID")));
				projeto.setPrazoDeConclusao(rs.getDate("PRAZO_CONCLUSAO"));
			}
			stmt.close();

		} catch (SQLException u) {
			throw new RuntimeException(u);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return projeto;
	}


	public List<Projetos> listProjetos(){
		String sql = "SELECT * FROM projeto";
		PreparedStatement stmt;
		List<Projetos> listProjetos = new ArrayList<Projetos>();
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Projetos projetos = new Projetos();
				projetos.setId(rs.getLong("ID"));
				projetos.setNome(rs.getString("NOME"));
				projetos.setDescricao(rs.getString("DESCRICAO"));
				projetos.setCriadoEm(rs.getDate("CRIADO_EM"));
				projetos.setCriadoPor(ActivityFacade.getInstance().getUsers(rs.getLong("CRIADO_POR")));
				if(rs.getString("STATUS").equals("ABERTO")){
					projetos.setStatus(StatusTipo.ABERTO);
				}
				if(rs.getString("STATUS").equals("ENCERRADO")){
					projetos.setStatus(StatusTipo.ENCERRADO);
				}
				if(rs.getString("STATUS").equals("INATIVO")){
					projetos.setStatus(StatusTipo.INATIVO);
				}
				if(rs.getString("PRIORIDADE").equals("ALTA")){
					projetos.setPrioridade(PrioridadeTipo.ALTA);
				}
				if(rs.getString("PRIORIDADE").equals("MEDIA")){
					projetos.setPrioridade(PrioridadeTipo.MEDIA);
				}
				if(rs.getString("PRIORIDADE").equals("BAIXA")){
					projetos.setPrioridade(PrioridadeTipo.BAIXA);
				}
				projetos.setListGrupo(GrupoDAO.getInstance().listGruposByProject(rs.getLong("ID")));
				projetos.setListAtividade(AtividadeDAO.getInstance().listAtividadesByProjeto(rs.getLong("ID")));
				projetos.setPrazoDeConclusao(rs.getDate("PRAZO_CONCLUSAO"));
				
				listProjetos.add(projetos);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return listProjetos;

	}
}
