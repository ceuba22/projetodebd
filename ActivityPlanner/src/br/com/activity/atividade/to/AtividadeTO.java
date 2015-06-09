package br.com.activity.atividade.to;

import java.util.Date;

import br.com.activity.atividade.entidade.Atividade;
import br.com.activity.facade.ActivityFacade;
import br.com.activity.tag.to.TagTO;

public class AtividadeTO {
	
	private long id;
	
	private String nome;
	
	private String descricao;
	
	private Date criadoEm;
	
	private int peso;
	
	private long tempoExecucao;
	
	private TagTO tag;
	
	private long tagId;
	
	private String tipoExecucao;

	public AtividadeTO(){
		
	}
	
	public AtividadeTO(Atividade atividade){
		this.id = atividade.getId();
		this.nome = atividade.getNome();
		this.peso = atividade.getPeso();
		this.descricao = atividade.getDescricao();
		this.tempoExecucao = atividade.getTempoExecucao();
		this.criadoEm = atividade.getCriadoEm();
		this.tag = new TagTO(atividade.getTag());
		this.tipoExecucao = atividade.getTipoExecucao();
		
	}
	
	public Atividade toVo(){
		Atividade atividade = new Atividade();
		
		atividade.setNome(this.getNome());
		atividade.setDescricao(this.getDescricao());
		atividade.setPeso(this.getPeso());
		atividade.setTempoExecucao(this.getTempoExecucao());
		atividade.setCriadoEm(new Date());
		try {
			atividade.setTag(ActivityFacade.getInstance().getTag(this.tagId));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		atividade.setTipoExecucao(this.tipoExecucao);
		
		return atividade;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public long getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public TagTO getTag() {
		return tag;
	}

	public void setTag(TagTO tag) {
		this.tag = tag;
	}

	public String getTipoExecucao() {
		return tipoExecucao;
	}

	public void setTipoExecucao(String tipoExecucao) {
		this.tipoExecucao = tipoExecucao;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	

}
