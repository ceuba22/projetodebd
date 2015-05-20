package br.com.activity.users.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@SequenceGenerator(name="USERS_ID_GEN", initialValue=1, allocationSize=5, sequenceName="USERS_ID_SEQ")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USERS_ID_GEN")
	@Column(name="ID")
	long id;

	@Column(name="NOME")
	String nome;

	@Column(name="EMAIL")
	private String email;

	@Column(name="CRIADO_EM")
	private Date criadoEm;
	
//	@Column(name="CRIADO_POR")
//	private Users criadoPor;
	
	@Column(name="ATUALIZADO_EM")
	private Date atualizadoEm;
	
//	@Column(name="ATUALIZDO_POR")
//	private Users atualizdoPor;

	@Column(name="SENHA")
	private String senha;

	@Column(name="CARGO")
	private String cargo;
	
	@Column(name="MANAGER")
	private boolean manager;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

//	public Users getCriadoPor() {
//		return criadoPor;
//	}
//
//	public void setCriadoPor(Users criadoPor) {
//		this.criadoPor = criadoPor;
//	}

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

//	public Users getAtualizdoPor() {
//		return atualizdoPor;
//	}
//
//	public void setAtualizdoPor(Users atualizdoPor) {
//		this.atualizdoPor = atualizdoPor;
//	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}



}
