package br.com.activity.users.to;

import java.util.Date;

import br.com.activity.beans.users.UsersMB;
import br.com.activity.users.entidade.Users;


public class UsersTO implements Comparable<UsersTO> {
	
	private long id;
	
	private String nome;
	
	private String cargo;
	
	private Date criadoEm;
	
//	private UsersTO criadoPor;
	
	private boolean manager;
	
	private Date atualizadoEm;
	
//	private UsersTO atualizadoPor;
	
	private String email;
	
	private String senha;
	
	public UsersTO(){
		
	}
	
	public UsersTO(UsersMB usersMB){
		this.id = usersMB.getId();
		this.nome = usersMB.getNome();
		this.email = usersMB.getEmail();
		this.cargo = usersMB.getCargo();
		this.atualizadoEm = usersMB.getAtualizadoEm();
		this.criadoEm = usersMB.getCriadoEm();
//		this.criadoPor = new UsersTO();
//		this.atualizadoPor = new UsersTO();
		this.manager = usersMB.isManager();
		this.senha = usersMB.getSenha();
	}
	
	public UsersTO(Users users){
		this.id = users.getId();
		this.nome = users.getNome();
		this.email = users.getEmail();
		this.cargo = users.getCargo();
		this.atualizadoEm = users.getAtualizadoEm();
		this.criadoEm = users.getCriadoEm();
//		this.criadoPor = new UsersTO();
//		this.atualizadoPor = new UsersTO();
		this.manager = users.isManager();
		this.senha = users.getSenha();
		
	}
	
	public Users toVO(){
		Users users = new Users();
		users.setNome(this.getNome());
		users.setCargo(this.getCargo());
		users.setEmail(this.getEmail());
		users.setManager(this.isManager());
		users.setCriadoEm(new Date());
		users.setAtualizadoEm(new Date());
//		users.setCriadoPor(new Users());
//		users.setCriadoPor(new Users());
		users.setSenha(this.getSenha());
		
		return users;
	}
	@Override
	public int compareTo(UsersTO o) {
		// TODO Auto-generated method stub
		return 0;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Date getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
//	public UsersTO getCriadoPor() {
//		return criadoPor;
//	}
//	public void setCriadoPor(UsersTO criadoPor) {
//		this.criadoPor = criadoPor;
//	}
	
	public Date getAtualizadoEm() {
		return atualizadoEm;
	}
	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
//	public UsersTO getAtualizadoPor() {
//		return atualizadoPor;
//	}
//	public void setAtualizadoPor(UsersTO atualizadoPor) {
//		this.atualizadoPor = atualizadoPor;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
