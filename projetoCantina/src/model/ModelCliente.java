package model;

public class ModelCliente {
	private int idUsuario;
	private String nomeUsuario, senhaUsuario, emailUsuario, foneUsuario, cpfUsuario, cepUsuario, statusCliente;
	
	public String getStatusCliente() {
		return statusCliente;
	}
	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenhaUsuario() {
		return senhaUsuario;
	}
	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getFoneUsuario() {
		return foneUsuario;
	}
	public void setFoneUsuario(String foneUsuario) {
		this.foneUsuario = foneUsuario;
	}
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public String getCepUsuario() {
		return cepUsuario;
	}
	public void setCepUsuario(String cepUsuario) {
		this.cepUsuario = cepUsuario;
	}
}
