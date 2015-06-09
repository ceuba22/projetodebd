package br.com.activity.beans.users;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.activity.users.entidade.Users;
import br.com.activity.users.to.UsersTO;

@SessionScoped
@ManagedBean
public class UsersMB implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5444817250101096551L;
	
	private Users userData;
	
	public long id;

	public String nome;

	public String email;

	public Date criadoEm;
	
//	public Users criadoPor;
	
	public Date atualizadoEm;
	
//	private Users atualizdoPor;

	public String senha;

	public String cargo;
	
	public boolean manager;
	
	private static UsersMB instance;

	public static UsersMB getInstance() {
		if (instance == null) {
			instance = new UsersMB();
		}
		return instance;
	}
	
	
	
	
	public UsersMB(){
		
	}
	
	public UsersTO userMBToVo(){
		UsersTO usersTO = new UsersTO();
		
		usersTO.setNome(this.nome);
		usersTO.setSenha(this.senha);
		usersTO.setEmail(this.email);
		usersTO.setCargo(this.cargo);
		usersTO.setAtualizadoEm(this.atualizadoEm);
		usersTO.setId(this.id);
		usersTO.setCriadoEm(this.criadoEm);
		usersTO.setManager(this.manager);
		
		return usersTO;
	}

	public Users getUserData() {
		return userData;
	}

	public void setUserData(Users userData) {
		this.userData = userData;
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

	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

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

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
}
